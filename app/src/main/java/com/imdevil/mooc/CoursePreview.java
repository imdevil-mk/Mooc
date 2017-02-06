package com.imdevil.mooc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by imdevil on 2017/2/5 0005.
 */
public class CoursePreview extends LinearLayout{

    private TextView course_preview_course;
    private ImageTextButton course_preview_button_1;
    private ImageTextButton course_preview_button_2;
    private ImageTextButton course_preview_button_3;
    private ImageTextButton course_preview_button_4;


    public CoursePreview(Context context) {
        super(context,null);
    }

    public CoursePreview(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(getContext()).inflate(R.layout.course_preview,this,true);
        this.course_preview_course = (TextView) findViewById(R.id.course_preview_course);
        this.course_preview_button_1 = (ImageTextButton) findViewById(R.id.course_preview_button_1);
        this.course_preview_button_2 = (ImageTextButton) findViewById(R.id.course_preview_button_2);
        this.course_preview_button_3 = (ImageTextButton) findViewById(R.id.course_preview_button_3);
        this.course_preview_button_4 = (ImageTextButton) findViewById(R.id.course_preview_button_4);
    }

    public void setCourse_preview_course(String course){
        course_preview_course.setText(course);
    }

    public void setCourse_preview_button_1(int resID,String text){
        course_preview_button_1.setImageRes(resID);
        course_preview_button_1.setTextText(text);
    }
    public void setCourse_preview_button_2(int resID,String text){
        course_preview_button_2.setImageRes(resID);
        course_preview_button_2.setTextText(text);
    }
    public void setCourse_preview_button_3(int resID,String text){
        course_preview_button_3.setImageRes(resID);
        course_preview_button_3.setTextText(text);
    }
    public void setCourse_preview_button_4(int resID,String text){
        course_preview_button_4.setImageRes(resID);
        course_preview_button_4.setTextText(text);
    }

    public ImageTextButton getCourse_preview_button_1(){
        return course_preview_button_1;
    }

    public ImageTextButton getCourse_preview_button_2(){
        return course_preview_button_2;
    }

    public ImageTextButton getCourse_preview_button_3(){
        return course_preview_button_3;
    }

    public ImageTextButton getCourse_preview_button_4(){
        return course_preview_button_4;
    }
}
