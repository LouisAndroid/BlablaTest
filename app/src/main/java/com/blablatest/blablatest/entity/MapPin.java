package com.blablatest.blablatest.entity;

/**
 * Created by Louis on 2018/1/27.
 */

/**
 * "anonymous": false,
 * "content0": "松山機場",
 * "content1": "",
 * "edit_time": null,
 * "icon": 2,
 * "index_id": 336,
 * "name": "測試專用",
 * "post_time": "Fri, 26 Jan 2018 12:57:46 GMT",
 * "score": 1000,
 * "x_axis": 121.555815897882,
 * "y_axis": 25.0665278041185
 */
public class MapPin {
    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getContent0() {
        return content0;
    }

    public void setContent0(String content0) {
        this.content0 = content0;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIndex_id() {
        return index_id;
    }

    public void setIndex_id(int index_id) {
        this.index_id = index_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getX_axis() {
        return x_axis;
    }

    public void setX_axis(double x_axis) {
        this.x_axis = x_axis;
    }

    public double getY_axis() {
        return y_axis;
    }

    public void setY_axis(double y_axis) {
        this.y_axis = y_axis;
    }

    private boolean anonymous;
    private String content0;
    private String content1;
    private String edit_time;
    private int icon;
    private int index_id;
    private String name;
    private String post_time;
    private int score;
    private double x_axis;
    private double y_axis;

}
