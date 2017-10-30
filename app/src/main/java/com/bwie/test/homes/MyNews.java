package com.bwie.test.homes;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ASUS on 2017/10/17.
 */

public class MyNews {
    private String pic_url;
    private String title;

    @Override
    public String toString() {
        return "MyNews{" +
                "pic_url='" + pic_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MyNews(String pic_url, String title) {

        this.pic_url = pic_url;
        this.title = title;
    }
}
