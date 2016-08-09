package com.hy.myfuck.json;

import android.util.Log;

import com.google.gson.JsonArray;
import com.hy.myfuck.bean.BeiJing;
import com.hy.myfuck.bean.HotBean;
import com.hy.myfuck.bean.News;
import com.hy.myfuck.bean.NewsTab;
import com.hy.myfuck.bean.SheQu;
import com.hy.myfuck.bean.SheQuNeiBu;
import com.hy.myfuck.bean.WanLe;
import com.hy.myfuck.bean.WanLeShang;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/7/27.
 */
public class NetJson {

    public static List<BeiJing> beiJingNews(String json) {
        List<BeiJing> list = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("articles");

            Log.e("123", "" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                BeiJing beiJing = new BeiJing();
                beiJing.auther_name = jsonObject2.getString("auther_name");
                beiJing.title = jsonObject2.getString("title");
                beiJing.weburl = jsonObject2.getString("weburl");
                if (jsonObject2.isNull("thumbnail_mpic")) {
                    list.add(beiJing);
                    continue;
                }
                beiJing.thumbnail_mpic = jsonObject2.getString("thumbnail_mpic");
                list.add(beiJing);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<WanLe> wanLeNews(String json) {
        List<WanLe> list1 = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("columns");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                JSONArray jsonArray1 = jsonObject2.getJSONArray("items");

                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                    WanLe wanLe = new WanLe();
                    JSONObject jsonObject4 = jsonObject3.getJSONObject("pic");
                    JSONObject jsonObject5 = jsonObject3.getJSONObject("article");
                    wanLe.share_content = jsonObject3.getString("share_content");
                    wanLe.title = jsonObject3.getString("title");
                    wanLe.url = jsonObject4.getString("url");
                    wanLe.weburl = jsonObject5.getString("weburl");
                    list1.add(wanLe);
                }
            }
            return list1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<WanLeShang> wanLeShangNews(String json) {
        List<WanLeShang> list2 = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("display");

            for (int i = 0; i < jsonArray2.length(); i++) {
                JSONObject jsonObject6 = jsonArray2.getJSONObject(i);
                WanLeShang wanLeShang = new WanLeShang();
                JSONObject jsonObject7 = jsonObject6.getJSONObject("pic");
//                JSONObject jsonObject8 = jsonObject6.getJSONObject("web");
                wanLeShang.m_url = jsonObject7.getString("m_url");
//                wanLeShang.url = jsonObject8.getString("url");
                list2.add(wanLeShang);
            }
            return list2;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("TAG", "wanLeShangNews: 解析出错");
        }
        return null;
    }

    public static List<WanLeShang> wanLeShangNews1(String json) {
        List<WanLeShang> list3 = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("display");

            for (int i = 0; i < 2; i++) {
                JSONObject jsonObject6 = jsonArray2.getJSONObject(i);
                WanLeShang wanLeShang = new WanLeShang();
//                JSONObject jsonObject7 = jsonObject6.getJSONObject("pic");
                JSONObject jsonObject8 = jsonObject6.getJSONObject("web");
//                wanLeShang.m_url = jsonObject7.getString("m_url");
                wanLeShang.url = jsonObject8.getString("url");
                Log.i("TAG", "wanLeShangNews1: " + wanLeShang.url);
                list3.add(wanLeShang);
            }
            return list3;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("TAG", "wanLeShangNews: 解析出错");
        }
        return null;
    }

    public static List<HotBean> hotBeen(String json) {
        List<HotBean> list4 = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("articles");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                HotBean hotBean = new HotBean();
                hotBean.title = jsonObject2.getString("title");
                hotBean.weburl = jsonObject2.getString("weburl");
                JSONObject jsonObject4 = jsonObject2.getJSONObject("special_info");
                if (jsonObject4.isNull("item_type")) {
                    hotBean.item_type = null;
                } else {
                    hotBean.item_type = jsonObject4.getString("item_type");
                }
                JSONArray jsonArray1 = jsonObject2.getJSONArray("thumbnail_medias");
                hotBean.thumbnail_medias = new ArrayList<>();
                if (jsonArray1 != null) {
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                        HotBean.Medias medias = hotBean.new Medias();
                        medias.url = jsonObject3.getString("url");
                        hotBean.thumbnail_medias.add(medias);
                    }
                }
                list4.add(hotBean);
            }
            return list4;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list4;
    }

    public static List<SheQu> sheQuNews(String json) {
        List<SheQu> list = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.optJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.optJSONObject(i);
                SheQu sheQu = new SheQu();
                sheQu.api_url = jsonObject2.optString("api_url");
                sheQu.pic = jsonObject2.optString("pic");
                sheQu.stitle = jsonObject2.optString("stitle");
                sheQu.title = jsonObject2.optString("title");
                list.add(sheQu);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static List<SheQuNeiBu> sheQuNeiBus(String json) {
//        List<SheQuNeiBu> list = new ArrayList<>();
//
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
//            SheQuNeiBu sheQuNeiBu = new SheQuNeiBu();
//
//            JSONObject jsonObject20 = jsonObject1.getJSONObject("discussion_info");
//            sheQuNeiBu.title = jsonObject20.getString("title");
//            JSONObject jsonObject30 = jsonObject1.getJSONObject("info");
//            sheQuNeiBu.next_url = jsonObject30.getString("next_url");
//            JSONArray jsonArray = jsonObject1.getJSONArray("posts");
//            sheQuNeiBu.posts = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//                SheQuNeiBu.PostsItem postsItem = sheQuNeiBu.new PostsItem();
//
//                JSONObject jsonObject4 = jsonObject2.getJSONObject("auther");
//                postsItem.content = jsonObject2.getString("content");
//                postsItem.weburl = jsonObject2.getString("weburl");
//                postsItem.name = jsonObject4.getString("name");
//                postsItem.icon = jsonObject4.getString("icon");
//                postsItem.hot_num = jsonObject2.getInt("hot_num");
//                JSONArray jsonArray1 = jsonObject2.getJSONArray("thumbnail_medias");
//                if (jsonArray1 != null) {
//                    postsItem.thumbnail_medias = new ArrayList<>();
//                    for (int j = 0; j < jsonArray1.length(); j++) {
//                        JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
//                        SheQuNeiBu.Medias medias = sheQuNeiBu.new Medias();
//                        medias.url = jsonObject3.getString("url");
//                        postsItem.thumbnail_medias.add(medias);
//                    }
//                }
//                sheQuNeiBu.posts.add(postsItem);
////                if (jsonObject2.has("special_info")) {
////                    JSONObject jsonObject5 = jsonObject2.getJSONObject("special_info");
////                    sheQuNeiBu.item_type = jsonObject5.getString("item_type");
////                }
//            }
//                list.add(sheQuNeiBu);
//            return list;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    public static List<SheQuNeiBu> sheQuNeiBus(String json) {
        List<SheQuNeiBu> list = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("posts");
            for (int i = 0; i < jsonArray.length(); i++) {
                SheQuNeiBu sheQuNeiBu = new SheQuNeiBu();
                JSONObject jsonObject30 = jsonObject1.getJSONObject("info");
                sheQuNeiBu.next_url = jsonObject30.getString("next_url");
                JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                JSONObject jsonObject4 = jsonObject3.getJSONObject("auther");
                sheQuNeiBu.name = jsonObject4.getString("name");
                sheQuNeiBu.icon = jsonObject4.getString("icon");

                sheQuNeiBu.content = jsonObject3.getString("content");
                sheQuNeiBu.hot_num = jsonObject3.getInt("hot_num");
                sheQuNeiBu.weburl = jsonObject3.getString("weburl");
                JSONArray jsonArray1 = jsonObject3.getJSONArray("thumbnail_medias");
                sheQuNeiBu.thumbnail_medias = new ArrayList<>();
                if (jsonArray1 != null) {
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject5 = jsonArray1.getJSONObject(j);
                        SheQuNeiBu.Medias medias = sheQuNeiBu.new Medias();
                        medias.url = jsonObject5.getString("url");
                        sheQuNeiBu.thumbnail_medias.add(medias);
                    }
                }
                list.add(sheQuNeiBu);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<NewsTab> newsTabs(String json) {
        List<NewsTab> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObject1.getJSONArray("datas");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                NewsTab newsTab = new NewsTab();
                newsTab.title = jsonObject2.getString("title");
                newsTab.list_icon = jsonObject2.getString("list_icon");
                JSONArray jsonArray1 = jsonObject2.getJSONArray("sons");
                for (int j = 0; j < jsonArray1.length(); j++) {
                    JSONObject jsonObject3 = jsonArray1.getJSONObject(j);
                    if (jsonObject3.isNull("api_url")) continue;
                    newsTab.api_url = jsonObject3.getString("api_url");
                    newsTab.block_color = jsonObject3.getString("block_color");

                    newsTab.titleNews = jsonObject3.getString("title");
                    if (jsonObject3.isNull("pic")) {
                        list.add(newsTab);
                        continue;
                    }
                    newsTab.pic = jsonObject3.getString("pic");
                    list.add(newsTab);
                }
                list.add(newsTab);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}