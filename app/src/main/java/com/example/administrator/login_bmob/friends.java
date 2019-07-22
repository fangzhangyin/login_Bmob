package com.example.administrator.login_bmob;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class friends extends Fragment {


    public friends() {
        // Required empty public constructor
    }

    private ListView listView;
    private List<String> list;
    private List<String> email;

    private String[] name=new String[]{"a","b","c","d","e"};
    private String[] femail=new String[]{"A","B","C","D","E"};
    private myadapt myadapt;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends,container,false);
        list=new ArrayList<String>();
        email=new ArrayList<String>();
        for(int i=0;i<11;i++){
            list.add("方"+i);
            email.add("2570@qq.com"+i);
        }
        context=getContext();
        myadapt=new myadapt();
        listView=(ListView)view.findViewById(R.id.listview);
        listView.setAdapter(myadapt);
        return view;
    }




    class myadapt extends BaseAdapter{

//        private String[] name;
//        private String[] email;
//        public myadapt(String[] name,String[] email){
//            super();
//            this.name=name;
//            this.email=email;
//        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) LayoutInflater.from(context);
            View view=layoutInflater.inflate(R.layout.friendslist,null);
            TextView fname=(TextView)view.findViewById(R.id.fname);
            TextView femail=(TextView)view.findViewById(R.id.femail);
            fname.setText((CharSequence) list.get(position));
            femail.setText((CharSequence) email.get(position));
            return view;
        }

    }


}
