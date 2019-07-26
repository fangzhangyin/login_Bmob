package com.example.administrator.login_bmob;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import entity.news;

public class mynew extends AppCompatActivity {

    private ListView myallnews;
    private Context context;
    private List<String> fhead;
    private List<String> fimg;
    private List<String> fname;
    private List<String> fnews;
    private List<String> objectid;
    private String oid;

    private ImageView back;

    private String name;

    private myadapt myadapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynew);
        back=(ImageView)findViewById(R.id.back);
        context=getBaseContext();
        name=lendin.getname();

        fhead = new ArrayList<String>();
        fname = new ArrayList<String>();
        fimg = new ArrayList<String>();
        fnews = new ArrayList<String>();
        objectid=new ArrayList<String>();

        BmobQuery<entity.news> query = new BmobQuery<entity.news>();
        query.addWhereEqualTo("adname", name);
        query.findObjects(new FindListener<entity.news>() {
            @Override
            public void done(final List<entity.news> list, BmobException e) {
                if (e == null) {
                    String name = null;
                    for (news n : list) {
                        fname.add(n.getAdname());
                        fimg.add(n.getImage());
                        fnews.add(n.getNews());
                        fhead.add(n.getFace());
                        objectid.add(n.getObjectId());
                    }
                }
                System.out.println(fname + "\n" + fimg + "\n" + fhead + "\n" + fnews);
                myallnews = (ListView) findViewById(R.id.myallnews);
                myadapt=new myadapt();
                myallnews.setAdapter(myadapt);

                myallnews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        oid=objectid.get(position);
                        news n=new news();
                        n.setObjectId(oid);
                        n.delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    fname.remove(position);
                                    fimg.remove(position);
                                    fnews.remove(position);
                                    fhead.remove(position);
                                }
                                myallnews = (ListView) findViewById(R.id.myallnews);
                                myadapt=new myadapt();
                                myallnews.setAdapter(myadapt);
                            }
                        });
                        Toast.makeText(mynew.this,"删除一条动态",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    class myadapt extends BaseAdapter {
        @Override
        public int getCount() {
            return fname.size();
        }

        @Override
        public Object getItem(int position) {
            return fname.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.newslist, null);
            TextView name = (TextView) view.findViewById(R.id.names);
            TextView msg = (TextView) view.findViewById(R.id.news);
            ImageView face = (ImageView) view.findViewById(R.id.nhead);
            ImageView img = (ImageView) view.findViewById(R.id.nimg);
            name.setText((CharSequence) fname.get(position));
            msg.setText((CharSequence) fnews.get(position));
            img.setImageBitmap(BitmapFactory.decodeFile(fimg.get(position)));
            face.setImageBitmap(BitmapFactory.decodeFile(fhead.get(position)));
            System.out.println(fname.get(position));
            return view;
        }
    }
}
