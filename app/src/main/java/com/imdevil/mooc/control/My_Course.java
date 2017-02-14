package com.imdevil.mooc.control;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imdevil.mooc.R;

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
}
