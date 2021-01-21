package com.kosmo.a12alertdailog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 목록형 대화상자에서 사용
    private String[] sports = {"축구", "야구", "배구", "농구"};
    // 체크박스, 라디오 대화상자에서 사용
    private String[] grilGroup = {"트와이스", "AOA", "모모랜드", "블랙핑크"};
    // 선택한 라디오 항목의 인덱스
    private int radio_index = -1;
    // 선택한 체크박스 항목 저장
    boolean[] which_checks = {false, false, true, true};
    // 진행대화상자 객체 생성
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 버튼 위젯 가져오기
        Button btnAlertBasic = (Button)findViewById(R.id.btn_alert_basic);
        Button btnAlertCheck = (Button)findViewById(R.id.btn_alert_checkbox);
        Button btnAlertRadio = (Button)findViewById(R.id.btn_alert_radio);
        Button btnAlertList = (Button)findViewById(R.id.btn_alert_list);
        Button btnAlertProgress = (Button)findViewById(R.id.btn_alert_progress);
        Button btnCustom = (Button)findViewById(R.id.btn_alert_custom);


        // 1. 기본대화상자
        btnAlertBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertBasic = new AlertDialog.Builder(view.getContext());

                alertBasic.setCancelable(false);
                alertBasic.setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("대화상자제목")
                        .setMessage("여기는 메세지가 들어갑니다")
                        .setPositiveButton("확인버튼",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Toast.makeText(MainActivity.this, "확인클릭합니다", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .setNegativeButton("취소버튼",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Toast.makeText(MainActivity.this, "취소클릭합니다", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .show();

            }
        });


        /* 2. 목록형 대화상자
                : 항목중 하나만 생성간으함
             .setItems(목록에 출력할 배열, 리스너)
                : 배열이 항목에 출력되고 항목을 클릭할 경우 바로 리스너가 감지하여 콜백한다.
                : 콜백함수쪽으로 선택한 항목의 인덱스가 전달된다.         */
        btnAlertList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertBasic = new AlertDialog.Builder(view.getContext());

                alertBasic.setCancelable(false);
                alertBasic.setIcon(android.R.drawable.ic_lock_power_off)
                        .setTitle("당신이 좋아하는 스포츠는?")
                        .setItems(sports,
                                    new DialogInterface.OnClickListener() {
                                        // 항목을 클릭할대 즉시 호출됨. i를 통해 인덱스가 전달됨.
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {
                                            Toast.makeText(MainActivity.this, sports[which], Toast.LENGTH_SHORT).show();
                                        }
                                    })
                        .setNegativeButton("취소",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {
                                                    Toast.makeText(MainActivity.this, "목록취소함", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .show();

            }
        });

        /* 3. 라디오형 대화상자 - 항목중 하나만 선택가능
                .setSingleChoiceItems(배열, 디폴트로 선택될 인덱스, 리스너)
                    : 두번째 매개변수를 마이너스값으로 지정하면 선택항목이 없는 상태로 대화창이 설정된다.
         */
        btnAlertRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setIcon(android.R.drawable.ic_dialog_email)
                        .setTitle("당신이 좋아하는 걸그룹은?")
                        .setSingleChoiceItems(grilGroup, radio_index,
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int which) {
                                                        // 라디오 항목을 선택했을 때 인덱스를 변수에 저장한다.
                                                        radio_index = which;
                                                    }
                                                })
                        .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {
                                                    Toast.makeText(MainActivity.this, grilGroup[radio_index], Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .setNegativeButton("CANCEL",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {
                                                    Toast.makeText(MainActivity.this, "취소하셨습니다", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .show();

            }
        });


        /* 4. 체크박스형 대화상자
                : 여러개 항목 선택가능
                .setMultiChoiceItems(배열, 디폴트로 선택될 항목의 boolean값을 담은 배열, 리스너)
                ※ 현재 멤버변수로 which_checks가 선언되어있음         */
        btnAlertCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(view.getContext())
                        .setIcon(android.R.drawable.ic_dialog_dialer)
                        .setTitle("당신이 좋아하는 걸그룹은?(여러개선택)")
                        .setMultiChoiceItems(grilGroup, which_checks,
                                            new DialogInterface.OnMultiChoiceClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                    /*
                                                    - i : 선택한 체크박스의 인덱스
                                                    - b : 선택한 항목의 체크여부(boolean) 체크했을 떄 true가 전달된다.                                         */
                                                    Toast.makeText(MainActivity.this, String.format("which:%d, isChecked:%b", i, b), Toast.LENGTH_SHORT).show();
                                                    which_checks[i] = b;
                                                }
                                            })
                        .setPositiveButton("예쓰~",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {

                                                    // 선택한 체크 항목을 StringBuffer클래스로 저장한다.
                                                    StringBuffer buf = new StringBuffer();
                                                    for(int i = 0; i < grilGroup.length; i++){
                                                        if(which_checks[i] == true)
                                                            buf.append(grilGroup[i]  + " ");
                                                    }

                                                    Toast.makeText(MainActivity.this, buf, Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .setNegativeButton("노우~",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {
                                                    // 취소(노우)버튼 클릭했을 때...
                                                    Toast.makeText(MainActivity.this, "노우~버튼을 클릭하였습니다", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                        .show();

            }
        });





        // 5. 프로그레스 다이얼로그 ProgressDialog
        // 진행대화창 객체생성
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  // 로딩이미지
        progressDialog.setIcon(android.R.drawable.ic_menu_day);         // 시스템의 기본아이콘 사용
        progressDialog.setTitle("KOSMO여러분들");
        progressDialog.setMessage("열공하는 중입니다. 조용히 합시다.");

        // 진행 대화상자 띄우기
        btnAlertProgress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 버튼을 눌렀을 때 대화상자가 열러있지 않을때만 보임처리 한다.
                if(!progressDialog.isShowing())
                    progressDialog.show();

                // 2초가 대기 후 진행창 닫기
                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();   // 열린상태이면 닫아준다.
                                        //progress.cancel();        // 무조건 닫아준다.
                                    }
                                }, 2000 );
            }
        });




        /* 6. 커스텀 대화상자
                1) layout폴더에 사용자가 정의한 대화상자 xml파일을 생성한다.
                2) infalte()를 통해 레이아웃을 인플레이트(전개)한다.
                3) Builder객체를 통해 대화창을 설정할 떄 setView()생성자를 통해
                    2번에서 전개한 레이아웃을 대화상자에 적용한다.         */
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //커스텀뷰로 레이아웃 전개함(인플레이션)
                View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);

                final EditText sportTxt = (EditText)view.findViewById(R.id.sport_txt);

                new AlertDialog.Builder(v.getContext())
                        .setView(view)
                        .setIcon(android.R.drawable.ic_media_play)
                        .setTitle("커스텀대화상자")
                        .setPositiveButton("화긴",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        // 확인을 눌렀을 때는 입력한 내용을 출력한다.
                                        Toast.makeText(MainActivity.this, sportTxt.getText(), Toast.LENGTH_LONG).show();
                                    }
                                })
                        .setNegativeButton("치소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        Toast.makeText(MainActivity.this, "취소를 눌렀습습니다", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .show();

            }
        });
    }
}