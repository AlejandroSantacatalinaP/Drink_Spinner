package com.example.alex.drink_spinner;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alex on 26/05/2017.
 */

public class CronometroInverso extends AppCompatActivity {
    Button btnAcabar;
    TextView temps;
    int sec=00,min=00;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crono_invers);

        btnAcabar = (Button)findViewById(R.id.btn_acabar);
        temps = (TextView)findViewById(R.id.txtCrono);
        sec=00;
        min=00;

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {

                if(sec==59){
                    sec=00;
                    min++;
                    temps.setText(min+":"+sec);
                }else {
                    sec++;
                    temps.setText(min+":"+sec);
                }
            }

            public void onFinish() {
                CronometroInverso.super.onBackPressed();
            }
        }.start();
    }

}



