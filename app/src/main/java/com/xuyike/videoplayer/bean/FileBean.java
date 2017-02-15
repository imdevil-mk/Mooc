package com.xuyike.videoplayer.bean;

/**
 * Created by Numb on 2017/2/11.
 */

import com.imdevil.mooc.Jsonbinder.CourseChapter;
import com.xuyike.videoplayer.Utils.annotation.*;

import java.util.List;


public class FileBean
{
    // (type= String.class)
    @TreeNodeId
    private int id;

    @TreeNodePid
    private int pId;

    @TreeNodeLabel
    private String label;
    @TreeNodemId
   private int mId;
    private String desc;

    public FileBean(int id, int pId, String label,int mId)
    {
        this.id = id;
        this.pId = pId;
        this.label = label;
        this.mId=mId;
    }

    public int getmId()
    {
        return mId;
    }

    public void setmId(int id)
    {
        this.mId = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getpId()
    {
        return pId;
    }

    public void setpId(int pId)
    {
        this.pId = pId;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    // ...

}