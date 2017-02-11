package com.imdevil.mooc.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imdevil.mooc.JavaBean.ListViewCollege;
import com.imdevil.mooc.R;

import java.util.List;

/**
 * Created by imdevil on 2017/2/11 0011.
 */
public class College_Adapter extends ArrayAdapter<ListViewCollege> {

    private int resourseId;
    private View view;

    public College_Adapter(Context context, int resourceId, List<ListViewCollege> colleges) {
        super(context, resourceId, colleges);
        this.resourseId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewCollege college = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourseId,null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) view.findViewById(R.id.college_item_img);
            viewHolder.text = (TextView) view.findViewById(R.id.college_item_text);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.img.setImageResource(college.getImgRes());
        viewHolder.text.setText(college.getCollege_name());
       return view;
    }

    class ViewHolder{
        ImageView img;
        TextView text;
    }
}
