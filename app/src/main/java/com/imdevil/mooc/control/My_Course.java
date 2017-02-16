package com.imdevil.mooc.control;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imdevil.mooc.Jsonbinder.Course;
import com.imdevil.mooc.Jsonbinder.Login_Course;
import com.imdevil.mooc.Jsonbinder.My;
import com.imdevil.mooc.R;

import java.util.List;

/**
 * Created by imdevil on 2017/2/13 0013.
 */
public class My_Course extends LinearLayout {

    private ImageView img;
    private TextView name;
    private TextView teacher;
    private TextView choose;
    private ProgressBar progress;

    public My_Course(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.my_course,this,true);
        img = (ImageView) findViewById(R.id.my_course_img);
        name = (TextView) findViewById(R.id.my_course_name);
        teacher = (TextView) findViewById(R.id.my_course_teacher);
        choose = (TextView) findViewById(R.id.my_course_choose);
        progress = (ProgressBar) findViewById(R.id.my_course_progress);
    }

    public ImageView getImg() {
        return img;
    }

    public TextView getChoose() {
        return choose;
    }

    public TextView getName() {
        return name;
    }

    public TextView getTeacher() {
        return teacher;
    }

    public ProgressBar getProgress() {
        return progress;
    }

    public void setData(Login_Course course){
        Glide.with(getContext()).load(course.getData().getCourse_introduce().get(0).getCourse_name_pic()).into(getImg());
        getName().setText(course.getData().getCourse_introduce().get(0).getCourse_name_name());
        getTeacher().setText(course.getData().getCourse_introduce().get(0).getCourse_name_adder());
        getChoose().setText(course.getData().getCourse_introduce().get(0).getCourse_name_class());
        getProgress().setProgress((int) course.getData().getCourse_progress());
    }
}
