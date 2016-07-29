package com.example.zj.test.Entity;

/**
 * Created by zj on 2016/7/5---10:16.
 */
public class Item {
    /**
     *  item image
     * author:  zj
     * created at @time 2016/7/5 10:16
     */
    private int img;
    //标题
    private String title;
    //描述
    private String description;
    //时间
    private String time;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Item [img=" + img + ", title=" + title + ", description="
                + description + ", time=" + time + "]";
    }
}
