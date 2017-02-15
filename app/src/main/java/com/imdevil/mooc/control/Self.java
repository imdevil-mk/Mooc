package com.imdevil.mooc.control;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imdevil.mooc.Jsonbinder.My;
import com.imdevil.mooc.R;
import com.imdevil.mooc.Utils.GlideCircleTransform;
import com.imdevil.mooc.ui.LoginActivity;

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
        LayoutInflater.from(getContext()).inflate(R.layout.self,this,true);
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

    public void setData(My my){
        getSelf_name().setText(my.getData().getUserinfo().getUsername());
        getSelf_introduce().setText(my.getData().getUserinfo().getIntro());
        Glide.with(getContext()).load(my.getData().getUserinfo().getPic()).transform(new GlideCircleTransform(getContext())).into(getSelf_img());
        getSelf_img().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LoginActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}
