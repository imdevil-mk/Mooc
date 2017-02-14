package com.imdevil.mooc.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;

import com.imdevil.mooc.R;

public class CourseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Intent intent = getIntent();
        int  college_id = intent.getIntExtra("college_id",404);
        if(college_id == 404){
            Fragment fragment = new CollegeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.college_frameLayout,fragment).commit();
        }else {
            Fragment fragment = new College_Course_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Target",college_id);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.college_frameLayout,fragment).commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
