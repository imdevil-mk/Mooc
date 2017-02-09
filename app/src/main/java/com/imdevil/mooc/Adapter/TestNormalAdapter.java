package com.imdevil.mooc.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.imdevil.mooc.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by imdevil on 2017/1/25 0025.
 */
public class TestNormalAdapter extends StaticPagerAdapter {

    private int[] imgs = {
            R.drawable.img1,
           R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
    };


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public int getCount() {
        return imgs.length;
    }
}
