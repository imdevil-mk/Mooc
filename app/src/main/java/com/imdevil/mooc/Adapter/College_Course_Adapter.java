package com.imdevil.mooc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imdevil.mooc.JavaBean.CollegeCourseItem;
import com.imdevil.mooc.R;

import java.util.List;

/**
 * Created by imdevil on 2017/2/11 0011.
 */
public class College_Course_Adapter extends ArrayAdapter<CollegeCourseItem>{

    private  int resourseId;
    private View view;

    public College_Course_Adapter(Context context,int resourceId, List<CollegeCourseItem> objects) {
        super(context,resourceId, objects);
        this.resourseId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollegeCourseItem item = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.college_course_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) view.findViewById(R.id.college_course_item_img);
            viewHolder.name = (TextView) view.findViewById(R.id.college_course_item_name);
            viewHolder.choose = (TextView) view.findViewById(R.id.college_course_item_choose);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(getContext()).load(item.getImg_url()).into(viewHolder.img);
        viewHolder.name.setText(item.getName());
        viewHolder.choose.setText(""+item.getChoose());
        return  view;
    }

    class ViewHolder{
        ImageView img;
        TextView name;
        TextView choose;
    }
}
