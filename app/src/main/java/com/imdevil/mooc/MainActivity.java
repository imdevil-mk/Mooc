package com.imdevil.mooc;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tab;
    private Toolbar toolbar;

    private List<Fragment> list = new ArrayList<>();
    private List<String> theme = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.vp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);

        theme.add("课程");
        theme.add("消息");
        theme.add("我的");

        list.add(new CourseFragment());
        list.add(new NewsFragment());
        list.add(new MyFragment());

        tab.addTab(tab.newTab().setText(theme.get(0)));
        tab.addTab(tab.newTab().setText(theme.get(1)));
        tab.addTab(tab.newTab().setText(theme.get(2)));
        tab.setTabMode(TabLayout.MODE_FIXED);

        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager(),list,theme);
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
