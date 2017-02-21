package com.imdevil.mooc.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.imdevil.mooc.R;

/**
 * Created by imdevil on 2017/2/21 0021.
 */
public class DialogContainer extends LinearLayout {

    private LinearLayout linearLayout;

    public DialogContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_container,null);
        linearLayout = (LinearLayout) findViewById(R.id.dialog_container);
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}
