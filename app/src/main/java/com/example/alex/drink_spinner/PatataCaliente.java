package com.example.alex.drink_spinner;

import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

import static com.example.alex.drink_spinner.Pantalla_Principal.rm;

/**
 * Created by Alex on 12/05/2017.
 */

public class PatataCaliente extends AppCompatActivity {
    Timer T;
    TextView tv;
    static GifImageView gifWarning;
    int count =0;
    Random random = new Random();
    Handler handler;
    boolean clickable = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijoc_patatacaliente);

        long startTime = System.currentTimeMillis();

        tv = (TextView)findViewById(R.id.txtPatataC);
        gifWarning = (GifImageView)findViewById(R.id.gifPatataCaliente);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickable=true;
                gifWarning.setBackgroundResource(R.drawable.gif2);
                tv.setVisibility(View.VISIBLE);
            }
        }, random.nextInt(15000 - 3000 + 1) + 3000);

        gifWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable){
                    PatataCaliente.super.onBackPressed();
                }
            }
        });
    }

    public static void girGif(){
        //Activate the gif during 2600ms
        gifWarning.postDelayed(new Runnable() {
            public void run() {
            }
        }, 2600);
    }
}
