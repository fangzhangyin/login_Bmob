package entity;

import cn.bmob.v3.BmobObject;

public class admin extends BmobObject {
    private String adname;
    private String password;

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
