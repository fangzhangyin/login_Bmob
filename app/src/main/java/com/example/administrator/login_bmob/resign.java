package com.example.administrator.login_bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import entity.admin;

public class resign extends AppCompatActivity {

    private EditText adname;
    private EditText pass1;
    private EditText pass2;
    private EditText email;
    private Button btn;
    private TextView text;


    private String name;
    private String p1;
    private String p2;
    private String em;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);
        adname=(EditText)findViewById(R.id.adname);
        pass1=(EditText)findViewById(R.id.pass1);
        pass2=(EditText)findViewById(R.id.pass2);
        email=(EditText)findViewById(R.id.email);
        btn=(Button)findViewById(R.id.btn);
        text=(TextView)findViewById(R.id.text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin ad=new admin();
                name=adname.getText().toString().trim();
                p1=pass1.getText().toString().trim();
                p2=pass2.getText().toString().trim();
                em=email.getText().toString().trim();
                ad.setAdname(name);
                ad.setPassword(p1);
                ad.setEmail(em);
                boolean f=checkemail(em);
                boolean p=checkpassword(p1,p2);
                if(f==false||email.equals("")){
                    text.setText("邮箱格式有误，请再次输入");
                }else if(p==false){
                    text.setText("两次密码输入不一致");
                }else if((name.equals("")||p1.equals("")||p2.equals(""))) {
                    text.setText("请填写完整的信息");
                }else{
                    ad.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                finish();
                            }
                            else if(e.getErrorCode()==401){
                                text.setText("该用户名已被注册");
                            }
                            else{
                                text.setText("未知错误");
                            }
                        }
                    });
                }

            }
        });

    }

    public boolean checkemail(String email){
        boolean flag=false;
        if (email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            flag=true;
        }
        return flag;
    }
    public boolean checkpassword(String p1,String p2){
        boolean flag=false;
        if(p1.equals(p2)){
            flag=true;
        }
        return flag;
    }
}
