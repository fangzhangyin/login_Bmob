package com.example.administrator.login_bmob;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import entity.admin;
import entity.news;

public class allnews extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout bg;

    private ImageView nback;
    private ImageView nadd;
    private ImageView face;
    private ListView allnews;

    private String fa;

    private List<String> fhead;
    private List<String> fimg;
    private List<String> fname;
    private List<String> fnews;

    private myadapt myadapt;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnews);


        fa = getIntent().getStringExtra("face");

        bg = (LinearLayout) findViewById(R.id.bg);
        nadd = (ImageView) findViewById(R.id.nadd);
        nback = (ImageView) findViewById(R.id.nback);
        face = (ImageView) findViewById(R.id.face);


        face.setImageBitmap(BitmapFactory.decodeFile(fa));

        nadd.setOnClickListener(this);
        nback.setOnClickListener(this);
        context = getBaseContext();

        fhead = new ArrayList<String>();
        fname = new ArrayList<String>();
        fimg = new ArrayList<String>();
        fnews = new ArrayList<String>();

        BmobQuery<entity.news> query = new BmobQuery<entity.news>();
        query.addWhereEqualTo("root", "1");
        query.findObjects(new FindListener<news>() {
            @Override
            public void done(final List<news> list, BmobException e) {
                if (e == null) {
                    String name = null;
                    for (news n : list) {
                        fname.add(n.getAdname());
                        fimg.add(n.getImage());
                        fnews.add(n.getNews());
                        fhead.add(n.getFace());
                    }
                }
                System.out.println(fname + "\n" + fimg + "\n" + fhead + "\n" + fnews);
                allnews = (ListView) findViewById(R.id.allnwes);
                myadapt = new myadapt();
                allnews.setAdapter(myadapt);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nback:
                finish();
                break;
            case R.id.nadd:
                Intent intent = new Intent(allnews.this, addnew.class);
                startActivity(intent);
                break;
        }

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
