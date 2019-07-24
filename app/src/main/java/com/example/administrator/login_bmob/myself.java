package com.example.administrator.login_bmob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildma.pictureselector.PictureSelector;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.OnClick;

public class myself extends AppCompatActivity implements View.OnClickListener {

    private static final int CROP_SMALL_PICTURE = 1;
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
    private LinearLayout selfline6;

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

        back=(ImageView)findViewById(R.id.back);

        selfline1=findViewById(R.id.selfline1);
        selfline2=findViewById(R.id.selfline2);
        selfline3=findViewById(R.id.selfline3);
        selfline4=findViewById(R.id.selfline4);
        selfline5=findViewById(R.id.selfline5);
        selfline6=findViewById(R.id.selfline6);

        head=(ImageView)findViewById(R.id.head);

        selfline1.setOnClickListener(this);
        selfline2.setOnClickListener(this);
        selfline3.setOnClickListener(this);
        selfline4.setOnClickListener(this);
        selfline5.setOnClickListener(this);


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PictureSelector.SELECT_REQUEST_CODE&&data!=null){
            String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            //System.out.println(PictureSelector.PICTURE_PATH);
            head.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
        else if(requestCode==1){
            selfname.setText(data.getStringExtra("msg"));
        }
        else if(requestCode==2){
            selfemail.setText(data.getStringExtra("msg"));
        }
        else if(requestCode==3){
            selfsex.setText(data.getStringExtra("msg"));
        }else if(requestCode==4){
            selfparent.setText(data.getStringExtra("msg"));
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
            case R.id.selfline6:

                break;
        }
    }



}
