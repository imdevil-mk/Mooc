package com.imdevil.mooc.HttpThread;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;




/**
 * Created by Alan on 2017/2/13.
 *  下载一些东西的方法
 */

public class DownloadSTH {
    //url为文件地址链接，context为调用activity的上下文信息，默认保存到手机的Download文件夹中
    public static void DownloadPPT(String url, Context context){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setDescription("HubuAPP");
        request.setTitle("HubuAPP Download");
        request.setAllowedOverRoaming(false);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager downManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);
    }
}
