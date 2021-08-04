package com.kisoo.chatt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kisoo.chatt.Adapter.ChattAdapter;
import com.kisoo.chatt.model.Chatt;
import com.kisoo.chatt.service.FirebaseServiceImplV1;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // chatt 메시지를 전달하는 view 들
    private RecyclerView chatt_list_view;
    private EditText txt_msg;

    // chatt 메시지를 표현할 view들
    private AppCompatButton btn_send;
    private ChattAdapter chattAdapter;
    private List<Chatt> chattList;

    // firebase와 연결하는 Connection을 위한 객체 선언하기
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
            setContentView(R.layout.activity_main);
            *layout.xml 파일을 읽어서 화면을 만드는 method
            setContentView는 한개의 파일을 읽ㅇ더서
                한개의 전체 화면을 만드는 것
         */
        setContentView(R.layout.activity_main);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chatt_list_view = findViewById(R.id.chatt_list_view);

        // 0. 보여줄 데이터 객체 생성
        chattList = new ArrayList<Chatt>();

        // 1. Adapter 객체 생성
        // Adapter 객체를 생성할때 보여줄 데이터 객체를
        // 생성자 매개변수로 주입해 주어야 한다.
        chattAdapter = new ChattAdapter(chattList);

        // 2. RecyclerView.Adapter와 RecyclerView 를 서로 연결
        chatt_list_view.setAdapter(chattAdapter);

        // 3. RecyclerView의 데이터들을 표현하는데 사용할
        // Layout 매니저를 설정하기
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chatt_list_view.setLayoutManager(layoutManager);


        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();
        // 사용할 table(path)
        // realtimeDatabase에서는 table을 path라는 개념으로 부른다
        dbRef = dbConn.getReference("chatting");

        // firebase로부터 데이터 변화 이벤트가 전달되면
        // 이벤트를 수신하여 할 일을 지정하기위한 핸들러 선언
        ChildEventListener childEventListener
                = new FirebaseServiceImplV1(chattAdapter);
        
        // 이벤트 핸들러 연결
        dbRef.addChildEventListener(childEventListener);




        // 테스트를 위한 가상의 데이터 생성하기
        /*
        Chatt chatt = new Chatt();
        chatt.setName("홍길동");
        chatt.setMsg("반갑습니다");
        chattList.add(chatt);

        chatt = new Chatt();
        chatt.setName("성춘향");
        chatt.setMsg("안녕하세요");
        chattList.add(chatt);

        chatt = new Chatt();
        chatt.setName("이몽룡");
        chatt.setMsg("오늘은 좋은날");
        chattList.add(chatt);
        */


        /*
            EditBox에 메시지를 입력하고 Send 버튼을 클릭했을때 할일 지정하기기

            EditBox에 메시지를 입력하고 Send를 하면
            Firebase의 Realtime DataBase에 데이터를 insert하기
        */
        btn_send.setOnClickListener(view->{
            // EditBox에 입력된 문자열을 추출하여 문자열 변수에 담기
            String msg = txt_msg.getText().toString();
            if(msg != null && !msg.isEmpty()){

                String toastMsg = String.format("메시지 : %s",msg);
                Toast.makeText(MainActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();

                Chatt chattVO = new Chatt();
                chattVO.setMsg(msg);
                chattVO.setName("개덥구만");

                Log.d("클릭",chattVO.toString());

                // chattList.add(chattVO);
                // firebase의 realtime DB의 table에 데이터를 insert하라
                // push하라
                dbRef.push().setValue(chattVO);
                txt_msg.setText("");
            }

        });

    }
}