package com.imdevil.mooc.HttpThread;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alan on 2017/2/9.
 */

public class HttpThreadForJson {

    //向服务器发送消息的线程，并获取反馈，listener用于接收返回的数据
    // Listener用于防止子线程未返回服务器数据，必须添加
    public static void sendHttpMessage(final String text, final String address, final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;

                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    OutputStream out = connection.getOutputStream();
                    String content = "text=" + text;
                    out.write(content.getBytes());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String str;

                    while ((str = reader.readLine()) != null) {
                        sb.append(str);
                    }

                    if (listener != null) {

                        listener.onFinish(sb.toString());
                    }
                    out.close();
                    //  echoFromPHP = sb.toString();
                    //在logcat输出这个值用以检查验证
                    //  System.out.println("PHP服务器返回的字符串信息为:" + echoFromPHP);
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();


    }

    //HttpCallbackListener用于防止子线程未返回服务器数据
    public interface HttpCallbackListener {
        void onFinish(String response);

        void onError(Exception e);
    }


    //从url获取Json数据，HttpCallbackListener用处同上，使用方法同sendHttpMessage
    public static void getJson(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);

                    if (conn.getResponseCode() == 200) {                //200表示请求成功
                        InputStream is = conn.getInputStream();       //以输入流的形式返回
                        //将输入流转换成字符串
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        String jsonString = baos.toString();
                        baos.close();
                        is.close();
                        if (listener != null) {

                            listener.onFinish(jsonString.toString());
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onError(e);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }

            }
        }).start();

    }
}
