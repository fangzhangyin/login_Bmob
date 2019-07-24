package com.example.administrator.login_bmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class change extends AppCompatActivity  {

    String flag;

    private EditText newmsg;
    private Button save;
    private TextView ding;

    public static int f;

    public static String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        newmsg=(EditText)findViewById(R.id.newmsg);
        save=(Button)findViewById(R.id.save);
        ding =(TextView)findViewById(R.id.ding);

        final Intent intent=getIntent();
       flag=intent.getStringExtra("flag");
        System.out.println(flag);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg=newmsg.getText().toString().trim();
                Intent intentTemp = new Intent();
                if(!msg.equals("")&&msg!=null){
                if(flag.equals("a")){
                    intentTemp.putExtra("msg",msg);
                    setResult(1,intentTemp);
                    finish();
                }else if(flag.equals("b")){
                    if(resign.checkemail(msg)) {
                        intentTemp.putExtra("msg", msg);
                        setResult(2, intentTemp);
                        finish();
                    }else{
                        ding.setText("邮箱格式不正确");
                    }
                }
                else if(flag.equals("c")){
                    if(resign.checksex(msg)){
                        intentTemp.putExtra("msg", msg);
                        setResult(3, intentTemp);
                        finish();
                    }else{
                        ding.setText("性别输入不正确");
                    }
                }
                else if(flag.equals("d")){
                    intentTemp.putExtra("msg",msg);
                    setResult(4,intentTemp);
                    finish();
                }
                }else {
                    ding.setText("不能为空");
                }
            }
        });

    }



}
