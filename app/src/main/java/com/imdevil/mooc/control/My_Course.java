package com.imdevil.mooc.control;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private TextView choose;
    private TextView process;

    public My_Course(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.my_course,this,true);
        img = (ImageView) findViewById(R.id.my_course_img);
        name = (TextView) findViewById(R.id.my_course_name);
        choose = (TextView) findViewById(R.id.my_course_choose);
        process = (TextView) findViewById(R.id.my_course_process);
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

    public TextView getProcess() {
        return process;
    }

    public void setData(Login_Course course){
        Glide.with(getContext()).load(course.getData().getCourse_introduce().get(0).getCourse_name_pic()).into(getImg());
        getChoose().setText(course.getData().getCourse_introduce().get(0).getCourse_name_class()+"");
        getName().setText(course.getData().getCourse_introduce().get(0).getCourse_name_name());
    }
}
