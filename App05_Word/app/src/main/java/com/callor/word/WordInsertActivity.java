package com.callor.word;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.callor.word.databinding.ActivityWordInsertBinding;

public class WordInsertActivity extends AppCompatActivity {

    // Binding을 적용하기 위한 선언
    ActivityWordInsertBinding wordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding을 적용하기 위한 생성(초기화)
        wordBinding = ActivityWordInsertBinding.inflate(getLayoutInflater());

        // Binding을 적용하여 Activity 화면그리기
        setContentView(wordBinding.getRoot());

        // open한 Activity에게 return 하기위한 intent 정보 추출
        Intent returnIntent = new Intent();

        // 저장버튼을 클릭했을때
        wordBinding.btnSave.setOnClickListener(view->{

            String word = wordBinding.inputWord.getText().toString();
            String korea = wordBinding.inputKorea.getText().toString();

            // word, korea 변수에 값을 보내기 위하여 setting(putting)하기
            returnIntent.putExtra("WORD",word);
            returnIntent.putExtra("KOREA",korea);

            setResult(RESULT_OK,returnIntent);
            // 새롭게 열린 Activity에서 자신을 닫는 매서드
            finish();
        });
    }
}