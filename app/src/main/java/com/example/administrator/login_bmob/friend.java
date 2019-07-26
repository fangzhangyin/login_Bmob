package com.example.administrator.login_bmob;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class friend extends AppCompatActivity implements View.OnClickListener {

    private TextView name;
    private TextView sex;
    private TextView femail;
    private TextView person;

    private ImageView fhead;

    private Spinner spinner;
    private ArrayAdapter<CharSequence> arrayAdapter;


    private String s1,s2,s3,s4,s5;

    private ImageView back;
    private ImageView fset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Intent intent=getIntent();
//        String a=intent.getStringExtra("fname");
//        System.out.println(a);
        name=(TextView)findViewById(R.id.friname);
        sex=(TextView)findViewById(R.id.sex);
        femail=(TextView)findViewById(R.id.femail);
        person=(TextView)findViewById(R.id.person);

        fhead=(ImageView)findViewById(R.id.fhead);

        back=(ImageView)findViewById(R.id.back);
        fset=(ImageView)findViewById(R.id.setf);
        back.setOnClickListener(this);
        //fset.setOnClickListener(this);

        name.setText(intent.getStringExtra("fname"));
        sex.setText(intent.getStringExtra("sex"));
        femail.setText(intent.getStringExtra("email"));
        person.setText(intent.getStringExtra("person"));

        fhead.setImageBitmap(BitmapFactory.decodeFile(intent.getStringExtra("head")));



        s1=name.getText().toString();
        s2=sex.getText().toString();
        s3=femail.getText().toString();
        s4=person.getText().toString();
        s5=intent.getStringExtra("head");

        spinner=(Spinner)findViewById(R.id.set);
        arrayAdapter=ArrayAdapter.createFromResource(friend.this,R.array.setlist,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }

}
