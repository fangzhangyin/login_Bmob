package entity;

import java.io.File;

import cn.bmob.v3.BmobObject;

public class news extends BmobObject {
    private String adname;
    private String news;
    private String image;
    private String root;
    private String face;

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String  image) {
        this.image = image;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
