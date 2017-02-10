package com.imdevil.mooc.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.imdevil.mooc.control.HotCourse;
import com.imdevil.mooc.control.ImageTextButton;
import com.imdevil.mooc.R;
import com.imdevil.mooc.Adapter.TestNormalAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment{


    public CourseFragment() {

    }


    private RollPagerView mRollViewPager;
    private ImageTextButton imageTextButton_1;
    private ImageTextButton imageTextButton_2;
    private ImageTextButton imageTextButton_3;
    private ImageTextButton imageTextButton_4;
    private ImageTextButton imageTextButton_5;
    private ImageTextButton imageTextButton_6;
    private ImageTextButton imageTextButton_7;
    private ImageTextButton imageTextButton_8;
    private HotCourse hotCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        imageTextButton_1 = (ImageTextButton) view.findViewById(R.id.image_text_button_1);
        imageTextButton_2 = (ImageTextButton) view.findViewById(R.id.image_text_button_2);
        imageTextButton_3 = (ImageTextButton) view.findViewById(R.id.image_text_button_3);
        imageTextButton_4 = (ImageTextButton) view.findViewById(R.id.image_text_button_4);
        imageTextButton_5 = (ImageTextButton) view.findViewById(R.id.image_text_button_5);
        imageTextButton_6 = (ImageTextButton) view.findViewById(R.id.image_text_button_6);
        imageTextButton_7 = (ImageTextButton) view.findViewById(R.id.image_text_button_7);
        imageTextButton_8 = (ImageTextButton) view.findViewById(R.id.image_text_button_8);


        hotCourse = (HotCourse) view.findViewById(R.id.hot_course);

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


        setCollege();


        hotCourse.getHotCourseButton_1().getTextView().setText("PHP");
        hotCourse.getHotCourseButton_1().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"PHP",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setCollege() {
        imageTextButton_1.setTextText("计算机");
        imageTextButton_1.setImageRes(R.drawable.it);

        imageTextButton_2.setTextText("经济管理");
        imageTextButton_2.setImageRes(R.drawable.economy);
        imageTextButton_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"1",Toast.LENGTH_SHORT).show();
            }
        });

        imageTextButton_3.setTextText("心理学");
        imageTextButton_3.setImageRes(R.drawable.psycho);

        imageTextButton_4.setTextText("外语");
        imageTextButton_4.setImageRes(R.drawable.language);

        imageTextButton_5.setTextText("文学历史");
        imageTextButton_5.setImageRes(R.drawable.history);

        imageTextButton_6.setTextText("艺术设计");
        imageTextButton_6.setImageRes(R.drawable.art);

        imageTextButton_7.setTextText("工学");
        imageTextButton_7.setImageRes(R.drawable.engineer);

        imageTextButton_8.setTextText("更多");
        imageTextButton_8.setImageRes(R.drawable.more);
    }

}
