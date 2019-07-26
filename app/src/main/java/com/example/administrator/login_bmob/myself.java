package com.example.administrator.login_bmob;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildma.pictureselector.PictureSelector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import entity.admin;
import gangbo.baseActivity;
import saveFile.fileWR;

public class myself extends baseActivity implements View.OnClickListener {

    private static final int CROP_SMALL_PICTURE = 1;
    private TextView selfname;
    private TextView selfemail;
    private TextView selfsex;
    private TextView selfparent;
    private TextView selfpass;

    private String s;
    private String id;

    private String path=null;

    private String name;
    private String email;
    private String sex;
    private String person;
    private String shead;

    private ImageView back;

    private LinearLayout selfline1;
    private LinearLayout selfline2;
    private LinearLayout selfline3;
    private LinearLayout selfline4;
    private LinearLayout selfline5;
    private LinearLayout selfline6;
    private LinearLayout pass;

    private ImageView head;

    private int Camera=100;
    private int Picture=200;
    private Uri tempUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);

        selfname=(TextView)findViewById(R.id.selfname);
        selfemail=(TextView)findViewById(R.id.selfemail);
        selfsex=(TextView)findViewById(R.id.selfsex);
        selfparent=(TextView)findViewById(R.id.selfparent);
        selfpass=(TextView)findViewById(R.id.selfpass) ;

        back=(ImageView)findViewById(R.id.back);

        selfline1=findViewById(R.id.selfline1);
        selfline2=findViewById(R.id.selfline2);
        selfline3=findViewById(R.id.selfline3);
        selfline4=findViewById(R.id.selfline4);
        selfline5=findViewById(R.id.selfline5);
        selfline6=findViewById(R.id.selfline6);
        pass=findViewById(R.id.pass);


        head=(ImageView)findViewById(R.id.head);

        selfline1.setOnClickListener(this);
        selfline2.setOnClickListener(this);
        selfline3.setOnClickListener(this);
        selfline4.setOnClickListener(this);
        selfline5.setOnClickListener(this);
        selfline6.setOnClickListener(this);
        pass.setOnClickListener(this);


        back.setOnClickListener(this);

        Intent intent=getIntent();
        name=intent.getStringExtra("self");
        email=intent.getStringExtra("email");
        sex=intent.getStringExtra("sex");
        person=intent.getStringExtra("person");
        id=intent.getStringExtra("id");
        selfpass.setText(intent.getStringExtra("pass"));
        shead=intent.getStringExtra("head");


        selfname.setText(name);
        selfemail.setText(email);
        selfsex.setText(sex);
        selfparent.setText(person);

        head.setImageBitmap(BitmapFactory.decodeFile(shead));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PictureSelector.SELECT_REQUEST_CODE&&data!=null){
            String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            path=data.getStringExtra(PictureSelector.PICTURE_PATH);
            head.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //System.out.println(path);
        }
        else if(requestCode==1){
            if(data.getStringExtra("msg")!=null) {
                selfname.setText(data.getStringExtra("msg"));
            }
        }
        else if(requestCode==2){
            if(data.getStringExtra("msg")!=null) {
                selfemail.setText(data.getStringExtra("msg"));
            }
            //selfemail.setText(data.getStringExtra("msg"));
        }
        else if(requestCode==3){
            if(data.getStringExtra("msg")!=null) {
                selfsex.setText(data.getStringExtra("msg"));
            }
            //selfsex.setText(data.getStringExtra("msg"));
        }else if(requestCode==4){
            if(data.getStringExtra("msg")!=null) {
                selfparent.setText(data.getStringExtra("msg"));
            }
           // selfparent.setText(data.getStringExtra("msg"));
        }else if(requestCode==5){
            if(data.getStringExtra("msg")!=null) {
                selfpass.setText(data.getStringExtra("msg"));
            }
            //selfpass.setText(data.getStringExtra("msg"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.selfline1:
//                AlertDialog.Builder builder=new AlertDialog.Builder(this);
//                builder.setTitle("选择来源");
//                builder.setItems(new String[]{"拍照", "图库"}, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case 0:
//                                //打开系统拍照程序，选择拍照
//                                Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                startActivityForResult(camera, Camera);
//                                break;
//                            case 1:
//                                //打开选择图片
//                                Intent picture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                                picture.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
////                                        "image/*");
//                                startActivityForResult(picture, Picture);
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
                PictureSelector
                        .create(myself.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.selfline2:
               Intent intent=new Intent(myself.this,change.class);
               intent.putExtra("flag","a");
                startActivityForResult(intent,1);
                break;
            case R.id.selfline3:
                Intent intent2=new Intent(myself.this,change.class);
                intent2.putExtra("flag","b");
                startActivityForResult(intent2,2);
                break;
            case R.id.selfline4:
                Intent intent3=new Intent(myself.this,change.class);
                intent3.putExtra("flag","c");
                startActivityForResult(intent3,3);
                break;
            case R.id.selfline5:
                Intent intent4=new Intent(myself.this,change.class);
                intent4.putExtra("flag","d");
                startActivityForResult(intent4,4);
                break;
            case R.id.pass:
                Intent intent5=new Intent(myself.this,change.class);
                intent5.putExtra("flag","e");
                startActivityForResult(intent5,5);
                break;
            case R.id.selfline6:
                admin admin=new admin();
                admin.setAdname(selfname.getText().toString());
                admin.setSex(selfsex.getText().toString());
                admin.setEmail(selfemail.getText().toString());
                admin.setPerson(selfparent.getText().toString());
                admin.setPassword(selfpass.getText().toString());
                if(path!=null){
                try {
                    path= fileWR.file2(admin.getAdname(),path);
                    admin.setHead(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }


                    admin.update(id,new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                       if(e==null){
//                           Intent intent = new Intent(myself.this,MainActivity.class);//跳转到MainActivity
//                           startActivity(intent);
                           android.os.Process.killProcess(android.os.Process.myPid());
                       }
                       else {
                           System.out.println(e.getErrorCode());
                       }
                        }
                    });

        }
    }





}
