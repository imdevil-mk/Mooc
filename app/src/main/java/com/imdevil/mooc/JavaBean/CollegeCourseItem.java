package com.imdevil.mooc.JavaBean;

/**
 * Created by imdevil on 2017/2/11 0011.
 */
public class CollegeCourseItem {
    private String img_url;
    private String name;
    private String teacher;
    private int choose;

    public CollegeCourseItem(String img_url,String name,String teacher,int choose){
        this.img_url = img_url;
        this.name = name;
        this.teacher = teacher;
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

    public String getTeacher() {
        return teacher;
    }
}
