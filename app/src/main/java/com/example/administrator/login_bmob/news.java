package com.example.administrator.login_bmob;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class news extends Fragment implements View.OnClickListener {


    private Context context;
    private ImageView search;
    private TextView flag;
    private Spinner add;
    private ArrayAdapter<CharSequence> adapter;

    private LinearLayout looknews;
    private LinearLayout mynews;

    private String face;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        face = ((lendin) activity).gethead();
    }

    public news() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_news,null);
        context=getContext();
        flag=(TextView)view.findViewById(R.id.flag);
        flag.setText("动态");
        search=(ImageView)view.findViewById(R.id.search);
        looknews=(LinearLayout)view.findViewById(R.id.looknews);
        mynews=(LinearLayout)view.findViewById(R.id.mynews);


        search.setOnClickListener(this);
        looknews.setOnClickListener(this);
        mynews.setOnClickListener(this);

        add=(Spinner)view.findViewById(R.id.add);
        adapter= ArrayAdapter.createFromResource(context,R.array.datalist,R.layout.support_simple_spinner_dropdown_item);
        add.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                Intent intent=new Intent(context,find.class);
                startActivity(intent);
                break;
            case R.id.looknews:
                Intent intent1=new Intent(context,allnews.class);
                intent1.putExtra("face",face);
                startActivity(intent1);
                break;
            case R.id.mynews:
                Intent intent2=new Intent(context,mynew.class);
                startActivity(intent2);
                break;



        }
    }
}
