package com.xuyike.videoplayer.Ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.Jsonbinder.College;
import com.imdevil.mooc.Jsonbinder.Course;
import com.imdevil.mooc.Jsonbinder.CourseChapter;
import com.imdevil.mooc.R;
import com.xuyike.videoplayer.Utils.adapter.TreeListViewAdapter;
import com.xuyike.videoplayer.adapter.*;
import com.xuyike.videoplayer.bean.*;
import com.xuyike.videoplayer.Utils.*;
import com.xuyike.videoplayer.video.videoPlay;

/**
 * Created by xuyike on 2017/2/11.
 */

public class VideoTreeView extends Activity {

    private ListView mTree;
    private SimpleTreeListViewAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;

    private ImageView title_image;
    private Gson gson = new Gson();
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private String url="http://120.27.104.19:3002/Hubu/Interface/Android/course_info.php?format=json&course_id=";
    private static final int COURSE = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COURSE:
                    int id=1;//章id
                    final List<CourseChapter.DataEntity> courseList = (List<CourseChapter.DataEntity>) msg.obj;

                    //initDatas();
                    mDatas = new ArrayList<FileBean>();

//                    FileBean bean = new FileBean(1, 0, courseList.get(0).getChapter_name());
//
//                    mDatas.add(bean);

                    for(int i=0;i<courseList.size();i++){
                        FileBean bean = new FileBean(id, 0, courseList.get(i).getChapter_name(),id);
                        id++;
                        Log.d("Tag_Course",courseList.get(i).getChapter_name());
                        mDatas.add(bean);

                    }
                    for(int j=0;j<courseList.get(j).getSection().size();j++){
                        List<CourseChapter.DataEntity.SectionEntity> sectionEntityList=courseList.get(j).getSection();

                        for(int n=0;n<sectionEntityList.size();n++){
                            FileBean s_bean=new FileBean(id,j+1,sectionEntityList.get(n).getName(),n);

                            Log.d("Tag_Section",sectionEntityList.get(n).getName());
                            id++;
                            mDatas.add(s_bean);
                        }

                    }

                    try
                    {
                        mAdapter = new SimpleTreeListViewAdapter<FileBean>(mTree,VideoTreeView.this,
                                mDatas, 0);
                        mTree.setAdapter(mAdapter);
                    } catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
/*
为每个item设置点击监听事件   小节可以点击进入播放界面，章点击只能打开或关闭
 */
                    mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener()
                    {
                        @Override
                        public void onClick(Node node, int position)
                        {
                            if (node.isLeaf()&&node.getpId()!=0)
                            {
                                int sum=0;
                                int m=0;
                                int n=0;
                               // String video_url1;
                                for(int i=1;i<node.getpId();i++){
                                   sum = sum +courseList.get(i-1).getSection().size();
                                    Log.d("size",courseList.get(i-1).getSection().size()+"");
                                }
                                Log.d("position",position+"");
                                sum+=node.getpId();
                                m=position-sum;

                               //video_url1= courseList.get(node.getpId()-1).getSection().get(m).getVideo_url();
                              n=  node.getpId()-1;
                               Log.d("url",courseList.get(n).getSection().get(m).getVideo_url());
                                if(courseList.get(n).getSection().get(m).getVideo_url()!=null){
                                    Intent intent = new Intent(VideoTreeView.this, videoPlay.class);
                                    intent.putExtra("Video_url",courseList.get(n).getSection().get(m).getVideo_url());
                                    intent.putExtra("video_name",courseList.get(n).getSection().get(m).getName());
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"暂无视频",Toast.LENGTH_SHORT).show();
                                }


                            }
                        }
                    });
            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolist_main);

        title_image=(ImageView)findViewById(R.id.title_imiage);

        /**
         * 获取课程ID
         */
        Intent intent = getIntent();
        String id = intent.getStringExtra("Course_ID");
        String image=intent.getStringExtra("Course_Image");

        Glide.with(VideoTreeView.this).load(image).into(title_image);
        url=url+id;
        Log.d("url","<<<<<<<<<<<<<<<<<<<<<<<"+url);
        TestNetWork();
        regist();
        getCourse();

        //initDatas();
        /*try
        {
            mAdapter = new SimpleTreeListViewAdapter<FileBean>(mTree, this,
                    mDatas, 0);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        initEvent()*/;
    }

    private void regist() {
        mTree = (ListView) findViewById(R.id.id_listview);
    }

    private void getCourse() {
        if (networkInfo == null || !networkInfo.isAvailable()) {
            Toast.makeText(VideoTreeView.this, "当前网络不可用，无法发送数据请求", Toast.LENGTH_SHORT).show();
        } else {
            HttpThreadForJson.getJson(url, new HttpThreadForJson.HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    final CourseChapter chapter = gson.fromJson(response, new TypeToken<CourseChapter>() {
                    }.getType());
                    final List<CourseChapter.DataEntity> courseList = chapter.getData();
                    int code = chapter.getCode();
                    if (code == 411) {
                        Message msg = new Message();
                        msg.what = COURSE;
                        msg.obj = courseList;
                        handler.sendMessage(msg);
                    }
                }

                @Override
                public void onError(Exception e) {
                }
            });
        }
    }

    private void initEvent()
    {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener()
        {
            @Override
            public void onClick(Node node, int position)
            {
                if (node.isLeaf())
                {
                    Toast.makeText(VideoTreeView.this, node.getName(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTree.setOnItemLongClickListener(new OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id)
            {
                // DialogFragment
                final EditText et = new EditText(VideoTreeView.this);
                new AlertDialog.Builder(VideoTreeView.this).setTitle("Add Node")
                        .setView(et)
                        .setPositiveButton("Sure", new OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                if (TextUtils.isEmpty(et.getText().toString()))
                                    return;
                                mAdapter.addExtraNode(position, et.getText()
                                        .toString());
                            }
                        }).setNegativeButton("Cancel", null).show();

                return true;
            }
        });
    }

    private void initDatas()
    {
//        mDatas = new ArrayList<FileBean>();
//        FileBean bean = new FileBean(1, 0, "第一章");
//        mDatas.add(bean);
//        bean = new FileBean(2, 0, "第二章");
//        mDatas.add(bean);
//        bean = new FileBean(3, 0, "第三章");
//        mDatas.add(bean);
//        bean = new FileBean(4, 1, "第一节");
//        mDatas.add(bean);
//        bean = new FileBean(5, 1, "第二节");
//        mDatas.add(bean);
//        bean = new FileBean(7, 3, "第一节");
//        mDatas.add(bean);
//        bean = new FileBean(8, 3, "第二节");
//        mDatas.add(bean);


    }

    private void TestNetWork() {
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }


}
