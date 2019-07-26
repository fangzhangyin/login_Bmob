package com.example.administrator.login_bmob;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class chart extends Fragment implements View.OnClickListener {


    private Context context;
    private ImageView search;
    private TextView flag;

    private Spinner add;

    private ArrayAdapter<CharSequence> adapter;

    public chart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chart,null);
        context=getContext();
        flag=(TextView)view.findViewById(R.id.flag);
        flag.setText("消息");
        search=(ImageView) view.findViewById(R.id.search);


        add=(Spinner)view.findViewById(R.id.add);
        adapter=ArrayAdapter.createFromResource(context,R.array.datalist,R.layout.support_simple_spinner_dropdown_item);
        add.setAdapter(adapter);

        search.setOnClickListener(this);
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
}
