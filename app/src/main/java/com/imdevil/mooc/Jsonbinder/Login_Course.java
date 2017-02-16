package com.imdevil.mooc.Jsonbinder;

import java.util.List;

/**
 * Created by imdevil on 2017/2/16 0016.
 */
public class Login_Course {
    /**
     * code : 424
     * data : {"chapter_progress":[{"chapter_progress_chapter":"1","chapter_progress_course":"1","chapter_progress_current_time":"10.78","chapter_progress_id":"1","chapter_progress_state":"60","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 20:54:47"},{"chapter_progress_chapter":"2","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"2","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 21:03:41"},{"chapter_progress_chapter":"3","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"3","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:22:46"},{"chapter_progress_chapter":"4","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"4","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:22:43"},{"chapter_progress_chapter":"12","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"5","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:59:26"},{"chapter_progress_chapter":"13","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"6","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:59:29"},{"chapter_progress_chapter":"18","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"11","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-16 01:37:19"}],"course_introduce":[{"course_name_adder":"admin000","course_name_class":"8","course_name_id":"1","course_name_intro":"这是高等数学的课程介绍，这句话是写在数据库里面的","course_name_name":"高等数学","course_name_pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ec9cf1513.png","course_name_time":"2017-01-26 20:33:33"}],"course_progress":5}
     * message : 课程介绍信息获取成功，学生该课程学习进度获取成功
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
        /**
         * chapter_progress : [{"chapter_progress_chapter":"1","chapter_progress_course":"1","chapter_progress_current_time":"10.78","chapter_progress_id":"1","chapter_progress_state":"60","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 20:54:47"},{"chapter_progress_chapter":"2","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"2","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 21:03:41"},{"chapter_progress_chapter":"3","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"3","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:22:46"},{"chapter_progress_chapter":"4","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"4","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:22:43"},{"chapter_progress_chapter":"12","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"5","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:59:26"},{"chapter_progress_chapter":"13","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"6","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-14 22:59:29"},{"chapter_progress_chapter":"18","chapter_progress_course":"1","chapter_progress_current_time":"0","chapter_progress_id":"11","chapter_progress_state":"0","chapter_progress_student":"9","chapter_progress_time":"2017-02-16 01:37:19"}]
         * course_introduce : [{"course_name_adder":"admin000","course_name_class":"8","course_name_id":"1","course_name_intro":"这是高等数学的课程介绍，这句话是写在数据库里面的","course_name_name":"高等数学","course_name_pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ec9cf1513.png","course_name_time":"2017-01-26 20:33:33"}]
         * course_progress : 5
         */

        private float course_progress;
        private List<ChapterProgressBean> chapter_progress;
        private List<CourseIntroduceBean> course_introduce;

        public float getCourse_progress() {
            return course_progress;
        }

        public void setCourse_progress(float course_progress) {
            this.course_progress = course_progress;
        }

        public List<ChapterProgressBean> getChapter_progress() {
            return chapter_progress;
        }

        public void setChapter_progress(List<ChapterProgressBean> chapter_progress) {
            this.chapter_progress = chapter_progress;
        }

        public List<CourseIntroduceBean> getCourse_introduce() {
            return course_introduce;
        }

        public void setCourse_introduce(List<CourseIntroduceBean> course_introduce) {
            this.course_introduce = course_introduce;
        }

        public static class ChapterProgressBean {
            /**
             * chapter_progress_chapter : 1
             * chapter_progress_course : 1
             * chapter_progress_current_time : 10.78
             * chapter_progress_id : 1
             * chapter_progress_state : 60
             * chapter_progress_student : 9
             * chapter_progress_time : 2017-02-14 20:54:47
             */

            private String chapter_progress_chapter;
            private String chapter_progress_course;
            private String chapter_progress_current_time;
            private String chapter_progress_id;
            private String chapter_progress_state;
            private String chapter_progress_student;
            private String chapter_progress_time;

            public String getChapter_progress_chapter() {
                return chapter_progress_chapter;
            }

            public void setChapter_progress_chapter(String chapter_progress_chapter) {
                this.chapter_progress_chapter = chapter_progress_chapter;
            }

            public String getChapter_progress_course() {
                return chapter_progress_course;
            }

            public void setChapter_progress_course(String chapter_progress_course) {
                this.chapter_progress_course = chapter_progress_course;
            }

            public String getChapter_progress_current_time() {
                return chapter_progress_current_time;
            }

            public void setChapter_progress_current_time(String chapter_progress_current_time) {
                this.chapter_progress_current_time = chapter_progress_current_time;
            }

            public String getChapter_progress_id() {
                return chapter_progress_id;
            }

            public void setChapter_progress_id(String chapter_progress_id) {
                this.chapter_progress_id = chapter_progress_id;
            }

            public String getChapter_progress_state() {
                return chapter_progress_state;
            }

            public void setChapter_progress_state(String chapter_progress_state) {
                this.chapter_progress_state = chapter_progress_state;
            }

            public String getChapter_progress_student() {
                return chapter_progress_student;
            }

            public void setChapter_progress_student(String chapter_progress_student) {
                this.chapter_progress_student = chapter_progress_student;
            }

            public String getChapter_progress_time() {
                return chapter_progress_time;
            }

            public void setChapter_progress_time(String chapter_progress_time) {
                this.chapter_progress_time = chapter_progress_time;
            }
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
