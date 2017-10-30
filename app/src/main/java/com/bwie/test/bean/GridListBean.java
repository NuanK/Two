package com.bwie.test.bean;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ASUS on 2017/10/24.
 */

public class GridListBean {
    String img;
    String text;

    @Override
    public String toString() {
        return "GridListBean{" +
                "img='" + img + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GridListBean(String img, String text) {

        this.img = img;
        this.text = text;
    }
}
