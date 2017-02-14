package com.imdevil.mooc.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imdevil.mooc.R;
import com.imdevil.mooc.control.Login;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private View  rootView;
    private LinearLayout linearLayout;
    private Button button;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public MyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView != null)
        {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent)
            {
                parent.removeView(rootView);
            }
        }else {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my,null);
            linearLayout = (LinearLayout) rootView.findViewById(R.id.my_fragment_ll);

            pref = PreferenceManager.getDefaultSharedPreferences(getContext());
            if(pref.getBoolean("Login",false)){
                //linearLayout.removeAllViews();
            }
            //else{
                button = (Button) rootView.findViewById(R.id.ll_btn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }
                });
           // }
        }

        return  rootView;
    }


}
