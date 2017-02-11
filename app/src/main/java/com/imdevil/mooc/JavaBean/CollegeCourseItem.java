package com.imdevil.mooc.JavaBean;

/**
 * Created by imdevil on 2017/2/11 0011.
 */
public class CollegeCourseItem {
    private String img_url;
    private String name;
    private int choose;

    public CollegeCourseItem(String img_url,String name,int choose){
        this.img_url = img_url;
        this.name = name;
        this.choose = choose;
    }

    public int getChoose() {
        return choose;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }
}
