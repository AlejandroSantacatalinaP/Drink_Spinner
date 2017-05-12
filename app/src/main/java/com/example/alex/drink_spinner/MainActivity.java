package com.example.alex.drink_spinner;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    static ImageView dtitle;
    TextView txtnj, txtefl, txtefons, txtModeMini;
    Spinner mySpinner, mySpinner2;
    Switch sw;
    EditText edt;
    static Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This code means that the screen will be complete at the app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dtitle = (ImageView)findViewById(R.id.dtitle);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        txtnj = (TextView)findViewById(R.id.txtNumJ);
        txtefl = (TextView)findViewById(R.id.txtElegFletx);
        btn = (Button)findViewById(R.id.btn_seguent);
        edt = (EditText)findViewById(R.id.dNumJug);
        txtefons = (TextView)findViewById(R.id.txtElegFons);
        mySpinner2 = (Spinner)findViewById(R.id.spinner2);
        txtModeMini = (TextView)findViewById(R.id.txtModeMini);
        sw = (Switch) findViewById(R.id.switch1);

        changeLanguage();

        //It change the font family to another (external font family)
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/alittlepot.ttf");
        txtefl.setTypeface(custom_font);
        txtnj.setTypeface(custom_font);
        txtefons.setTypeface(custom_font);
        txtModeMini.setTypeface(custom_font);

        ArrayList<ConstrArray> arl = new ArrayList<ConstrArray>();
        arl.add(new ConstrArray("1", R.drawable.fletxa2_spinner));
        arl.add(new ConstrArray("2", R.drawable.fletxa3_spinner));
        arl.add(new ConstrArray("3", R.drawable.fletxa4_spinner));
        arl.add(new ConstrArray("4", R.drawable.fletxa5_spinner));
        arl.add(new ConstrArray("5", R.drawable.fletxa6_spinner));
        arl.add(new ConstrArray("6", R.drawable.fletxa7_spinner));

        ArrayList<ConstrArray> arl2 = new ArrayList<ConstrArray>();
        arl2.add(new ConstrArray("1", R.drawable.fonsmadera));
        arl2.add(new ConstrArray("2", R.drawable.fonsmarmol));
        arl2.add(new ConstrArray("3", R.drawable.fons2));

        SpinnerAdapter adapter = new SpinnerAdapter(this,
                R.layout.row_spinner, R.id.txt, arl);
        mySpinner.setAdapter(adapter);

        SpinnerAdapter adapter2 = new SpinnerAdapter(this,
                R.layout.row_spinner, R.id.txt, arl2);
        mySpinner2.setAdapter(adapter2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt.getText().toString().isEmpty()){
                    if(Integer.valueOf(edt.getText().toString())<= 16) {
                        Intent principal = new Intent(MainActivity.this, Pantalla_Principal.class);
                        principal.putExtra("numj", Integer.valueOf(edt.getText().toString()));
                        principal.putExtra("img", mySpinner.getSelectedItemPosition());
                        principal.putExtra("fons", mySpinner2.getSelectedItemPosition());
                        if (!sw.isChecked()){
                            principal.putExtra("minijocs", false);
                        }else
                        {principal.putExtra("minijocs", true);}
                        startActivity(principal);
                    }else{
                        Toast.makeText(MainActivity.this,R.string.camp,Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,R.string.camp,Toast.LENGTH_LONG).show();
                }
            }
        });
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
            dtitle.setBackgroundResource(R.drawable.tit_es);
            btn.setBackgroundResource(R.drawable.empezar_btn);
        }
        else if (language.startsWith("ca")) {
            localizacion = new Locale("cat", "CAT");
            Locale.setDefault(localizacion);
            config.setLocale(localizacion);
            dtitle.setBackgroundResource(R.drawable.tit_cat);
            btn.setBackgroundResource(R.drawable.comenca_btn);
        }else{
            localizacion = new Locale("en", "EN");
            Locale.setDefault(localizacion);
            config.setLocale(localizacion);
            dtitle.setBackgroundResource(R.drawable.tit_en);
            btn.setBackgroundResource(R.drawable.start_btn);
        }
    }
}
