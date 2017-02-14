package com.imdevil.mooc.ui;


import android.content.Context;
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
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.JavaBean.ListViewCollege;
import com.imdevil.mooc.Jsonbinder.College;
import com.imdevil.mooc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollegeFragment extends Fragment {

    private List<ListViewCollege> colleges = new ArrayList<>();
    private ListView listView;
    private View rootView;

    public CollegeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView != null)
        {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent)
            {
                parent.removeView(rootView);
            }
        }else {
            rootView = inflater.inflate(R.layout.fragment_college, container, false);

            listView = (ListView) rootView.findViewById(R.id.college_listView);

            final int[] drawables = {R.drawable.btn_1,R.drawable.btn_2,R.drawable.btn_3,R.drawable.btn_4,R.drawable.btn_5,R.drawable.btn_6,R.drawable.btn_7,R.drawable.btn_8,R.drawable.btn_9,R.drawable.btn_10,R.drawable.btn_11,R.drawable.btn_12,R.drawable.btn_13,};;

            final Gson gson = new Gson();
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            String url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_class_list.php?format=json";
            if (networkInfo == null || !networkInfo.isAvailable()) {
                Toast.makeText(getContext(), "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
            } else {
                HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        final College college = gson.fromJson(response, new TypeToken<College>() {
                        }.getType());
                        final List<College.DataEntity> collegeList= college.getData();
                        int code = college.getCode();
                        if (code == 400){
                            ListViewCollege item;
                            for(int i=0;i<collegeList.size();i++){
                                item = new ListViewCollege(drawables[i],collegeList.get(i).getName());
                                colleges.add(item);
                            }
                            final College_Adapter adapter = new College_Adapter(getContext(),R.layout.college_item,colleges);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listView.setAdapter(adapter);
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(Exception e) {
                    }
                });
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("Target",position+1);
                    Fragment fragment = new College_Course_Fragment();
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.college_frameLayout,fragment).addToBackStack(null).commit();
                }
            });
            }
        return  rootView;
    }

}
