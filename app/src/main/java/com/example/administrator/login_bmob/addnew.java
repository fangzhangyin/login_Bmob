package com.example.administrator.login_bmob;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wildma.pictureselector.PictureSelector;

import java.io.IOException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import saveFile.fileWR;

public class addnew extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private Button save;

    private EditText msg;

    private LinearLayout getimg;
    private LinearLayout setroot;
    private String path;
    private String nn;
    private String name;
    private String head;

    private ImageView img;
    private String flag="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        back=(ImageView)findViewById(R.id.back);
        save=(Button)findViewById(R.id.save);
        msg=(EditText)findViewById(R.id.msg);
        getimg=(LinearLayout)findViewById(R.id.getimg);
        setroot=(LinearLayout)findViewById(R.id.setroot);
        img=(ImageView)findViewById(R.id.img);

        name=lendin.getname();
        head=lendin.gethead();

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        getimg.setOnClickListener(this);
        setroot.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.save:
                nn=msg.getText().toString();
                if(nn!=null&&!nn.equals("")){
                    entity.news news=new entity.news();
                    news.setNews(nn);
                    news.setAdname(name);
                    news.setRoot(flag);
                    news.setFace(head);
                    if(path!=null) {
                        try {
                            path = fileWR.file3(name, path);
                            news.setImage(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        news.setImage(path);
                    }
                    news.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                finish();
                            }
                        }
                    });
                }

                break;
            case R.id.getimg:
                PictureSelector
                        .create(addnew.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.setroot:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE && data != null) {
            String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            path = data.getStringExtra(PictureSelector.PICTURE_PATH);
            img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //System.out.println(path);
        }
    }
}
