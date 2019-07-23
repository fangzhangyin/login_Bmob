package com.example.administrator.login_bmob;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class self extends Fragment implements View.OnClickListener {


    public self() {
        // Required empty public constructor
    }

    private TextView name;
    private TextView oneself;
    private TextView syset;
    private TextView about;
    private TextView exit;

    private String self;
    private String email;
    private String parent;

    private Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        self = ((lendin) activity).getself();
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_self,null);
        name=view.findViewById(R.id.self);
        name.setText(self);
        oneself=(TextView) view.findViewById(R.id.oneself);
        syset=(TextView) view.findViewById(R.id.syset);
        about=(TextView) view.findViewById(R.id.about);
        exit=(TextView) view.findViewById(R.id.exit);

        oneself.setOnClickListener(this);
        syset.setOnClickListener(this);
        about.setOnClickListener(this);
        exit.setOnClickListener(this);

        context=getContext();

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.oneself:
                Intent intent=new Intent(context,myself.class);
                intent.putExtra("self",self);

                break;
        }
    }
}
