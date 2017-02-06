package com.imdevil.mooc;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment{


    public CourseFragment() {

    }


    private RollPagerView mRollViewPager;
    private ImageTextButton imageTextButton_2;
    private CoursePreview coursePreview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        imageTextButton_2 = (ImageTextButton) view.findViewById(R.id.image_text_button_2);
        coursePreview = (CoursePreview) view.findViewById(R.id.course_preview);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(5000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

        imageTextButton_2.setTextText("文学");
        imageTextButton_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
            }
        });

        coursePreview.setCourse_preview_course("文学");
        coursePreview.setCourse_preview_button_1(R.drawable.img1,"计算机");
        coursePreview.setCourse_preview_button_2(R.drawable.img2,"文学");
        coursePreview.setCourse_preview_button_3(R.drawable.img3,"物理学");
        coursePreview.setCourse_preview_button_4(R.drawable.img4,"艺术学");
        coursePreview.getCourse_preview_button_1().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"11111",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
