package com.imdevil.mooc.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.imdevil.mooc.R;
import com.imdevil.mooc.control.News;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    private LinearLayout linearLayout;
   private News news;

    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.news_linear_layout);
     news = new News(getContext(),null);
      linearLayout.addView(news);

        return view;
    }

}
