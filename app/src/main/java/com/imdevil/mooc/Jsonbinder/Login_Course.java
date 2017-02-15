package com.imdevil.mooc.Jsonbinder;

import java.util.List;

/**
 * Created by imdevil on 2017/2/15 0015.
 */
public class Login_Course {

    /**
     * code : 423
     * data : {"course_introduce":[{"course_name_adder":"admin000","course_name_class":"8","course_name_id":"1","course_name_intro":"这是高等数学的课程介绍，这句话是写在数据库里面的","course_name_name":"高等数学","course_name_pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ec9cf1513.png","course_name_time":"2017-01-26 20:33:33"}]}
     * message : 课程介绍信息获取成功，未提供学生ID因而不反馈学生的学习进度等信息
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<CourseIntroduceBean> course_introduce;

        public List<CourseIntroduceBean> getCourse_introduce() {
            return course_introduce;
        }

        public void setCourse_introduce(List<CourseIntroduceBean> course_introduce) {
            this.course_introduce = course_introduce;
        }

        public static class CourseIntroduceBean {
            /**
             * course_name_adder : admin000
             * course_name_class : 8
             * course_name_id : 1
             * course_name_intro : 这是高等数学的课程介绍，这句话是写在数据库里面的
             * course_name_name : 高等数学
             * course_name_pic : http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ec9cf1513.png
             * course_name_time : 2017-01-26 20:33:33
             */

            private String course_name_adder;
            private String course_name_class;
            private String course_name_id;
            private String course_name_intro;
            private String course_name_name;
            private String course_name_pic;
            private String course_name_time;

            public String getCourse_name_adder() {
                return course_name_adder;
            }

            public void setCourse_name_adder(String course_name_adder) {
                this.course_name_adder = course_name_adder;
            }

            public String getCourse_name_class() {
                return course_name_class;
            }

            public void setCourse_name_class(String course_name_class) {
                this.course_name_class = course_name_class;
            }

            public String getCourse_name_id() {
                return course_name_id;
            }

            public void setCourse_name_id(String course_name_id) {
                this.course_name_id = course_name_id;
            }

            public String getCourse_name_intro() {
                return course_name_intro;
            }

            public void setCourse_name_intro(String course_name_intro) {
                this.course_name_intro = course_name_intro;
            }

            public String getCourse_name_name() {
                return course_name_name;
            }

            public void setCourse_name_name(String course_name_name) {
                this.course_name_name = course_name_name;
            }

            public String getCourse_name_pic() {
                return course_name_pic;
            }

            public void setCourse_name_pic(String course_name_pic) {
                this.course_name_pic = course_name_pic;
            }

            public String getCourse_name_time() {
                return course_name_time;
            }

            public void setCourse_name_time(String course_name_time) {
                this.course_name_time = course_name_time;
            }
        }
    }
}
