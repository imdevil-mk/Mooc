package com.imdevil.mooc.ui;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdevil.mooc.Adapter.College_Adapter;
import com.imdevil.mooc.Adapter.College_Course_Adapter;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.JavaBean.CollegeCourseItem;
import com.imdevil.mooc.JavaBean.ListViewCollege;
import com.imdevil.mooc.Jsonbinder.College;
import com.imdevil.mooc.Jsonbinder.Course;
import com.imdevil.mooc.R;
import com.xuyike.videoplayer.Ui.VideoTreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class College_Course_Fragment extends Fragment {

    private View rootView;
    private ListView listView;
    private List<CollegeCourseItem> courses = new ArrayList<>();
    private String base_url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_name_list.php?format=json&course_class_code=";


    public College_Course_Fragment() {

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView != null)
        {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent)
            {
                parent.removeView(rootView);
            }
        }else{
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_college__course_,null);
            listView = (ListView) rootView.findViewById(R.id.college_course_listView);
            int target = getArguments().getInt("Target");
            String url = base_url+target;
            Log.d("<<<<<<",url);
            final Gson gson = new Gson();
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isAvailable()) {
                Toast.makeText(getContext(), "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
            } else {
                HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        final Course course = gson.fromJson(response, new TypeToken<Course>() {
                        }.getType());
                        final List<Course.DataEntity> courseList= course.getData();
                        Log.d("<<<<<<<<<<<<<<<",courseList.get(0).getName());
                        int code = course.getCode();
                        if (code == 405){
                            Log.d("<<<code<<<<<<<<",""+code);
                            CollegeCourseItem item;
                            for (int i=0;i<courseList.size();i++){
                                item = new CollegeCourseItem(courseList.get(i).getImage(),courseList.get(i).getName(),courseList.get(i).getChoose_count());
                                courses.add(item);
                                Log.d("<<<<<item<<<<",item.getName());
                                Log.d("<<<<<<<<courses<<<<<",courses.get(0).getName());
                            }

                            final College_Course_Adapter adapter = new College_Course_Adapter(getContext(),R.layout.college_course_item,courses);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Toast.makeText(getContext(),courseList.get(position).getCourse_id(),Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getActivity(), VideoTreeView.class);
                                            intent.putExtra("Course_ID",courseList.get(position).getCourse_id());
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(Exception e) {
                    }
                });
            }
        }

        return rootView;
    }

}
