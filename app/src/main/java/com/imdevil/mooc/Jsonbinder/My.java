package com.imdevil.mooc.Jsonbinder;

import java.util.List;

/**
 * Created by imdevil on 2017/2/16 0016.
 */
public class My {
    /**
     * code : 418
     * message : 登陆成功，学生用户个人信息以及已选课程的ID信息获取成功
     * data : {"userinfo":{"student_id":"9","email":"1784225410@qq.com","username":"1784225410@qq.com","sex":"1","tel":"17671846116","qq":"1784225410","addr":"湖大二期-D-426","pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ed2127cb3.jpg","intro":"这是我的个人介绍！！！5","verify":"1"},"mycourse":["2","17","4","1","18"]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userinfo : {"student_id":"9","email":"1784225410@qq.com","username":"1784225410@qq.com","sex":"1","tel":"17671846116","qq":"1784225410","addr":"湖大二期-D-426","pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ed2127cb3.jpg","intro":"这是我的个人介绍！！！5","verify":"1"}
         * mycourse : ["2","17","4","1","18"]
         */

        private UserinfoBean userinfo;
        private List<String> mycourse;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public List<String> getMycourse() {
            return mycourse;
        }

        public void setMycourse(List<String> mycourse) {
            this.mycourse = mycourse;
        }

        public static class UserinfoBean {
            /**
             * student_id : 9
             * email : 1784225410@qq.com
             * username : 1784225410@qq.com
             * sex : 1
             * tel : 17671846116
             * qq : 1784225410
             * addr : 湖大二期-D-426
             * pic : http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ed2127cb3.jpg
             * intro : 这是我的个人介绍！！！5
             * verify : 1
             */

            private String student_id;
            private String email;
            private String username;
            private String sex;
            private String tel;
            private String qq;
            private String addr;
            private String pic;
            private String intro;
            private String verify;

            public String getStudent_id() {
                return student_id;
            }

            public void setStudent_id(String student_id) {
                this.student_id = student_id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getVerify() {
                return verify;
            }

            public void setVerify(String verify) {
                this.verify = verify;
            }
        }
    }
}
