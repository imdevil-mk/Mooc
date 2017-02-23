package com.imdevil.mooc.ui;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdevil.mooc.HttpThread.DownloadSTH;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.Jsonbinder.College;
import com.imdevil.mooc.Jsonbinder.Course;
import com.imdevil.mooc.Jsonbinder.Login_Course;
import com.imdevil.mooc.Jsonbinder.My;
import com.imdevil.mooc.R;
import com.imdevil.mooc.Adapter.TabViewPagerAdapter;
import com.imdevil.mooc.control.Dialog;
import com.imdevil.mooc.control.DialogContainer;
import com.imdevil.mooc.control.My_Course;
import com.imdevil.mooc.control.Self;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tab;
    private Toolbar toolbar;
    private ConnectivityManager connectivityManager;
    private List<Fragment> list = new ArrayList<>();
    private List<String> theme = new ArrayList<>();
    private NetworkInfo networkInfo;
    private LinearLayout dialogContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         *初始化极光推送
         */
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register();

        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo);

        theme.add("课程");
        theme.add("消息");
        theme.add("我的");

        list.add(new CourseFragment());
        list.add(new NewsFragment());
        list.add(new MyFragment());

        tab.addTab(tab.newTab().setText(theme.get(0)));
        tab.addTab(tab.newTab().setText(theme.get(1)));
        tab.addTab(tab.newTab().setText(theme.get(2)));
        tab.setTabMode(TabLayout.MODE_FIXED);

        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(),list,theme);
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        if(pref.getBoolean("remember_psw",false)){
            getMy(pref.getString("ID",""),pref.getString("PassWord",""));
        }
    }

    private void register() {
        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.vp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dialogContainer = new LinearLayout(MainActivity.this);
        dialogContainer.setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void getMy(String id,String psw) {
       final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case OK:
                        My my = (My) msg.obj;
                        Self self = new Self(MainActivity.this,null);
                        self.setData(my);
                        for (int i=0;i<my.getData().getMycourse().size();i++){
                             getMyCourse(my.getData().getMycourse().get(i),my.getData().getUserinfo().getStudent_id());
                        }
                        new AlertDialog.Builder(MainActivity.this).setTitle("我的课程").setView(dialogContainer).
                                setPositiveButton("确定",null).show();
                }
            }
        };
        String url = "http://120.27.104.19:3002/Hubu/Interface/Android/student_login.php?format=json";
        HttpThreadForJson.LoginIn(id, psw, url, new HttpThreadForJson.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Gson gson = new Gson();
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
    private void getMyCourse(String Course_ID,String Student_ID) {
        String url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_introduce.php?format=json&course_id="+Course_ID+"&student_id="+Student_ID;
        final Handler course_handler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        Dialog dialog = new Dialog(MainActivity.this,null);
                        dialog.setData((Login_Course)msg.obj);
                        dialogContainer.addView(dialog);
                }
            }
        };
        TestNetWork();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(MainActivity.this, "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
        } else {
            HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Gson gson = new Gson();
                    final Login_Course course = gson.fromJson(response, new TypeToken<Login_Course>() {
                    }.getType());
                    int code = course.getCode();
                    if (code == 424) {
                        Message message = new Message();
                        message.obj = course;
                        message.what = 1;
                        course_handler.sendMessage(message);
                    }
                }
                @Override
                public void onError(Exception e) {
                }
            });
        }
    }
    private void TestNetWork() {
        connectivityManager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }
}
