package com.imdevil.mooc.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imdevil.mooc.R;

import org.w3c.dom.Text;

/**
 * Created by imdevil on 2017/2/13 0013.
 */
public class Self extends LinearLayout {

    private ImageView self_img;
    private TextView self_name;
    private TextView self_introduce;

    public Self(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.self,null);
        self_img = (ImageView) findViewById(R.id.self_img);
        self_name = (TextView) findViewById(R.id.self_name);
        self_introduce = (TextView) findViewById(R.id.self_introduce);
    }

    public ImageView getSelf_img() {
        return self_img;
    }

    public TextView getSelf_introduce() {
        return self_introduce;
    }

    public TextView getSelf_name() {
        return self_name;
    }
}
