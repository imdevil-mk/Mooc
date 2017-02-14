package com.imdevil.mooc.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.Jsonbinder.College;
import com.imdevil.mooc.control.HotCourse;
import com.imdevil.mooc.control.ImageTextButton;
import com.imdevil.mooc.R;
import com.imdevil.mooc.Adapter.TestNormalAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment{


    public CourseFragment() {

    }


    private RollPagerView mRollViewPager;
    private ImageTextButton imageTextButton_1;
    private ImageTextButton imageTextButton_2;
    private ImageTextButton imageTextButton_3;
    private ImageTextButton imageTextButton_4;
    private ImageTextButton imageTextButton_5;
    private ImageTextButton imageTextButton_6;
    private ImageTextButton imageTextButton_7;
    private ImageTextButton imageTextButton_8;
    private HotCourse hotCourse;
    private Gson gson = new Gson();
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private static final int HOT = 0;
    private String url = "http://120.27.104.19:3002/Hubu/Interface/Android/course_class_list.php?format=json";
    private String hot_url = "http://120.27.104.19:3002/Hubu/Interface/Android/hot_course_list.php?format=json";
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what){
                case HOT:
                    com.imdevil.mooc.Jsonbinder.HotCourse hots = (com.imdevil.mooc.Jsonbinder.HotCourse) msg.obj;
                    hotCourse.setData(hots);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        regist(view);
        TestNetWork();
        SetupRollViewPager();
        setImageTextButtonImg();
        getImageTextButtonText();
        setImageTextButtonClick();
        getHotCourse();
        return view;
    }

    private void getHotCourse() {
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(getContext(), "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
        } else {
            HttpThreadForJson.getJson(hot_url, new HttpThreadForJson.HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    final com.imdevil.mooc.Jsonbinder.HotCourse hot_course = gson.fromJson(response, new TypeToken<com.imdevil.mooc.Jsonbinder.HotCourse>() {
                    }.getType());
                    int code = hot_course.getCode();
                    if (code == 408){
                        Message msg = new Message();
                        msg.what = HOT;
                        msg.obj = hot_course;
                        handler.sendMessage(msg);
                    }
                }
                @Override
                public void onError(Exception e) {
                }
            });
        }
    }

    private void setImageTextButtonClick() {
        imageTextButton_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("college_id",1);
                startActivity(intent);
            }
        });
        imageTextButton_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("college_id",2);
                startActivity(intent);
            }
        });
        imageTextButton_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("college_id",3);
                startActivity(intent);
            }
        });
        imageTextButton_4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("college_id",4);
                startActivity(intent);
            }
        });
        imageTextButton_5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("college_id",6);
                startActivity(intent);
            }
        });
        imageTextButton_7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                intent.putExtra("college_id",7);
                startActivity(intent);
            }
        });
        imageTextButton_8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getImageTextButtonText() {
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(getContext(), "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
        } else {
            HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    final College college = gson.fromJson(response, new TypeToken<College>() {
                    }.getType());
                    final List<College.DataEntity> collegeList = college.getData();
                    int code = college.getCode();
                    if (code == 400) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageTextButton_1.setTextText(collegeList.get(0).getName());
                                imageTextButton_2.setTextText(collegeList.get(1).getName());
                                imageTextButton_3.setTextText(collegeList.get(2).getName());
                                imageTextButton_4.setTextText(collegeList.get(3).getName());
                                imageTextButton_5.setTextText(collegeList.get(4).getName());
                                imageTextButton_6.setTextText(collegeList.get(5).getName());
                                imageTextButton_7.setTextText(collegeList.get(6).getName());
                                imageTextButton_8.setTextText("更多");
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

    private void setImageTextButtonImg() {
        imageTextButton_1.setImageRes(R.drawable.btn_1);
        imageTextButton_2.setImageRes(R.drawable.btn_2);
        imageTextButton_3.setImageRes(R.drawable.btn_3);
        imageTextButton_4.setImageRes(R.drawable.btn_4);
        imageTextButton_5.setImageRes(R.drawable.btn_5);
        imageTextButton_6.setImageRes(R.drawable.btn_6);
        imageTextButton_7.setImageRes(R.drawable.btn_7);
        imageTextButton_8.setImageRes(R.drawable.more);
    }

    private void SetupRollViewPager() {
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(5000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }

    private void regist(View view) {
        mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        imageTextButton_1 = (ImageTextButton) view.findViewById(R.id.image_text_button_1);
        imageTextButton_2 = (ImageTextButton) view.findViewById(R.id.image_text_button_2);
        imageTextButton_3 = (ImageTextButton) view.findViewById(R.id.image_text_button_3);
        imageTextButton_4 = (ImageTextButton) view.findViewById(R.id.image_text_button_4);
        imageTextButton_5 = (ImageTextButton) view.findViewById(R.id.image_text_button_5);
        imageTextButton_6 = (ImageTextButton) view.findViewById(R.id.image_text_button_6);
        imageTextButton_7 = (ImageTextButton) view.findViewById(R.id.image_text_button_7);
        imageTextButton_8 = (ImageTextButton) view.findViewById(R.id.image_text_button_8);
        hotCourse = (HotCourse) view.findViewById(R.id.hot_course);
    }

    private void TestNetWork() {
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }

}
