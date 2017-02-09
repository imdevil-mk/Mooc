package com.imdevil.mooc.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imdevil.mooc.R;

/**
 * Created by imdevil on 2017/2/9 0009.
 */
public class HotCourseButton extends LinearLayout{

    private ImageView imageView;
    private TextView textView;

    public HotCourseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.hot_course_button,this,true);

        this.imageView = (ImageView) findViewById(R.id.course_hot_img);
        this.textView = (TextView) findViewById(R.id.course_hot_text);

    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }
}
