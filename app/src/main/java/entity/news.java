package entity;

import java.io.File;

import cn.bmob.v3.BmobObject;

public class news extends BmobObject {
    private String adname;
    private String news;
    private File image;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
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
