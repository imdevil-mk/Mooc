package com.imdevil.mooc.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imdevil.mooc.Jsonbinder.Login_Course;
import com.imdevil.mooc.R;

/**
 * Created by Alan on 2017/2/20.
 */


public class Dialog extends LinearLayout {

    private TextView name;
    private TextView teacher;
    private TextView choose;
    private TextView progress_text;
    private ProgressBar progress;
    private ImageView imageView;

    public Dialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.dialog,this,true);
        name = (TextView) findViewById(R.id.my_course);
        choose = (TextView) findViewById(R.id.my_course_choose);
        progress_text = (TextView) findViewById(R.id.my_course_progress_text);
        progress = (ProgressBar) findViewById(R.id.my_course_progress);
        imageView = (ImageView) findViewById(R.id.image);
    }


    public TextView getChoose() {
        return choose;
    }

    public TextView getName() {
        return name;
    }
    public ImageView getImageView(){return imageView;}

    public ProgressBar getProgress() {
        return progress;
    }

    public TextView getProgress_text() {
        return progress_text;
    }

    public void setData(Login_Course course){
        getName().setText(course.getData().getCourse_introduce().get(0).getCourse_name_name());
        getChoose().setText("共"+course.getData().getCourse_introduce().get(0).getCourse_name_class()+"节");
        getProgress_text().setText("已学"+course.getData().getCourse_progress()+"%");
        getProgress().setProgress((int) course.getData().getCourse_progress());
        if (course.getData().getCourse_progress()>=90){
            getImageView().setImageResource(R.drawable.good);

        }else if (course.getData().getCourse_progress()<=10){
            getImageView().setImageResource(R.drawable.bad);
        }else{
            getImageView().setVisibility(getImageView().INVISIBLE);
        }
        //getImageView().setImageResource(R.drawable.good);
    }
    public void setName(String name){
        getName().setText(name);

    }
}
