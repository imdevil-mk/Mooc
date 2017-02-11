package com.xuyike.videoplayer.domain;

/**
 * Created by xuyike on 2017/2/11.
 */

import java.io.Serializable;

/**
 * 代表一个具体的视频
 */
public class VideoItem implements Serializable{

    //标题
private  String title;
    //时长
private String duration;
    //文件大小
    private  long size;
    //播放地址
    private  String data;


    @Override
    public String toString() {
        return "VideoItem{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
