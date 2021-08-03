package com.callor.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // activity_*.xml 에서 생성한 view를  java에서 핸들링하기
    // view를 핸들링 하기 위해서 class member 영역에
    // 해당 view의 객체변수를 선언한다
    private Button btn_login = null;
    private TextInputEditText input_email = null;
    private TextInputEditText input_password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 파일에 선언된 view를 java의 객체로 변환 생성
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(view->{
            String email = input_email.getText().toString();
            String password = input_password.getText().toString();
        });

    }
}