package com.example.administrator.login_bmob;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.OnClick;

public class lendin extends AppCompatActivity implements View.OnClickListener{

    private String adname;
    private String sex=null;
    private String person;
    private String email;
    private String id;
    private String pass;
    private String head;

    private String[] s;

    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

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
        person=intent.getStringExtra("person");
        sex=intent.getStringExtra("sex");
        email=intent.getStringExtra("email");
        id=intent.getStringExtra("id");
        pass=intent.getStringExtra("pass");
        head=intent.getStringExtra("head");
        System.out.println(adname+"\n"+person+"\n"+sex+"\n"+email+"\n"+id+"\n"+pass+"\n"+head);

//        s=intent.getStringArrayExtra("s");
//        System.out.println(s[0]+"" +"\n"+s[1]);


        m1=(ImageView)findViewById(R.id.f1);
        m2=(ImageView)findViewById(R.id.f2);
        m3=(ImageView)findViewById(R.id.f3);
        m4=(ImageView)findViewById(R.id.f4);
        flag=(TextView)findViewById(R.id.flag);

        spinner=(Spinner) findViewById(R.id.add);
        adapter=ArrayAdapter.createFromResource(this,R.array.datalist,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        linearLayout=(LinearLayout)findViewById(R.id.lin1);

        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        m3.setOnClickListener(this);
        m4.setOnClickListener(this);

    }

    public String  getself(){
        return adname;
    }
    public String getemail(){
        return email;
    }
    public String getperson(){
        return person;
    }
    public String getsex(){
        return sex;
    }
    public String getid(){return id;}
    public String getpass(){return pass;}
    public String gethead(){return head;}


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
