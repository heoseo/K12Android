package com.kosmo.a08datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // 멤버변수
    Calendar calendar;                  // 캘린더 클래스(시간, 날짜 생성)
    TextView date_tv, time_tv;          // 텍스트뷰(시간, 날짜 표시)

    int yearStr, monthStr, dayStr;      // 현재 날짜
    int hourStr, minuteStr, secondStr;  // 현재 시간


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 텍스트뷰 가져오기
        date_tv = (TextView)findViewById(R.id.date_tv);
        time_tv = (TextView)findViewById(R.id.time_tv);

        // 날짜와 시간을 다루는 클래스면 무엇이든 사용가능
        calendar = Calendar.getInstance()  ;        // 생성자가 없느느 클래스이므로 유틸메소드로 객체 생성

        yearStr = calendar.get(calendar.YEAR);
        monthStr = calendar.get(calendar.MONTH);
        dayStr = calendar.get(calendar.DATE);

        hourStr = calendar.get(calendar.HOUR_OF_DAY);
        minuteStr = calendar.get(calendar.MINUTE);
        secondStr = calendar.get(calendar.SECOND);

        // 현재 시간, 날짜를 텍스트뷰에 설정
        date_tv.setText(yearStr + "년 " + (monthStr+1)+"월 " + dayStr + "일");
        time_tv.setText(hourStr + "시 " + minuteStr+"분 " + secondStr+"초");



        // 날짜 선택 버튼에 리스너 부착
        Button btn_datepicker = (Button)findViewById(R.id.btn_datepicker);
        btn_datepicker.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    /*
                    버튼을 눌렀을 때 데이트피커 대화창을 띄워준다.
                    - 형식]
                        new DatePickerDialog(
                            대화창을 띄울 컨텍스트, 리스너, 설정할 년, 월, 일);

                        리스너는 내부 외부 어디든 설정 가능함.
                     */
                    DatePickerDialog dialog = new DatePickerDialog(view.getContext(), listener,
                            yearStr, monthStr, dayStr);
                    dialog.show();  // 객체  생성후 show()함수를 통해 출력한다.
                }
            }
        );


        Button btn_timepicker = (Button)findViewById(R.id.btn_timepicker);
        btn_timepicker.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        /*
                        버튼 클릭시 타임피커 대화창을 띄워준다.
                        - 형식]
                            new TimePickerDialog(컨텍스트, 리스터, 설정할 시간, 분, 24시간포맷);

                            : 24시간 포맷은 true(24 : 24시간제) / flase(12시간제)                         */
                        new TimePickerDialog(view.getContext(),
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                                    // 선택한 시간을 텍스트뷰와 토스트로 출력한다.
                                    time_tv.setText(String.format("설정된 시간%n%d시 %d분", hourOfDay, minute));

                                    Toast.makeText(getApplicationContext(),
                                                    String.format("설정된 시간%n%d시 %d분", hourOfDay, minute),
                                                    Toast.LENGTH_LONG).show();
                                }

                            },
                            hourStr,
                            minuteStr,
                            true
                        ).show();

                    }
                }
        );

    }//// onCreate() 끝

    // 데이트피커에서 날짜를 선택한 후 확인버튼을 눌렀을 때의 리스너 선언
    private DatePickerDialog.OnDateSetListener listener =
    new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            // 선택한 날짜를 텍스트뷰에 설정함
            date_tv.setText(String.format("설정된 날짜%n%d년 %d월 %d일",
                                        year, (month+1), dayOfMonth));

            /*
            - Calendar클래스를 통해 우러을 반환받으면 0~11까지 이므로  +! 해줘야 현재 월이 출력됨.             */
            Toast.makeText(getApplicationContext(),
                            year+"년 " + (month+1)+"월 " + dayOfMonth+"일",
                            Toast.LENGTH_LONG).show();
        }
    };
}