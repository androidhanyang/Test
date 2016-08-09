package com.hy.myfuck.bean;

import java.util.List;

/**
 * Created by user on 2016/8/1.
 */
public class HotBean {
    public String auther_name;
    public String date;
    public String title;
    public String weburl;
    public String item_type;
    public List<Medias> thumbnail_medias;

    public class Medias {
        public String url;
    }


}
