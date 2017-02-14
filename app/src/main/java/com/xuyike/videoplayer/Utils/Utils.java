package com.xuyike.videoplayer.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by xuyike on 2017/2/11.
 * 转换时间格式，显示视频时间
 */

public class Utils {
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public Utils(){
        mFormatBuilder=new StringBuilder();
        mFormatter=new Formatter(mFormatBuilder, Locale.getDefault());

    }
    /**
     * 把毫秒转换成1:20:30形式
     */

    public String stringForTime(int timeMs){
        int totalSeconds=timeMs/1000;
        int seconds=totalSeconds%60;
        int minutes=(totalSeconds/60)%60;
        int hours=totalSeconds/3600;
        mFormatBuilder.setLength(0);
        if(hours>0){
            return mFormatter.format("%d:%02d:%02d",hours,minutes,seconds).toString();

        }else {
            return mFormatter.format("%02d:%02d",minutes,seconds).toString();
        }
    }

public String getSystemTime(){
    SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
    return format.format(new Date());
}
}
