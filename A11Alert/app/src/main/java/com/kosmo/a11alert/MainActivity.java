package com.kosmo.a11alert;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 대화창 1 : 기본형(타이틀+내용)
    public void onBtn1Clicked(View v ){
        MyAlert.AlertShow(MainActivity.this, "아이디를 입력해 주세요", "알림");
    }

    // 대화창 2 : 상단 타이틀 없이 내용만 출력됨
    public void onBtn2Clicked(View v ){
        MyAlert.AlertShow(MainActivity.this, "패스워드를 입력해주세요");
    }

    /* 대화창 3 : 버튼 처리
        - 확인, 취소 버튼에 리스너 부착 후 동작할 내용 정의
        - AlertDialog창을 띄우는 절차
            1) AlertDialog.Builder 객체 생성
            2) 생성자 함수를 통한 설정(타이틀, 메세지, 아이콘 등)
            3) 2에서 생성한 객체를 통해 create()메소드 호출
            4) show()호출을 통해 경고창 띄움 */
    public void onBtn3Clicked(View v ){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("앱을 종료하시겠습니까?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton("Yes(대신 그래요 써도됨)", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                        // Yes버튼을 누르면 -1이 반환된다.
                        Toast.makeText(getApplicationContext(), "Yes => ID is "+Integer.toString(id)
                                    , Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton("No(대신 놉 써도 됨)", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                        // No버튼을 누르면 -2가 반환된다.
                        Toast.makeText(getApplicationContext(), "No => ID is "+Integer.toString(id)
                                , Toast.LENGTH_SHORT).show();

                        // 아래는 주석 하고 안하고에 차이는 없다.
                        //dialogInterface.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}