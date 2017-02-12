package com.imdevil.mooc.Jsonbinder;

import java.util.List;

/**
 * Created by Alan on 2017/2/12.
 */

public class CourseChapter {

    /**
     * code : 411
     * message : 课程章节列表细心获取成功
     * data : [{"chapter_name":"第一章 测试数据1","section":[{"section_id":"25","name":"1.1 红楼梦1.1","video_url":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e8740dbaa.mp4","ppt_url":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e874125e3.ppt"}]},{"chapter_name":"第二章 红楼梦2","section":[{"section_id":"26","name":"2.1 红楼梦测试","video_url":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e8d1c3754.mp4","ppt_url":"http://120.27.104.19:3002/Hubu/Public/"}]}]
     */

    private int code;
    private String message;
    /**
     * chapter_name : 第一章 测试数据1
     * section : [{"section_id":"25","name":"1.1 红楼梦1.1","video_url":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e8740dbaa.mp4","ppt_url":"http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e874125e3.ppt"}]
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
        private String chapter_name;
        /**
         * section_id : 25
         * name : 1.1 红楼梦1.1
         * video_url : http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e8740dbaa.mp4
         * ppt_url : http://120.27.104.19:3002/Hubu/Public/Uploads/2017-01-24/5886e874125e3.ppt
         */

        private List<SectionEntity> section;

        public void setChapter_name(String chapter_name) {
            this.chapter_name = chapter_name;
        }

        public void setSection(List<SectionEntity> section) {
            this.section = section;
        }

        public String getChapter_name() {
            return chapter_name;
        }

        public List<SectionEntity> getSection() {
            return section;
        }

        public static class SectionEntity {
            private String section_id;
            private String name;
            private String video_url;
            private String ppt_url;

            public void setSection_id(String section_id) {
                this.section_id = section_id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public void setPpt_url(String ppt_url) {
                this.ppt_url = ppt_url;
            }

            public String getSection_id() {
                return section_id;
            }

            public String getName() {
                return name;
            }

            public String getVideo_url() {
                return video_url;
            }

            public String getPpt_url() {
                return ppt_url;
            }
        }
    }
}
