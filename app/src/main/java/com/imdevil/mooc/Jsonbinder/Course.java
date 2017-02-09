package com.imdevil.mooc.Jsonbinder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alan on 2017/2/9.
 */

public class Course {

    /**
     * code : 400
     * message : 课程类别数据获取成功
     * data : [{"course_id":"1","name":"高等数学","image":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ec9cf1513.png","description":"这是高等数学的课程介绍，这句话是写在数据库里面的","teacher":"admin000","choose_count":2,"class":12},{"course_id":"14","name":"线性代数","image":"http://120.27.104.19:3002/Hubu/Public/","description":"xd","teacher":"胡","choose_count":0,"class":0}]
     */

    private int code;
    private String message;
    /**
     * course_id : 1
     * name : 高等数学
     * image : http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ec9cf1513.png
     * description : 这是高等数学的课程介绍，这句话是写在数据库里面的
     * teacher : admin000
     * choose_count : 2
     * class : 12
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String course_id;
        private String name;
        private String image;
        private String description;
        private String teacher;
        private int choose_count;
        @SerializedName("class")
        private int classX;

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public void setChoose_count(int choose_count) {
            this.choose_count = choose_count;
        }

        public void setClassX(int classX) {
            this.classX = classX;
        }

        public String getCourse_id() {
            return course_id;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }

        public String getTeacher() {
            return teacher;
        }

        public int getChoose_count() {
            return choose_count;
        }

        public int getClassX() {
            return classX;
        }
    }
}
