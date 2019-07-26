package com.example.administrator.login_bmob;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import entity.admin;
import entity.news;

public class find extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private EditText searcht;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private ListView searchlist;

    private List<String> face;
    private List<String> name;
    private List<String> msg;
    private List<Map<String,Object>>listitem=new ArrayList<Map<String, Object>>();

    private SimpleAdapter simpleAdapter;
    private myadapt adapter;


    String ss=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        back=(ImageView)findViewById(R.id.back);
        searcht=(EditText)findViewById(R.id.searcht);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        searchlist=(ListView)findViewById(R.id.searchlist);

        name=new ArrayList<String>();
        face=new ArrayList<String>();
        msg=new ArrayList<String>();
        adapter=new myadapt();
        searchlist.setAdapter(adapter);


        back.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.t1:
                ss=searcht.getText().toString().trim();
                if(!(ss.equals("")&&ss==null)){
                   dolist(1);
            }else{ }
                break;
            case R.id.t2:
                ss=searcht.getText().toString().trim();
                if(!(ss.equals("")&&ss==null)){
                    dolist(2);
                }else{ }
                break;
            case R.id.t3:
                break;
            default:
                break;
        }
    }




    private void dolist(int g) {
        if(g==1){
            BmobQuery<admin> query=new BmobQuery<admin>();
            query.addWhereEqualTo("adname", ss);
            query.findObjects(new FindListener<admin>() {
                @Override
                public void done(List<admin> list, BmobException e) {
                    if(e==null){
                        face.clear();
                        msg.clear();
                        name.clear();
                        Iterator iterator = list.iterator();
                        while (((Iterator) iterator).hasNext()) {
                            admin admin = (admin) iterator.next();
                            face.add(admin.getHead());
                            name.add(admin.getAdname());
                            msg.add(admin.getEmail());
                        }
                    }
                }
            });
            adapter=new myadapt();
            searchlist.setAdapter(adapter);
        }
        if(g==2){
            BmobQuery<news> query=new BmobQuery<news>();
            query.addWhereEqualTo("adname", ss);
            query.findObjects(new FindListener<news>() {
                @Override
                public void done(List<news> list, BmobException e) {
                    if(e==null){
                        face.clear();
                        msg.clear();
                        name.clear();
                        Iterator iterator = list.iterator();
                        while (((Iterator) iterator).hasNext()) {
                            news n=new news();
                            face.add(n.getImage());
                            msg.add(n.getNews());
                            name.add(n.getAdname());
                        }
                    }
                }
            });
            adapter=new myadapt();
            searchlist.setAdapter(adapter);
        }
    }

    class myadapt extends BaseAdapter{

        @Override
        public int getCount() {
            return name.size();
        }

        @Override
        public Object getItem(int position) {
            return name.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) LayoutInflater.from(find.this);
            View view = layoutInflater.inflate(R.layout.searchlist, null);
            TextView fname = (TextView) view.findViewById(R.id.name);
            TextView fmsg = (TextView) view.findViewById(R.id.msg);
            ImageView fhead=(ImageView)view.findViewById(R.id.img);
            fname.setText((CharSequence) name.get(position));
            fmsg.setText((CharSequence) msg.get(position));
            fhead.setImageBitmap(BitmapFactory.decodeFile((String) face.get(position)));
            return view;
        }
    }

}
