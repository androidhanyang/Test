package com.hy.myfuck.bean;

import java.util.List;

/**
 * Created by user on 2016/8/3.
 */
public class SheQuNeiBu {


    public String content;
    public String icon;
    public String name;
    public String next_url;
    public int hot_num;//热度

    public String weburl;
    public String item_type;
    public List<Medias> thumbnail_medias;

    public class Medias {
        public String url;

    }


}
