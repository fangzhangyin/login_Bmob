package com.example.administrator.login_bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import db.ADUS;
import entity.admin;

public class resign extends AppCompatActivity {

    private EditText adname;
    private EditText pass1;
    private EditText pass2;
    private EditText email;
    private Button btn;
    private TextView text;
    private TextView sex;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private String[] s=new String[]{"","男","女"};

    private String name;
    private String p1;
    private String p2;
    private String em;
    private String sexx;
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
        sex=(TextView)findViewById(R.id.sex);


        adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,s);

        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex.setText(adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin ad=new admin();
                name=adname.getText().toString().trim();
                p1=pass1.getText().toString().trim();
                p2=pass2.getText().toString().trim();
                em=email.getText().toString().trim();
                sexx=sex.getText().toString().trim();
                ad.setAdname(name);
                ad.setPassword(p1);
                ad.setEmail(em);
                ad.setSex(sexx);
                ad.setRoot("1");
                boolean f=checkemail(em);
                boolean p=checkpassword(p1,p2);
                if(f==false||email.equals("")){
                    text.setText("邮箱格式有误，请再次输入");
                }else if(p==false){
                    text.setText("两次密码输入不一致");
                }else if((name.equals("")||p1.equals("")||p2.equals("")||sexx.equals(("")))) {
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
