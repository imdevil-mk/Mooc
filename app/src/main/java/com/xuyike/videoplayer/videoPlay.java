package com.xuyike.videoplayer;

/**
 * Created by Numb on 2017/2/9.
 */
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.imdevil.mooc.R;

public class videoPlay  extends AppCompatActivity {

    private Button btn_load;
    private Button btn_play;
    private Button btn_pause;
    private SeekBar sb_progress;
    private VideoView vv_player;
    private boolean flag = true;
    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            sb_progress.setProgress(msg.getData().getInt("current", 0)/1000);
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_main);
        loadView();//加载
        addListener();
    }

    public void loadView(){

        btn_load = (Button)findViewById(R.id.btn_load);
        btn_play = (Button)findViewById(R.id.btn_play);
        btn_pause = (Button)findViewById(R.id.btn_pause);

        sb_progress = (SeekBar)findViewById(R.id.sb_progress);

        vv_player = (VideoView)findViewById(R.id.vv_player);
    }
    public void addListener(){

        btn_load.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                Uri uri = Uri.parse("http://192.168.0.105:8080/test/6.mp4");
                vv_player.setVideoURI(uri);


                vv_player.setMediaController(new MediaController(videoPlay.this));

                vv_player.requestFocus();
            }
        });

        btn_play.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                vv_player.start();
                sb_progress.setMax(vv_player.getDuration()/1000);

                new Thread(){
                    @Override
                    public void run() {

                        super.run();
                        while (flag) {
                            Message msg = handler.obtainMessage();
                            msg.getData().putInt("current", vv_player.getCurrentPosition());
                            handler.sendMessage(msg);
                            try {
                                sleep(1000);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        }
                    }
                }.start();
            }
        });

        btn_pause.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                vv_player.pause();
            }
        });

    }
}
