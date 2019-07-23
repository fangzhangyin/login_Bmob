package db;

import android.support.annotation.ArrayRes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import entity.admin;

import static entity.admin.*;

public class ADUS {

    private static admin admin;
    private int s;
    private static ArrayList<admin> adminlist;

    public static ArrayList<admin> searchbyroot(String root) {
        adminlist=new ArrayList<admin>();
        BmobQuery<admin> query = new BmobQuery<admin>();
        query.addWhereEqualTo("root", root);
        query.findObjects(new FindListener<admin>() {
            @Override
            public void done(List<admin> list, BmobException e) {
                // ArrayList<admin> adminlist=new ArrayList<admin>();
                if (e == null) {
                    System.out.println(list.size());
                    Iterator<admin> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        admin ad = (admin) iterator.next();
                        adminlist.add(ad);
                    }
                } else {
                    System.out.println("error");
                }
            }
        });
        return adminlist;
    }

    public static admin searchbyname(String name) {
        BmobQuery<admin> query = new BmobQuery<admin>();
        query.addWhereEqualTo("adname", name);
        query.findObjects(new FindListener<admin>() {
            @Override
            public void done(List<admin> list, BmobException e) {
                if (list.size() == 1) {
                    if (e == null) {
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            admin ad = (admin) iterator.next();
                            admin.setAdname(ad.getAdname());
                            admin.setEmail(ad.getEmail());
                            admin.setPassword(ad.getPassword());
                            admin.setId(ad.getId());
                        }
                    } else {

                    }
                }
            }
        });
        return admin;
    }

    public static admin searchbyemail(String email) {
        BmobQuery<admin> query = new BmobQuery<admin>();
        query.addWhereEqualTo("email", email);
        query.findObjects(new FindListener<admin>() {
            @Override
            public void done(List<admin> list, BmobException e) {
                if (list.size() == 1) {
                    if (e == null) {
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext()) {
                            admin ad = (admin) iterator.next();
                            admin.setAdname(ad.getAdname());
                            admin.setEmail(ad.getEmail());
                            admin.setPassword(ad.getPassword());
                            admin.setId(ad.getId());
                        }
                    } else {

                    }
                }
            }
        });
        return admin;
    }

    public int edAdmin(admin admin) {
        admin ad = new admin();
        ad.setPassword(admin.getPassword());
        ad.setEmail(admin.getEmail());
        ad.setAdname(admin.getAdname());
        ad.update(admin.getId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    s = 1;
                } else {
                    s = 0;
                }
            }
        });
        return s;
    }

    public int deAdmin(admin admin) {
        admin ad = new admin();
        ad.setId(admin.getId());
        ad.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    s = 1;
                } else {
                    s = 0;
                }
            }
        });
        return s;
    }

    public int adAdmin(admin admin) {
        admin.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    s = 1;
                } else if (e.getErrorCode() == 401) {
                    s = -1;
                } else {
                    s = 0;
                }
            }
        });
        return s;
    }
}
