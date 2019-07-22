package com.example.administrator.login_bmob;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.OnClick;

public class lendin extends AppCompatActivity implements View.OnClickListener{

    private String adname;

    private ImageView m1;
    private ImageView m2;
    private ImageView m3;
    private ImageView m4;
    private LinearLayout linearLayout;

    private TextView flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendin);
        Intent intent=getIntent();
        adname=intent.getStringExtra("adname");
        System.out.println(adname);


        m1=(ImageView)findViewById(R.id.f1);
        m2=(ImageView)findViewById(R.id.f2);
        m3=(ImageView)findViewById(R.id.f3);
        m4=(ImageView)findViewById(R.id.f4);
        flag=(TextView)findViewById(R.id.flag);

        linearLayout=(LinearLayout)findViewById(R.id.lin1);

        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        m3.setOnClickListener(this);
        m4.setOnClickListener(this);

    }

    public String  getself(){
        return adname;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Fragment f=null;
        switch (v.getId()){
            case R.id.f1:
                f=new chart();
                flag.setText("消息");
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.f2:
                f=new friends();
                flag.setText("朋友");
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.f3:
                f=new news();
                flag.setText("圈子");
                linearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.f4:
                f=new self();
                flag.setText("个人");
                linearLayout.setVisibility(View.GONE);
                break;
                default:
                    break;
        }
        ft.replace(R.id.frame,f);
        ft.commit();
    }
}
