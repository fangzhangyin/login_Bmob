package com.example.administrator.login_bmob;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TooManyListenersException;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import db.ADUS;
import entity.admin;

import static android.media.CamcorderProfile.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class friends extends Fragment implements View.OnClickListener {


    private ImageView search;
    private Spinner add;
    private ArrayAdapter<CharSequence> adapter;

    public friends() {
        // Required empty public constructor
    }

    private ListView listView;
    private List<String> list1;
    private List<String> email;
    private List<String> person;
    private List<String>sex;
    private List<String>head;

    private TextView flag;

    private ADUS adus;

    private List<admin> adlist;

    //    private String[] name=new String[]{"a","b","c","d","e"};
//    private String[] femail=new String[]{"A","B","C","D","E"};
    private myadapt myadapt;
    private Context context;
    private View view;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_friends, container, false);
        list1 = new ArrayList<String>();
        email = new ArrayList<String>();
        person=new ArrayList<String>();
        sex=new ArrayList<String>();
        head=new ArrayList<String>();

        flag=(TextView)view.findViewById(R.id.flag);
        flag.setText("朋友");

        search=(ImageView) view.findViewById(R.id.search);
        search.setOnClickListener(this);

        context = getContext();

        add=(Spinner)view.findViewById(R.id.add);
        adapter= ArrayAdapter.createFromResource(context,R.array.datalist,R.layout.support_simple_spinner_dropdown_item);
        add.setAdapter(adapter);



//        adus=new ADUS();
//        adlist=new ArrayList<admin>();
//        adlist=adus.searchbyroot("1");
//        System.out.println(adlist.size());
//        Iterator<admin> iterator = adlist.iterator();
//        while (iterator.hasNext()) {
//            admin ad = (admin) iterator.next();
//            list.add(ad.getAdname());
//            email.add(ad.getEmail());
//        }


        BmobQuery<admin> query = new BmobQuery<admin>();
        query.addWhereEqualTo("root", "1");
        query.findObjects(new FindListener<admin>() {
            @Override
            public void done(final List<admin> list, BmobException e) {
                // ArrayList<admin> adminlist=new ArrayList<admin>();
                if (e == null) {
                    Iterator<admin> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        admin ad = (admin) iterator.next();
                        list1.add(ad.getAdname());
                        email.add(ad.getEmail());
                        person.add(ad.getPerson());
                        head.add(ad.getHead());
                        //System.out.println(ad.getHead());
                        sex.add(ad.getSex());
                    }
                    myadapt=new myadapt();
                    listView = (ListView) view.findViewById(R.id.listview);
                    listView.setAdapter(myadapt);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        //列表的按下事件监听
                        //parent 表示listview
                        //view 表示适配器item对应的view
                        //position表示按下对应的id
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String name = list1.get(position);
                            String femail=email.get(position);
                            String fperson=person.get(position);
                            String fsex=sex.get(position);
                            String heads=head.get(position);
                            //Toast.makeText(context,"Toast"+name,Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context,friend.class);
                            intent.putExtra("fname",name);
                            intent.putExtra("email",femail);
                            intent.putExtra("person",fperson);
                            intent.putExtra("sex",fsex);
                            intent.putExtra("head",heads);
                            startActivity(intent);
                        }
                    });


                } else {
                    System.out.println("error");
                }
            }
        });

        //System.out.println(list1);
//        for(int i=0;i<11;i++){
//            list.add("方"+i);
//            email.add("2570@qq.com"+i);
//        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                Intent intent=new Intent(context,find.class);
                startActivity(intent);
                break;

        }
    }

    class myadapt extends BaseAdapter {

//        private String[] name;
//        private String[] email;
//        public myadapt(String[] name,String[] email){
//            super();
//            this.name=name;
//            this.email=email;
//        }

        @Override
        public int getCount() {
            return list1.size();
        }

        @Override
        public Object getItem(int position) {
            return list1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.friendslist, null);
            TextView fname = (TextView) view.findViewById(R.id.fname);
            TextView femail = (TextView) view.findViewById(R.id.femail);
            ImageView fhead=(ImageView)view.findViewById(R.id.head);
            fname.setText((CharSequence) list1.get(position));
            femail.setText((CharSequence) email.get(position));
            fhead.setImageBitmap(BitmapFactory.decodeFile((String) head.get(position)));
            return view;
        }

    }


}
