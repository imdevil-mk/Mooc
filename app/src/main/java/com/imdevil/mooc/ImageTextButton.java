package com.imdevil.mooc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by imdevil on 2017/2/5 0005.
 */
public class ImageTextButton extends LinearLayout {

    private ImageView imageView;
    private TextView textView;

    public ImageTextButton(Context context){
        super(context,null);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.image_text_button,this,true);

        this.imageView = (ImageView) findViewById(R.id.button_image);
        this.textView = (TextView) findViewById(R.id.button_text);
    }

    public void setImageRes(int resourceID) {
        this.imageView.setImageResource(resourceID);
    }

    public void setTextText(String text){
        this.textView.setText(text);
    }

}
