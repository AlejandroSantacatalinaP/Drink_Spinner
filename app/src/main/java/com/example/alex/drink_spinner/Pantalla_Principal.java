package com.example.alex.drink_spinner;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Alex on 10/05/2017.
 */

public class Pantalla_Principal extends AppCompatActivity {
    static ImageView arrow, base;
    EditText dnumJug;
    static Button parar, girar;
    static ImageView imgf;
    int numjug, img, imgF;
    Intent i;
    boolean minijocs;
    String imita[];
    static Random rm = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);
        //This code means that the screen will be complete at the app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        arrow = (ImageView)findViewById(R.id.imgArrow);
        base = (ImageView)findViewById(R.id.imgBase);
        girar = (Button)findViewById(R.id.buttonGirar);
        parar = (Button)findViewById(R.id.buttonParar);
        dnumJug = (EditText)findViewById(R.id.dNumJug);
        imgf = (ImageView) findViewById(R.id.imgf);


        changeLanguage();
        Imitar();

        i= getIntent();
        numjug=i.getIntExtra("numj",999);
        img = i.getIntExtra("img",999);
        imgF = i.getIntExtra("fons",999);
        minijocs= i.getBooleanExtra("minijocs",false);


        CanviarFletxa(img);
        CanviarFons(imgF);

        girar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rotate(arrow);
                girar.setVisibility(View.INVISIBLE);
                parar.setVisibility(View.VISIBLE);
            }
        });
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrow.clearAnimation();
                PararFletxa(numjug);
                girar.setVisibility(View.VISIBLE);
                parar.setVisibility(View.INVISIBLE);
                if(minijocs){

                    ElegirMinijoc();
                }
            }
        });
    }
    public void ElegirMinijoc(){
        int valor= 10;  //Numero de minijocs
        switch(rm.nextInt(valor+1)){
            case 0:
                dialog(R.string.minijoc,getString(R.string.Descrminij_patataC),0);
                break;
            case 1:
                dialog(R.string.minijoc,getString(R.string.Descrminij_imita)+imita[rm.nextInt(5)],1);
                break;
            case 2:
                dialog(R.string.minijoc,getString(R.string.Descrminij_escondite),2);
                break;
        }

    }
    public void Imitar(){
        imita = new String[5];
        imita[0]=getString(R.string.animalfav);
        imita[1]=getString(R.string.jugadorDerecha);
        imita[2]=getString(R.string.jugadorIzquierda);
        imita[3]=getString(R.string.ppf);
        imita[4]=getString(R.string.afo);
    }
    public void PararFletxa(int nj){
        int antes=1;
        int gxj = 360/nj;
        Random rm = new Random();
        int rand = rm.nextInt(360);
        for (int i=gxj; i<=360; i+=gxj){

            if((rand==antes || rand>antes) &&(rand<i)){

                arrow.setRotation(i-antes);

            }else{
                antes = i;
            }

        }


    }
    private void dialog(int title, String message,final int minij) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (minij == 0) {
                            Intent minijoc = new Intent(Pantalla_Principal.this, PatataCaliente.class);
                            startActivity(minijoc);
                        }else if (minij == 2){
                            Intent minijoc = new Intent(Pantalla_Principal.this, CronometroInverso.class);
                            startActivity(minijoc);
                        }
                        dialog.cancel();
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void Rotate (ImageView img){
        Random rm = new Random();
        //This method make spin the spinner. We have to indicate the speed.
        float ROTATE_FROM = 0.0f;
        final float ROTATE_TO = -10.0f * 360.0f;
        RotateAnimation r = new RotateAnimation(ROTATE_FROM, ROTATE_TO,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration(2000);
        r.setRepeatCount(Animation.INFINITE);
        r.setRepeatMode(Animation.RESTART);
        r.setInterpolator(getApplicationContext(), android.R.anim.linear_interpolator);
        img.startAnimation(r);

    }
    public static void CanviarFletxa(int caso){
        switch (caso){
            case 1:
                arrow.setBackgroundResource(R.drawable.fletxa3);
                break;
            case 2:
                arrow.setBackgroundResource(R.drawable.fletxa4);
                break;
            case 3:
                arrow.setBackgroundResource(R.drawable.fletxa5);
                break;
            case 4:
                arrow.setBackgroundResource(R.drawable.fletxa6);
                break;
            case 5:
                arrow.setBackgroundResource(R.drawable.fletxa7);
                break;
        }

    }

    public static void CanviarFons(int caso){
        switch (caso){
            case 0:
                imgf.setBackgroundResource(R.drawable.madera_defons);
                break;
            case 1:
                imgf.setBackgroundResource(R.drawable.marmol_defons);
                break;
            case 2:
                imgf.setBackgroundResource(R.drawable.fons);
                break;
        }

    }
    public static void changeLanguage() {
        //Detects the current phone language and adapts the app language
        // and use the title photo according to the language
        String language = Locale.getDefault().getDisplayLanguage();
        Locale localizacion;
        Configuration config = new Configuration();
        if (language.startsWith("es")) {
            localizacion = new Locale("es", "ES");
            Locale.setDefault(localizacion);
            config.setLocale(localizacion);
            base.setBackgroundResource(R.drawable.inserte_es);

        }
        else if (language.startsWith("ca")) {
            localizacion = new Locale("cat", "CAT");
            Locale.setDefault(localizacion);
            config.setLocale(localizacion);

        }else{
            localizacion = new Locale("en", "EN");
            Locale.setDefault(localizacion);
            config.setLocale(localizacion);
            base.setBackgroundResource(R.drawable.inserte_cat);
            girar.setBackgroundResource(R.drawable.spin);
            parar.setBackgroundResource(R.drawable.stop);
        }
    }
}
