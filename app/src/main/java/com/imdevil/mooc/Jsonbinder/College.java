package com.imdevil.mooc.Jsonbinder;

import java.util.List;

/**
 * Created by Alan on 2017/2/9.
 */

public class College {

    /**
     * code : 400
     * message : 课程类别数据获取成功
     * data : [{"college_id":"1","name":"计算机","icon":"","type":"1"},{"college_id":"2","name":"经济管理","icon":"","type":"2"}]
     */

    private int code;
    private String message;
    /**
     * college_id : 1
     * name : 计算机
     * icon :
     * type : 1
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
        private String college_id;
        private String name;
        private String icon;
        private String type;

        public void setCollege_id(String college_id) {
            this.college_id = college_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCollege_id() {
            return college_id;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public String getType() {
            return type;
        }
    }
}
