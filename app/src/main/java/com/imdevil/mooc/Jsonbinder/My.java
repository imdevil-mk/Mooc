package com.imdevil.mooc.Jsonbinder;

import java.util.List;

/**
 * Created by imdevil on 2017/2/15 0015.
 */
public class My {
    /**
     * code : 418
     * data : {"mycourse":["2","17","4","1"],"userinfo":{"addr":"湖大二期-D-426","email":"1784225410@qq.com","intro":"这是我的个人介绍！！！5","pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ed2127cb3.jpg","qq":"1784225410","sex":"1","tel":"17671846116","username":"1784225410@qq.com","verify":"1"}}
     * message : 登陆成功，学生用户个人信息以及已选课程的ID信息获取成功
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
         * mycourse : ["2","17","4","1"]
         * userinfo : {"addr":"湖大二期-D-426","email":"1784225410@qq.com","intro":"这是我的个人介绍！！！5","pic":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ed2127cb3.jpg","qq":"1784225410","sex":"1","tel":"17671846116","username":"1784225410@qq.com","verify":"1"}
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
             * addr : 湖大二期-D-426
             * email : 1784225410@qq.com
             * intro : 这是我的个人介绍！！！5
             * pic : http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-26/5889ed2127cb3.jpg
             * qq : 1784225410
             * sex : 1
             * tel : 17671846116
             * username : 1784225410@qq.com
             * verify : 1
             */

            private String addr;
            private String email;
            private String intro;
            private String pic;
            private String qq;
            private String sex;
            private String tel;
            private String username;
            private String verify;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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
