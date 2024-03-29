package com.example.administrator.login_bmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import entity.admin;
import gangbo.baseActivity;

public class MainActivity extends baseActivity {


   private  TextView t1;
    private TextView t2;

    private Button btn1;
    private Button btn2;

    private ImageButton btn;

    private EditText tt1;
    private EditText tt2;

    private String name=null;
    private String password=null;
    private String sex=null;
    private String person;
    private String email;
    private String id;
    private String head;


    private String[] s;

    int flag=0;

    admin ad=new admin();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化bmob云
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

        btn=(ImageButton)findViewById(R.id.btnshow);


        //添加记录到adname(注册)

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                name=tt1.getText().toString().trim();
//                password=tt2.getText().toString().trim();
//                if(name!=null||!name.equals("")||password!=null||!password.equals("")){
//                    ad.setAdname(name);
//                    ad.setPassword(password);
//                    ad.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//                            if(e==null){
//                                t1.setText("注册成功");
//                            }
//                            else if(e.getErrorCode()==401){
//                                t1.setText("用户名已存在");
//                            }
//                            else{
//                                t1.setText("未知错误");
//                            }
//                        }
//                    });
//                }
                startActivity(new Intent(getBaseContext(),resign.class));
            }
        });


        //查询数据（实现登陆）
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=tt1.getText().toString().trim();
                password=tt2.getText().toString().trim();
                if(name!=null||!name.equals("")||password!=null||!password.equals("")){
                    BmobQuery<admin>query=new BmobQuery<admin>();
                    query.addWhereEqualTo("adname",name);
                    query.findObjects(new FindListener<admin>() {
                        @Override
                        public void done(List<admin> list, BmobException e) {
                            String pass=null;
                            if(list.size()==1) {
                                if (e == null) {
                                    Iterator iterator = list.iterator();
                                    while (((Iterator) iterator).hasNext()) {
                                        admin admin = (admin) iterator.next();
                                        pass=admin.getPassword();
                                        person=admin.getPerson();
                                        sex=admin.getSex();
                                        email=admin.getEmail();
                                        id=admin.getObjectId();
                                        head=admin.getHead();
                                        s=new String[]{name,person,sex,email};
                                        break;
                                    }
                                    if(pass.equals(password)){
                                        Intent intent=new Intent(MainActivity.this,lendin.class);
                                        intent.putExtra("adname",name);
                                        intent.putExtra("pass",password);
                                        intent.putExtra("s",s);
                                        System.out.println(name);
                                        intent.putExtra("sex",sex);
                                        intent.putExtra("person",person);
                                        intent.putExtra("email",email);
                                        intent.putExtra("id",id);
                                        intent.putExtra("head",head);
                                        startActivity(intent);
                                    }else{
                                        t1.setText("用户名或者密码错误");
                                    }
                                }
                            }
                            else{
                                t1.setText("用户不存在");
                            }
                        }
                    });

                }
            }
        });

        //密码的显示和隐藏
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    btn.setBackgroundResource(R.drawable.bmob_update_btn_check_on_focused_holo_light);
                    tt2.setInputType(InputType.TYPE_CLASS_TEXT);
                    flag=1;
                }else if(flag==1){
                    btn.setBackgroundResource(R.drawable.bmob_update_btn_check_off_focused_holo_light);
                    tt2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                    flag=0;
                }
            }
        });


    }

    //绑定按钮
    public void button(View view) {
        Intent intent = new Intent(this, lendin.class);
        startActivity(intent);
    }

    //重写onkeydown方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//点击的为返回键
        if (keyCode == event.KEYCODE_BACK) {
            exit();// 退出方法
        }
        return true;
    }
    //退出方法
    private long time = 0;
    private void exit() {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
            showToast("再点击一次退出应用程序");
        } else {
            Intent intent = new Intent("drc.xxx.yyy.baseActivity");
            intent.putExtra("closeAll", 1);
            sendBroadcast(intent);//发送广播
        }
    }

}
