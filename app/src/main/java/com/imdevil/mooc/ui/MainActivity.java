package com.imdevil.mooc.ui;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.Jsonbinder.College;
import com.imdevil.mooc.Jsonbinder.Course;
import com.imdevil.mooc.R;
import com.imdevil.mooc.Adapter.TabViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tab;
    private Toolbar toolbar;

    private List<Fragment> list = new ArrayList<>();
    private List<String> theme = new ArrayList<>();

    private Button getJson;
    private Button parseJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.vp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);

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

        getJson = (Button) findViewById(R.id.getJson);
        parseJson = (Button) findViewById(R.id.parseJson);


        /**
         *从服务器获取json数据的按键事件
         */
        getJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_class_list.php?format=json";
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isAvailable()) {
                    Toast.makeText(MainActivity.this, "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();

                } else {
                    /**
                     * 用下面这个方法开启http请求，获取json数据
                     */
                    HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            //数据成功返回后需要走的逻辑写在这里
                            Log.d("返回的Json数据", response);
                        }
                        @Override
                        public void onError(Exception e) {
                            //异常情况需要走的逻辑
                        }
                    });
                }
            }
        });


        /**
         *处理Json数据的按键逻辑
         */
        parseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_class_list.php?format=json";
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isAvailable()) {
                    Toast.makeText(MainActivity.this, "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();

                } else {
                    //用此方法开启http请求
                    HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            //数据成功返回后需要走的逻辑写在这里
                            Log.d("返回的Json数据", response);
                            parseJson(response);//解析json调用的方法
                        }
                        @Override
                        public void onError(Exception e) {
                            //异常情况需要走的逻辑
                        }
                    });
                }
            }
        });


        /**
         * 登录按键的事件 暂时未用到
         * @param jsonData

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.31.140:3002/Hubu/Interface/testAPI_1.php";
                HttpThreadForJson.LoginIn(editText.getText().toString(), password.getText().toString(), url, new HttpThreadForJson.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Log.d("返回的数据", response);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
         */


    }



    /**     解析college json的方法
     */
    public void parseJson(String jsonData) {
        Gson gson = new Gson();
        College college = gson.fromJson(jsonData, new TypeToken<College>() {
        }.getType());
        List<College.DataEntity> collegeList= college.getData();

        int code = college.getCode();
        if (code == 400){
            Log.d("MainActivity","code"+code);
            for (College.DataEntity data : collegeList) {
                Log.d("MainActivity", "data_name: " + data.getName());
                Log.d("MainActivity", "college_id：" + data.getCollege_id());
                Log.d("MainActivity", "data_Icon：" + data.getIcon() );
                Log.d("MainActivity","data_Type：" + data.getType()  );
            }
        }

    }

    /**    解析 course json的方法 先注释掉，你需要的时候再安排怎么用
     *
     *

    public void parseJson1(String jsonData) {
        Gson gson = new Gson();
        Course course = gson.fromJson(jsonData, new TypeToken<Course>() {
        }.getType());
        List<Course.DataEntity> courseList= course.getData();

        int code = course.getCode();
        if (code == 400){
            Log.d("MainActivity","code"+code);
            for (Course.DataEntity data : courseList) {
                Log.d("MainActivity", "data_name: " + data.getName());
                Log.d("MainActivity", "college_id：" + data.getCourse_id());
                Log.d("MainActivity", "Description：" + data.getDescription() );
                Log.d("MainActivity","Image：" + data.getImage()  );
            }
        }

    }
     */





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
