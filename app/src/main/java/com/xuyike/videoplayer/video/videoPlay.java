package com.xuyike.videoplayer.video;

/**
 * Created by Numb on 2017/2/9.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.imdevil.mooc.R;
import com.xuyike.videoplayer.Utils.Utils;

import java.util.ArrayList;

public class videoPlay  extends Activity {
    protected static final int PROGRESS=1;//更新进度
    protected static final int DELAYED_HIDECONTROLPLAYER=2;//隐藏控制面板
private  Uri uri;
    private String video_url;

    private TextView tv_video_title;
    private ImageView iv_battery;
    private TextView tv_system_time;

    private Button btn_voice;
    private SeekBar seekBar_voice;
    private Button btn_switch;

    private TextView tv_current_time;
    private SeekBar seekBar_video;
    private TextView tv_duration;

  //  private Button btn_exit;
  //  private Button btn_pre;
    private  Button btn_play_pause;
   // private Button btn_next;
    private  Button btn_screen;

    private Utils utils;
    private LinearLayout ll_control_player;
    private VideoView videoView;
    //定义手势识别器
    private GestureDetector detector;
    //是否显示控制面板
    private boolean isShowControl=false;
    /**
     * 判断当前activity是否被销毁
     * true 被销毁
     */
    private  boolean isDestroyed;
    /**
     * 判断是否播放
     * true 播放
     * false 暂停
     */
    private boolean isPlaying=false;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PROGRESS:
                    //得到视频的当前播放进度
                    int currentPosition= videoView.getCurrentPosition();
                   // tv_current_time.setText(utils.stringForTime(currentPosition));
                    //seekBar进度更新
                    seekBar_video.setProgress(currentPosition);

                    //设置电量的显示
                  //  setBattery();
                    //设置显示手机当前的时间
                   // tv_system_time.setText(utils.getSystemTime());
                    //消息的死循环
                    if(!isDestroyed){
                        handler.sendEmptyMessageDelayed(PROGRESS,1000);
                    }

                    break;

                case DELAYED_HIDECONTROLPLAYER:
                    hideControlPlayer();
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplayer);



        initData();
        initView();
        getData();
        setData();
        setListener();



      //  videoView.setMediaController(new MediaController(videoPlay.this));
    }
    /**
     * 得到数据
     */
    private void getData(){
        //得到播放列表
        //videoItems= (ArrayList<VideoItem>)getIntent().getSerializableExtra("videolist");
       // position=getIntent().getIntExtra("position",0);
        //得到播放地址--来自第三方软件--文件夹管理器和浏览器
       // uri=getIntent().getData();
        //videoView.setVideoURI(uri);


        /**
         * 获取视频地址
         */
        Intent intent = getIntent();
        video_url = intent.getStringExtra("Video_url");
        Log.d("url",video_url);
        uri = Uri.parse(video_url);
    }
    /**
     * 设置数据
     */
    private void setData(){

      if(uri!=null){
            //设置播放地址
            videoView.setVideoURI(uri);
            //设置标题
            tv_video_title.setText(uri.toString());
        }

    }
    /**
     * 加载布局文件
     */
    public void initView(){

        videoView = (VideoView)findViewById(R.id.videoview);
        tv_video_title=(TextView)findViewById(R.id.tv_video_title);
        tv_system_time=(TextView)findViewById(R.id.tv_system_time);
        iv_battery=(ImageView) findViewById(R.id.iv_battery);

        btn_voice=(Button)findViewById(R.id.btn_voice);
        seekBar_voice=(SeekBar)findViewById(R.id.seekbar_voice);
        btn_switch=(Button)findViewById(R.id.btn_switch);

        tv_current_time=(TextView)findViewById(R.id.tv_current_time);
        seekBar_video=(SeekBar)findViewById(R.id.seekbar_video);
        tv_duration=(TextView)findViewById(R.id.tv_duration);

       // btn_exit=(Button) findViewById(R.id.btn_exit);
       // btn_pre=(Button) findViewById(R.id.btn_pre);
        btn_play_pause=(Button)findViewById(R.id.btn_paly_pause);
      //  btn_next=(Button) findViewById(R.id.btn_next);
       // btn_screen=(Button) findViewById(R.id.btn_screen);
        ll_control_player=(LinearLayout)findViewById(R.id.ll_control_player);
    }


private void setListener(){

    seekBar_video.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        /**
         * 当seekbar状态发生变化的时候回调这个方法
         * seekBar 自身
         * progress seekBar位置，视频的长度和seekbar的长度一一对应
         * fromUser 当手指滑动，来自用户为true，自身变化为false
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                //拖动到具体的视频位置
                videoView.seekTo(progress);
            }
        }
        /**
         * 手指开始在seekBar控件上拖动的时候回调这个方法
         */
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //手在滑动的过程中，是不可以隐藏的
            removeDelayedHideControlPlayer();
        }
        /**
         * 手指离开seekBar控件。回调这个方法
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            sendDelayedHideControlPlayer();
        }
    });

    //监听视频是否准备要播放了，开始播放
    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            //开始播放视频
            videoView.start();
            isPlaying=true;
            //得到视频的长度
            int duration=videoView.getDuration();
            tv_duration.setText(utils.stringForTime(duration));
            //1.视频的总时长要关联SeekBar
            seekBar_video.setMax(duration);
            //设置隐藏控制面板
            hideControlPlayer();
            //开始更新进度
            handler.sendEmptyMessage(PROGRESS);

        }
    });

    //添加控制栏
    //videoView.setMediaController(new MediaController(this));
}
    private void initData(){
        utils=new Utils();
        isDestroyed=false;
//
//        //监听电量变化
//        IntentFilter filter=new IntentFilter();
//        filter.addAction(Intent.ACTION_BATTERY_CHANGED);//电量变化的时候，系统会发这个广播
//        receiver=new MyBroadcastRecerver();
//        registerReceiver(receiver,filter);
//2.实例化手势识别器
        detector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
               // Toast.makeText(getApplicationContext(),"长按",Toast.LENGTH_SHORT).show();
                super.onLongPress(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //Toast.makeText(getApplicationContext(),"双击",Toast.LENGTH_SHORT).show();
                StartOrPause();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                 // Toast.makeText(getApplicationContext(),"单击",Toast.LENGTH_SHORT).show();
                if(isShowControl){
                   removeDelayedHideControlPlayer();
                    hideControlPlayer();
                }
                else {
                    showControlPlayer();
                    sendDelayedHideControlPlayer();
                }
                return true;
            }

        });
    }

//使用手势识别器

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//执行父类的方法
        detector.onTouchEvent(event);
        return true;//对时间进行处理了
    }

    //发送控制栏延迟消息
    private void sendDelayedHideControlPlayer(){
        handler.sendEmptyMessageDelayed(DELAYED_HIDECONTROLPLAYER,5000);
    }

    /**
     * 移除消息
     */
    protected void removeDelayedHideControlPlayer(){
        handler.removeMessages(DELAYED_HIDECONTROLPLAYER);
    }
    //是否显示控制面板
    private void hideControlPlayer(){
        ll_control_player.setVisibility(View.GONE);
        isShowControl=false;
    }

    private void showControlPlayer(){
        ll_control_player.setVisibility(View.VISIBLE);
        isShowControl=true;
    }

    /**
     *视频的播放和暂停
     */
    private void StartOrPause(){
        if(isPlaying){

            videoView.pause();//暂停
            //按钮状态设置为播放
            btn_play_pause.setBackgroundResource(R.drawable.btn_play_selector);

        }else{
            //播放
            videoView.start();
            //按钮播放设置为暂停
            btn_play_pause.setBackgroundResource(R.drawable.btn_pause_selector);

        }
        isPlaying=!isPlaying;
    }
}
