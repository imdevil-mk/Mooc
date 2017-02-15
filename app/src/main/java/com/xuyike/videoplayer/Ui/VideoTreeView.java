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
import android.widget.ListView;
import android.widget.Toast;

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
/**
 * Created by xuyike on 2017/2/11.
 */

public class VideoTreeView extends Activity {

    private ListView mTree;
    private SimpleTreeListViewAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;
    private List<OrgBean> mDatas2;
    private String stl;
    private Gson gson = new Gson();
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private String url="http://120.27.104.19:3002/Hubu/Interface/Android/course_info.php?format=json&course_id=2";
    private static final int COURSE = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COURSE:
                    List<CourseChapter.DataEntity> courseList = (List<CourseChapter.DataEntity>) msg.obj;
                    Log.d("Tag_Course",courseList.get(0).getChapter_name());
                    //initDatas();
                    mDatas = new ArrayList<FileBean>();
                    FileBean bean = new FileBean(1, 0, courseList.get(0).getChapter_name());
                    mDatas.add(bean);
                    try
                    {
                        mAdapter = new SimpleTreeListViewAdapter<FileBean>(mTree,VideoTreeView.this,
                                mDatas, 0);
                        mTree.setAdapter(mAdapter);
                    } catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolist_main);



        /**
         * 获取课程ID
         */
        Intent intent = getIntent();
        String id = intent.getStringExtra("Course_ID");
        Log.d("ID","<<<<<<<<<<<<<<<<<<<<<<<"+id);

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
        mDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1, 0, "第一章");
        mDatas.add(bean);
        bean = new FileBean(2, 0, "第二章");
        mDatas.add(bean);
        bean = new FileBean(3, 0, "第三章");
        mDatas.add(bean);
        bean = new FileBean(4, 1, "第一节");
        mDatas.add(bean);
        bean = new FileBean(5, 1, "第二节");
        mDatas.add(bean);
        bean = new FileBean(7, 3, "第一节");
        mDatas.add(bean);
        bean = new FileBean(8, 3, "第二节");
        mDatas.add(bean);

        // initDatas
        mDatas2 = new ArrayList<OrgBean>();
        OrgBean bean2 = new OrgBean(1, 0, "根目录1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(2, 0, "根目录2");
        mDatas2.add(bean2);
        bean2 = new OrgBean(3, 0, "根目录3");
        mDatas2.add(bean2);
        bean2 = new OrgBean(4, 1, "根目录1-1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(5, 1, "根目录1-2");
        mDatas2.add(bean2);
        bean2 = new OrgBean(6, 5, "根目录1-2-1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(7, 3, "根目录3-1");
        mDatas2.add(bean2);
        bean2 = new OrgBean(8, 3, "根目录3-2");
        mDatas2.add(bean2);

    }

    private void TestNetWork() {
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }


}
