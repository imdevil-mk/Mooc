package com.imdevil.mooc.JavaBean;

/**
 * Created by imdevil on 2017/2/11 0011.
 */
public class ListViewCollege {

    private  int imgRes;
    private  String college_name;

    public ListViewCollege(int imgRes,String college_name){
        this.imgRes = imgRes;
        this.college_name = college_name;
    }

    public int getImgRes() {
        return imgRes;
    }

    public String getCollege_name() {
        return college_name;
    }
}
