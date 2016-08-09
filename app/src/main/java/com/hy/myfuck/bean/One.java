package com.hy.myfuck.bean;



import java.util.List;

/**
 * Created by user on 2016/8/1.
 */
public class One {

    public Data data;
    public class Data{

    public List<DataOne> list;
    }
    public static class DataOne{
        public String title;
        public String promotion_img;
        public Topic topic;
        public Block block_info;
        public Article article;
        public Post post;
    }

    public class Topic{
        public String api_url;
        public String title;
    }
    public class Block{
        public String api_url;
        public String title;
    }
    public class Article{
        public String weburl;
    }

    public class Post{
        public String weburl;
    }
}