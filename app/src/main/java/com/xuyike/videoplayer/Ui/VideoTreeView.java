package com.xuyike.videoplayer.Ui;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
//veshi
private String stl;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolist_main);

        mTree = (ListView) findViewById(R.id.id_listview);

        initDatas();
        try
        {
            mAdapter = new SimpleTreeListViewAdapter<FileBean>(mTree, this,
                    mDatas, 0);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        initEvent();
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
}
