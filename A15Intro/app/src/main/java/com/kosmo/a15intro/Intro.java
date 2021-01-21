package com.kosmo.a15intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
fade_in 페이드인 효과
    : 투명상테에서 선명한 상태로 변환됨
fade_out 페이드아웃 효과
    : 선명한 상태에서 투명상태로 변환됨
slide_in_down
    : 화면 아래쪽에서 위쪽으로 액티비티가 들어오는 효과
slide_in_left
    : 화면 왼쪽에서 오른쪽으로 액티비티가 들어오는 효과
slide_in_right
    : 화면 오른쪽에서 왼쪽으로 액티비티가 들어오는 효과
slide_in_up
    : 화면 위쪽에서 아래쪽으로 액티비티가 들어오는 효과
hold 움직임없음 효과
    : 인트로 실행시 사라지는 액티비텡 적용할 애니메이션.
    두 개의 액티비티에 애니메이션을 적용하더라도 사라지는 액티비티의 효과는 거의 눈에 보이지 않는다.

 */
public class Intro extends AppCompatActivity {

    /*
    - Manifest.xml파일을 수정하여 Intro액티비티가 가장 먼저 실행되도록 한다.     */
    // 일정시간 이후에 실행하기 위해 Handler객체를 실행한다.
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 인트로 화면 이후에 메인액티비티를 실행하기 위해 인텐트 객체 생성
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            // 액티비티 실행
            startActivity(intent);

            /*
            - 액티비티가 실행되거나 종료될대 애니메이션 효과를 부여한다.
            - 인자1 : 새롭게 실행되는 액티비티의 애니메이션
            - 인자2 : 종료되는 액티비티에 적용             */
            overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            // 인트로 액티비티를 종료한다.
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

    }

    // 액티비티 실행시 3번째로 실행되는 수명주기 함수
    @Override
    protected void onResume() {
        // Intro화면에 진입한 후 2초 후에 runnable객체를 실행한다.
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    // 액티비티 종료시 첫번째로 실행되는 수명주기 함수
    @Override
    protected void onPause() {
        // Intro가 종료될 때 handler에 예약해놓은 작업을 취소한다.
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}