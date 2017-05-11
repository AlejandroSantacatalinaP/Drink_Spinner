package com.example.alex.drink_spinner;

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
    Random rm = new Random();
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

        i= getIntent();
        numjug=i.getIntExtra("numj",999);
        img = i.getIntExtra("img",999);
        imgF = i.getIntExtra("fons",999);

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
                girar.setVisibility(View.VISIBLE);
                parar.setVisibility(View.INVISIBLE);
            }
        });
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
