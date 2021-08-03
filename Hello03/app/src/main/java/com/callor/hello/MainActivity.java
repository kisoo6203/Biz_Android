package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText us_id = null;
    private TextInputEditText us_pw = null;
    private TextInputEditText us_name = null;
    private TextInputEditText us_tel = null;
    private TextInputEditText us_addr = null;

    private Button btn_join = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        us_id = findViewById(R.id.layout_4);
        us_pw = findViewById(R.id.layout_5);
        us_name = findViewById(R.id.layout_1);
        us_tel = findViewById(R.id.layout_3);
        us_addr = findViewById(R.id.layout_2);

        btn_join = findViewById(R.id.button);

        btn_join.setOnClickListener((view)->{
            String userId = us_id.getText().toString();
            String userPw = us_pw.getText().toString();
            String userName = us_name.getText().toString();
            String userTel = us_tel.getText().toString();
            String userAddr = us_addr.getText().toString();

            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("ID",userId);
            intent.putExtra("PW",userPw);
            intent.putExtra("NAME",userName);
            intent.putExtra("TEL",userTel);
            intent.putExtra("ADDR",userAddr);
            startActivity(intent);

        });



    }
}