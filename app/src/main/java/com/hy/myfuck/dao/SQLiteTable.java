package com.hy.myfuck.dao;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by user on 2016/8/8.
 */

@Table(name = "zaker")//表名
public class SQLiteTable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //加注了@Column的实体类属性将会映射到sqlite数据库中的字段,
    // @Column注解有name、property、isId、autoGen属性,
    // name属性决定了实体类属性对应的数据库字段名;
    // property属性可以用来添加数据库中字段一级的属性或约束条件例如not null,索引等;
    // isId属性表示该字段是否是主键,默认为false;
    // autoGen则表示如果一个字段为主键,是否自增长,默认为true,所以该字段只有在isId属性为true时有效
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "titleNews")
    private String titleNews;
    @Column(name = "api_url")
    private String api_url;
    @Column(name = "block_color")
    private String block_color;
    @Column(name = "pic")
    private String pic;
    @Column(name = "title")
    private String title;
    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getBlock_color() {
        return block_color;
    }

    public void setBlock_color(String block_color) {
        this.block_color = block_color;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
