package com.imdevil.mooc.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.Jsonbinder.Login_Course;
import com.imdevil.mooc.Jsonbinder.My;
import com.imdevil.mooc.R;
import com.imdevil.mooc.control.My_Course;
import com.imdevil.mooc.control.Self;



/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private View  rootView;
    private LinearLayout linearLayout;
    private Button button;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson = new Gson();
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private  final static int OK = 0;
    private final static int OK_COURSE = 0;
    private Handler handler;
    public MyFragment() {

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
            regist();
            Log.d("TAG_Login",pref.getBoolean("Login",false)+"");
            if(pref.getBoolean("Login",false)){
                linearLayout.removeAllViews();  //*如果登录成功，去掉登录按钮
                getMy(pref.getString("ID",""),pref.getString("PassWord","")); //网络访问得到登录后的数据，通过handler发送回来
                handler = new Handler(){
                    public void handleMessage(Message msg){
                        switch (msg.what){
                            case OK:
                                My my = (My) msg.obj;
                                Self self = new Self(getContext(),null);
                                self.setData(my);
                                linearLayout.addView(self);
                                for (int i=0;i<my.getData().getMycourse().size();i++){
                                    getMyCourse(my.getData().getMycourse().get(i),my.getData().getUserinfo().getStudent_id());
                                }
                        }
                    }
                };
            }
            else{
                addLogin();
            }
        }

        return  rootView;
    }

    private void getMyCourse(String Course_ID,String Student_ID) {
        String url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_introduce.php?format=json&course_id="+Course_ID+"&student_id="+Student_ID;
        final Handler course_handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case OK_COURSE:
                        My_Course my_course = new My_Course(getContext(),null);
                        my_course.setData((Login_Course)msg.obj);
                        linearLayout.addView(my_course);
                }
            }
        };
        TestNetWork();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(getContext(), "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
        } else {
            HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    final Login_Course course = gson.fromJson(response, new TypeToken<Login_Course>() {
                    }.getType());
                    int code = course.getCode();
                    if (code == 424) {
                        Message message = new Message();
                        message.obj = course;
                        message.what = OK_COURSE;
                        course_handler.sendMessage(message);
                    }
                }
                @Override
                public void onError(Exception e) {
                }
            });
        }
    }

    private void addLogin() {
        button = (Button) rootView.findViewById(R.id.ll_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void TestNetWork() {
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }

    private void regist() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my,null);
        linearLayout = (LinearLayout) rootView.findViewById(R.id.my_fragment_ll);
        pref = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    private void getMy(String id,String psw) {
        String url = "http://120.27.104.19:3002/Hubu/Interface/Android/student_login.php?format=json";
        HttpThreadForJson.LoginIn(id, psw, url, new HttpThreadForJson.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                My my = gson.fromJson(response,My.class);
                Message message = new Message();
                message.obj = my;
                message.what = OK;
                handler.sendMessage(message);
            }
            @Override
            public void onError(Exception e) {

            }
        });
    }

}
