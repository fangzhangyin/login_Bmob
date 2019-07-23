package com.example.administrator.login_bmob;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class myself extends AppCompatActivity {

    private TextView selfname;
    private TextView selfemail;
    private TextView selfsex;
    private TextView selfparent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);

        selfname=(TextView)findViewById(R.id.selfname);
        selfemail=(TextView)findViewById(R.id.selfemail);
        selfsex=(TextView)findViewById(R.id.selfsex);
        selfparent=(TextView)findViewById(R.id.selfparent);

    }
}
