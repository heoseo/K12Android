package com.kosmo.a11alert;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class MyAlert {

    // 1번 경고창 타이틀, 내용, 확인버튼 있음
    public static void AlertShow(Context context, String msg, String title){
        // 경고창을 띄우기 위한 객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // 아이콘 설정(기본이미지 사용)
        // builder은 메소드 체인형태로 기술 가능. (~~~.ㅇㅇㅇ().ㄴㄴㄴ().ㄷㄷㄷ() )
        builder.setIcon(android.R.drawable.ic_dialog_alert);    // 아이콘설정(기본이미지 사용)
        builder.setTitle(title);            // 제목설정
        builder.setCancelable(false); /*    // 안드로이드의 물리적버튼(Back버튼)을 눌렀을 때 동작방식
                                             - AlertDialog를 띄운 상태엥서 BACK버튼을 눌렀을 때 대화창이 닫히지 않게 해준다.
                                             - TRUE로 지정한 경우에는 닫히게 된다.
                                             - 또한 여백을 눌러도 닫히게 된다. */
        builder.setMessage("\n"+msg+"\n"+"Back버튼 동작 안함" );// 메세지창의 내용
        builder.setPositiveButton("확인", null);  // 확인 버튼

        // 위에서 설정한 내용을 대화창으로 생성하고, 화면에 띄운다.
        AlertDialog alert = builder.create();
        alert.show();

        // AlertDialog의 가운데 정렬을 위한.. setting
        // Must call show() prior to fetching text view

        // 아래 부분을 주석처리하면 메세지는 좌측정렬된다.
//        TextView messageView = (TextView)alert.findViewById(android.R.id.message);
//        messageView.setGravity(Gravity.CENTER);
    }


    // 2번 경고창 : 내용만 있음. 메소드 오버로딩!
    public static void AlertShow(Context context, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);   // back버튼 누르면 대화창 닫힘
        builder.setMessage("\n"+msg+"\n"+"Back버튼으로 닫힘");
        builder.setPositiveButton("확인", null);

        AlertDialog alert = builder.create();
        alert.show();

        // AlertDialog의 가운데 정렬을 위한..setting
        // Must call show() prior to fetching text view

        // Alert창에 출력되는 메세지를 정렬할 때 아래 코드를 사용한다.(가운데정렬)
        TextView messageView = (TextView)alert.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);

    }

}
