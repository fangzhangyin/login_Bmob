package com.example.administrator.login_bmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import entity.admin;

public class MainActivity extends AppCompatActivity {


   private  TextView t1;
    private TextView t2;

    private Button btn1;
    private Button btn2;

    private EditText tt1;
    private EditText tt2;

    private String name=null;
    private String password=null;

    admin ad=new admin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(MainActivity.this,"273a956d9720a23f5562cc6bf625c4f0");
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
//        //根据adname 查找
//        String s="方";
//        BmobQuery<admin> query=new BmobQuery<admin>();
//        query.addWhereContains("adname",s);
//        query.findObjects(new FindListener<admin>() {
//            @Override
//            public void done(List<admin> list, BmobException e) {
//                if(e==null) {
//                    t1.setText(list.toString());
//                }
//            }
//        });

        tt1=(EditText)findViewById(R.id.adname);
        tt2=(EditText)findViewById(R.id.password);



        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);


        //添加记录到adname(注册)

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=tt1.getText().toString();
                password=tt2.getText().toString();
                if(name!=null||!name.equals("")||password!=null||!password.equals("")){
                    ad.setAdname(name);
                    ad.setPassword(password);
                    ad.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                t1.setText("注册成功");
                            }
                            else if(e.getErrorCode()==401){
                                t1.setText("用户名已存在");
                            }
                            else{
                                t1.setText("未知错误");
                            }
                        }
                    });
                }
            }
        });


        //查询数据（实现登陆）
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
