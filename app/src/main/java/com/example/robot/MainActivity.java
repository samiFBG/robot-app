package com.example.robot;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int countrevgauche = 0;
    int countrevdroit=0;
    String reversegauche ="";
    String reversedroit ="";


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
    }

    public  void revgauche(View v){

        if (countrevgauche == 0){
            countrevgauche = 1;
            reversegauche ="rev:";
        }else{
            countrevgauche = 0;
            reversegauche ="";
        }

        TextView textView = findViewById(R.id.pgauche);
        textView.setText(reversegauche);
    }

    public  void revdroit(View v){

        if (countrevdroit == 0){
            countrevdroit = 1;
            reversedroit ="rev";
        }else{
            countrevdroit = 0;
            reversedroit ="";
        }
        TextView textView = findViewById(R.id.pdroit);
        textView.setText(reversedroit);
    }


}