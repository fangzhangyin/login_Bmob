package com.example.administrator.login_bmob;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myself extends AppCompatActivity implements View.OnClickListener {

    private TextView selfname;
    private TextView selfemail;
    private TextView selfsex;
    private TextView selfparent;

    private String name;
    private String email;
    private String sex;
    private String person;

    private ImageView back;

    private LinearLayout selfline1;
    private LinearLayout selfline2;
    private LinearLayout selfline3;
    private LinearLayout selfline4;
    private LinearLayout selfline5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);

        selfname=(TextView)findViewById(R.id.selfname);
        selfemail=(TextView)findViewById(R.id.selfemail);
        selfsex=(TextView)findViewById(R.id.selfsex);
        selfparent=(TextView)findViewById(R.id.selfparent);

        back=(ImageView)findViewById(R.id.back);


        back.setOnClickListener(this);

        Intent intent=getIntent();
        name=intent.getStringExtra("self");
        email=intent.getStringExtra("email");
        sex=intent.getStringExtra("sex");
        person=intent.getStringExtra("person");

        selfname.setText(name);
        selfemail.setText(email);
        selfsex.setText(sex);
        selfparent.setText(person);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.selfline1:

        }
    }
}
