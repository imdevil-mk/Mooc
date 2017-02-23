package com.xuyike.videoplayer.video;

/**
 * Created by Numb on 2017/2/9.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.imdevil.mooc.R;
import com.xuyike.videoplayer.Utils.PixeUtil;
import com.xuyike.videoplayer.Utils.Utils;

import java.util.ArrayList;

public class videoPlay  extends Activity {
    protected static final int PROGRESS=1;//更新进度
    protected static final int DELAYED_HIDECONTROLPLAYER=2;//隐藏控制面板
private  Uri uri;
    private AudioManager mAudioManager;//音频管理器
    private float mBrightness;
    private int screen_height,screen_width;
    private String video_url;
private String video_name;
    private TextView tv_video_title;

    private TextView tv_system_time;

    private Button btn_voice;
    private SeekBar seekBar_voice;
private Button btn_fullscreen;

    private TextView tv_current_time;
    private SeekBar seekBar_video;
    private TextView tv_duration;


    private  Button btn_play_pause;

private boolean isFullScreen=false;

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

private boolean isAdjust=false;
    private int threshold=54;//控制音量手势误触的值

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case PROGRESS:
                    //得到视频的当前播放进度
                    int currentPosition= videoView.getCurrentPosition();
                    tv_current_time.setText(utils.stringForTime(currentPosition));
                    //seekBar进度更新
                    seekBar_video.setProgress(currentPosition);



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
        mAudioManager=(AudioManager)getSystemService(AUDIO_SERVICE);//获取系统的音频服务



        initView();
        initData();
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
        video_name=intent.getStringExtra("video_name");
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
            tv_video_title.setText(video_name);
        }

    }
    /**
     * 加载布局文件
     */
    public void initView(){

        PixeUtil.initContext(this);
        videoView = (VideoView)findViewById(R.id.videoview);
        tv_video_title=(TextView)findViewById(R.id.tv_video_title);


        btn_fullscreen=(Button)findViewById(R.id.btn_fullscreen);
        btn_voice=(Button)findViewById(R.id.btn_voice);
        seekBar_voice=(SeekBar)findViewById(R.id.seekbar_voice);
        /**
         * 设备的最大音量
         */
        int streamMaxVolume=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        /**
         * 设备当前的音量
         */
        int streamVolme=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekBar_voice.setMax(streamMaxVolume);
        seekBar_voice.setProgress(streamVolme);

        tv_current_time=(TextView)findViewById(R.id.tv_current_time);
        seekBar_video=(SeekBar)findViewById(R.id.seekbar_video);
        tv_duration=(TextView)findViewById(R.id.tv_duration);


        btn_play_pause=(Button)findViewById(R.id.btn_paly_pause);

        ll_control_player=(LinearLayout)findViewById(R.id.ll_control_player);
//获取屏幕的宽高
        screen_width=getResources().getDisplayMetrics().widthPixels;
        screen_height=getResources().getDisplayMetrics().heightPixels;
    }


private void setListener(){
    //设置按钮的监听
    btn_play_pause.setOnClickListener(mOnClickListener);
btn_fullscreen.setOnClickListener(mOnClickListener);
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


    seekBar_voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                /**
                 * 设置当前设备的音量
                 */
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {


        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

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

    OnClickListener mOnClickListener=new OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_paly_pause:
                    StartOrPause();
                    break;
                case R.id.btn_fullscreen:
                    if(isFullScreen){
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//切换为竖屏
                    }
                    else{
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//切换为横屏

                    }
                    break;


            }
        }


    };
    private void initData(){
        utils=new Utils();
        isDestroyed=false;

//2.实例化手势识别器
        detector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {



                super.onLongPress(e);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {

                StartOrPause();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

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

    /**
     * 调节声音
     * @param detlaY
     */
//    private void changeVolume(float detlaY){
//        int max=mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大声音
//        int current=mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//当前声音
//        int index=(int)(detlaY/screen_height*max*3);
//        int volum=Math.max(current+index,0);
//        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volum,0);
//        seekBar_voice.setProgress(volum);
//
//    }

    /**
     * 调节亮度
     *
     * @return
     */
//    private void changeBrightness(float detlaY){
//        WindowManager.LayoutParams attributes=getWindow().getAttributes();
//        mBrightness=attributes.screenBrightness;
//        float index=detlaY/screen_height/3;
//        mBrightness+=index;
//        if(mBrightness>1.0f){
//            mBrightness=1.0f;
//        }
//        if(mBrightness<0.01f){
//            mBrightness=0.01f;
//        }
//        attributes.screenBrightness=mBrightness;
//        getWindow().setAttributes(attributes);
//
//    }

    private float startY;
    private float audioTouchRang;//屏幕滑动范围
    private int mVol;
//使用手势识别器

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//执行父类的方法
        detector.onTouchEvent(event);
//        float x=event.getX();
//        float y=event.getY();
//        float lastx=0,lasty=0;
//        switch (event.getAction()){
//            /**
//             * 手指落下屏幕的那一刻（只会调用一次）
//             */
//            case MotionEvent.ACTION_DOWN:
//                lastx=x;
//                lasty=y;
//                break;
//            /**
//             * 手指在屏幕上移动（调用多次）
//             */
//            case MotionEvent.ACTION_MOVE:
//                //手指移动是x和y的偏移量
//                float deltaX=x-lastx;
//                float deltaY=y-lasty;
//                float absdeltaX=Math.abs(deltaX);
//                float absdeltaY=Math.abs(deltaY);
//                /**
//                 * 手势合法验证
//                 */
//                if(absdeltaX>threshold&&absdeltaY>threshold){
//                    if(absdeltaX<absdeltaY){
//                        isAdjust=true;
//                    }
//                    else {
//                        isAdjust=false;
//                    }
//                }
//                else if(absdeltaX<threshold&&absdeltaY>threshold){
//                    isAdjust=true;
//                }
//                else if(absdeltaX>threshold&&absdeltaY<threshold){
//                    isAdjust=false;
//                }
//                if(isAdjust){
//                    /**
//                     * 在判断好当前手势事件已经合法的前提下，去区分此时手势应该调节亮度还是调节声音
//                     */
//                    if(x<screen_width/2){
//                        /**
//                         * 调节亮度
//                         */
//
//                        changeBrightness(-deltaY);
//                    }
//                    else {
//                        /**
//                         * 调节声音
//                         */
//
//                        changeVolume(-deltaY);
//                    }
//                }
//                lastx=x;
//                lasty=y;
//                break;
//
//            /**
//             * 手指离开屏幕的那一刻（只会调用一次）
//             */
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }

        switch (event.getAction()){
            /**
             * 手指落下屏幕的那一刻（只会调用一次）
             */
            case MotionEvent.ACTION_DOWN:
                removeDelayedHideControlPlayer();
              startY=event.getY();
                audioTouchRang=Math.min(screen_height,screen_width);
               mVol= mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

                break;
            /**
             * 手指在屏幕上移动（调用多次）
             */
            case MotionEvent.ACTION_MOVE:

                float endY=event.getY();
                float distanceY=startY-endY;

                float datel=distanceY/audioTouchRang;
                float volume=distanceY/audioTouchRang*mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

                float volumeS=Math.min(Math.max(volume+mVol,0),mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

                if(datel!=0){
                    updateVolume((int)volumeS);
                    seekBar_voice.setProgress((int)volumeS);
                }

                break;

            /**
             * 手指离开屏幕的那一刻（只会调用一次）
             */
            case MotionEvent.ACTION_UP:
                sendDelayedHideControlPlayer();
                break;
            default:
                break;
        }

        return true;//对时间进行处理了
    }

    protected void updateVolume(int volume){
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0);
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

    private  void setVideoViewScale(int width,int height){
        ViewGroup.LayoutParams layoutParams=videoView.getLayoutParams();
        layoutParams.width=width;
        layoutParams.height=height;
        videoView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1=ll_control_player.getLayoutParams();
        layoutParams1.width=width;
        layoutParams1.height=height;
        ll_control_player.setLayoutParams(layoutParams1);
    }
/**
 * 监听屏幕方向的改变
 */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        /**
         * 当屏幕方向为横向的时候
         */
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){

            isFullScreen=true;
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        }
        /**
         * 当屏幕方向为纵向的时候
         */
        else{
            isFullScreen=false;
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    /**
     *视频的播放和暂停
     */
    private void StartOrPause(){
        if(isPlaying){
//暂停
            videoView.pause();
            //按钮状态设置为播放
            btn_play_pause.setBackgroundResource(R.drawable.btn_play_selector);

        }else{

            videoView.start();
            //按钮播放设置为暂停
            btn_play_pause.setBackgroundResource(R.drawable.btn_pause_selector);

        }
        isPlaying=!isPlaying;
    }
}
