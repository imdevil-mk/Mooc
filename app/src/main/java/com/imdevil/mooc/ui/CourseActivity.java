package com.imdevil.mooc.ui;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.imdevil.mooc.R;

public class CourseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Fragment fragment = new CollegeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.college_frameLayout,fragment).commit();

    }
}
