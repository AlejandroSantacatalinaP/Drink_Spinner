package com.example.alex.drink_spinner;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Spinner;

import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView dtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This code means that the screen will be complete at the app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dtitle = (TextView)findViewById(R.id.dtitle);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/alittlepot.ttf");
        dtitle.setTypeface(custom_font);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<ConstrArray> arl = new ArrayList<ConstrArray>();
        arl.add(new ConstrArray("1", R.drawable.fletxa2_spinner));
        arl.add(new ConstrArray("2", R.drawable.fletxa3_spinner));
        arl.add(new ConstrArray("3", R.drawable.fletxa4_spinner));
        arl.add(new ConstrArray("4", R.drawable.fletxa5_spinner));
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(this,
                R.layout.row_spinner, R.id.txt, arl);
        mySpinner.setAdapter(adapter);
    }
}
