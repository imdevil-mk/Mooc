package com.imdevil.mooc.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imdevil.mooc.R;

/**
 * Created by imdevil on 2017/2/9 0009.
 */
public class News extends LinearLayout {

    private TextView news_text_time;
    private TextView news_text_course_name;
    private TextView news_text_content;

    public News(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.news,this,true);
        this.news_text_time = (TextView) findViewById(R.id.news_text_time);
        this.news_text_course_name = (TextView) findViewById(R.id.news_text_course_name);
        this.news_text_content = (TextView) findViewById(R.id.news_text_course_content);
    }

    public TextView getNews_text_time() {
        return news_text_time;
    }

    public TextView getNews_text_course_name() {
        return news_text_course_name;
    }

    public TextView getNews_text_content() {
        return news_text_content;
    }
}
