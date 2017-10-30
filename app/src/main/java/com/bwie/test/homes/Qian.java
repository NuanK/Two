package com.bwie.test.homes;

/**
 * Created by ASUS on 2017/10/20.
 */

public class Qian {
    private String pic_url;
    private String title;

    public Qian(String pic_url, String title) {
        this.pic_url = pic_url;
        this.title = title;
    }

    public Qian() {
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

    @Override
    public String toString() {
        return "Qian{" +
                "pic_url='" + pic_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
