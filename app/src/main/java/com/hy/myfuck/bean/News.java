package com.hy.myfuck.bean;

import java.util.List;

/**
 * Created by user on 2016/7/26.
 */
public class News {
    public Data data;

    public class Data {
        public List<DataNews> gallery;
    }

    public static class DataNews {
        public String title;
        public String promotion_img;
        public Article article;
    }

    public static class Article {
        public String weburl;
    }
}
