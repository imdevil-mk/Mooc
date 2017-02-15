package com.imdevil.mooc.control;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.imdevil.mooc.R;
import com.xuyike.videoplayer.Ui.VideoTreeView;

/**
 * Created by imdevil on 2017/2/5 0005.
 */
public class HotCourse extends LinearLayout{


    private HotCourseButton hotCourseButton_1;
    private HotCourseButton hotCourseButton_2;
    private HotCourseButton hotCourseButton_3;
    private HotCourseButton hotCourseButton_4;
    private HotCourseButton hotCourseButton_5;
    private HotCourseButton hotCourseButton_6;
    private HotCourseButton hotCourseButton_7;
    private HotCourseButton hotCourseButton_8;


    public HotCourse(Context context) {
        super(context,null);
    }

    public HotCourse(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(getContext()).inflate(R.layout.course_hot,this,true);
        this.hotCourseButton_1 = (HotCourseButton) findViewById(R.id.hot_course_button_1);
        this.hotCourseButton_2 = (HotCourseButton) findViewById(R.id.hot_course_button_2);
        this.hotCourseButton_3 = (HotCourseButton) findViewById(R.id.hot_course_button_3);
        this.hotCourseButton_4 = (HotCourseButton) findViewById(R.id.hot_course_button_4);
        this.hotCourseButton_5 = (HotCourseButton) findViewById(R.id.hot_course_button_5);
        this.hotCourseButton_6 = (HotCourseButton) findViewById(R.id.hot_course_button_6);
        this.hotCourseButton_7 = (HotCourseButton) findViewById(R.id.hot_course_button_7);
        this.hotCourseButton_8 = (HotCourseButton) findViewById(R.id.hot_course_button_8);
    }

    public HotCourseButton getHotCourseButton_1() {
        return hotCourseButton_1;
    }

    public HotCourseButton getHotCourseButton_2() {
        return hotCourseButton_2;
    }

    public HotCourseButton getHotCourseButton_3() {
        return hotCourseButton_3;
    }

    public HotCourseButton getHotCourseButton_4() {
        return hotCourseButton_4;
    }

    public HotCourseButton getHotCourseButton_5() {
        return hotCourseButton_5;
    }

    public HotCourseButton getHotCourseButton_6() {
        return hotCourseButton_6;
    }

    public HotCourseButton getHotCourseButton_7() {
        return hotCourseButton_7;
    }

    public HotCourseButton getHotCourseButton_8() {
        return hotCourseButton_8;
    }

    public void setData(final com.imdevil.mooc.Jsonbinder.HotCourse hots){
        getHotCourseButton_1().getTextView().setText(hots.getData().get(0).getName());
        Glide.with(getContext()).load(hots.getData().get(0).getImage()).into(getHotCourseButton_1().getImageView());
        getHotCourseButton_1().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(0).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_2().getTextView().setText(hots.getData().get(1).getName());
        Glide.with(getContext()).load(hots.getData().get(1).getImage()).into(getHotCourseButton_2().getImageView());
        getHotCourseButton_2().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(1).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_3().getTextView().setText(hots.getData().get(2).getName());
        Glide.with(getContext()).load(hots.getData().get(2).getImage()).into(getHotCourseButton_3().getImageView());
        getHotCourseButton_3().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(2).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_4().getTextView().setText(hots.getData().get(3).getName());
        Glide.with(getContext()).load(hots.getData().get(3).getImage()).into(getHotCourseButton_4().getImageView());
        getHotCourseButton_4().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(3).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_5().getTextView().setText(hots.getData().get(4).getName());
        Glide.with(getContext()).load(hots.getData().get(4).getImage()).into(getHotCourseButton_5().getImageView());
        getHotCourseButton_5().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(4).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_6().getTextView().setText(hots.getData().get(5).getName());
        Glide.with(getContext()).load(hots.getData().get(5).getImage()).into(getHotCourseButton_6().getImageView());
        getHotCourseButton_6().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(5).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_7().getTextView().setText(hots.getData().get(6).getName());
        Glide.with(getContext()).load(hots.getData().get(6).getImage()).into(getHotCourseButton_7().getImageView());
        getHotCourseButton_7().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(6).getCourse_id());
                getContext().startActivity(intent);
            }
        });

        getHotCourseButton_8().getTextView().setText(hots.getData().get(7).getName());
        Glide.with(getContext()).load(hots.getData().get(7).getImage()).into(getHotCourseButton_8().getImageView());
        getHotCourseButton_8().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoTreeView.class);
                intent.putExtra("Course_ID",hots.getData().get(8).getCourse_id());
                getContext().startActivity(intent);
            }
        });
    }



}
