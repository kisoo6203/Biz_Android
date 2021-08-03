package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView join = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        join = findViewById(R.id.join);

        Intent intent = getIntent();
        String userId = intent.getExtras().getString("ID");
        String userPw = intent.getExtras().getString("PW");
        String userName = intent.getExtras().getString("NAME");
        String userTel = intent.getExtras().getString("TEL");
        String userAddr = intent.getExtras().getString("ADDR");

        String msg = String.format("ID : %s\nPW : %s\nNAME : %s\nTEL : %s\nADDR : %s", userId, userPw, userName, userTel, userAddr);
        join.setText(msg);

    }
}