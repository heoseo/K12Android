package com.kosmo.a01helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 전화번호 입력상자를 전역적으로 사용하기 위해 선언
    private EditText editText;


    /*
    - Java에서의 출발점은 main()메소드였듯이,
        안드로이드의 출발점은 onCreate()메소드이다.
    - 수명주기(lifecyle) 메소드 중에서 첫번째로 실행된다.     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        - xml로 생성된 레이아웃을 액티비티에 전개(Inflate)하는 메소드로
            해당 메소드가 실행되지 않으면 아무것도 보이지 않는다.         */
        setContentView(R.layout.activity_main);

        /*
        - findViewById(res에 설정된 id)
            : xml에 설정된 id값을 통해 버튼 객체를 Java클래스로 가져와서 사용한다.         */
        Button btnNate = (Button)findViewById(R.id.btnNate);
        // 리스너 부착방법 1 : 버튼 객체에 직접 리스너 인터페이스를 사용한다.
        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                - 버튼을 눌렀을 떄 토스트 메세질르 띄워준다.
                - 토스트는 JS의 aler()와 비슷하게 메세지를 일정시간 보여준다.
                - 형식] Toast.makeTest(메세지를 띄워 컨텍스트(뷰),
                                        메세지내용,
                                        시간(Long or Shor)).show();                 */
                Toast.makeText(view.getContext(),
                                "잠시후 네이트로 이동합니다.",
                                Toast.LENGTH_LONG).show();
                /*
                - 인텐트를 통해 새로운 액티비티를 띄워준다.
                - 웹 URL이 전달되므로 외장 웹브라우저를 통해 네이트를 접속하게 된다.                 */
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://nate.com"));
                startActivity(intent);
            }
        }); ////btnNate 끝

        // 버튼객체 가져온 후 리스너 부착하기
        Button  btnCall = (Button)findViewById(R.id.btnCall);
        // 리스너 부착방법 2 : 외부에 선언된 리스너 객첼르 사용한다.
        btnCall.setOnClickListener(listener);

        Button  btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(listener);



        // 전화번호 입력상자 가져오기
        editText = (EditText) findViewById(R.id.editText);

    }////onCreate() 끝

    View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v){

            // 버튼을 누를 때 전달되는 View객체를 통해 눌러진 버튼을 알아낼 수 있다.
            if(v.getId() == R.id.btnCall){
                // EditText에 입력된 내용을 가져와서 문자열로 변경
                String callNumber = editText.getText().toString();
                // 인텐트를 통해 전달하면 전화걸기 앱이 실행된다.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + callNumber));
                startActivity(intent);
            }

            else if(v.getId() == R.id.btnNext){
                Intent intent = new Intent(v.getContext(), SubActivity.class);
                // SubActivity를 띄워준다.
                startActivity(intent);
            }
        }
    };  ////View.OnClickListener 끝



    /*
    리스너 부착방법4 : 버튼의 onClick속성을 이용한다.
                    이 때 호출할 메소드명을 기술하면 아래 함수를 즉시 호출할 수 있다.     */
    public void myBtnClick(View view){
        Toast.makeText(view.getContext(), "onClick속성을 이용한 버튼입니다. 저는 짧아욬",
                        Toast.LENGTH_SHORT).show();
    }//// myBtnClick() 끝


}//// MainActivity 끝