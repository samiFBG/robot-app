package com.example.robot;


import com.example.robot.bluetooth.DeviceListActivity;
import com.example.robot.bluetooth.CommunicationsTask;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    int countrevGauche = 0;
    int countrevDroit=0;
    String reverseGauche ="";
    String reverseDroit ="";
    TextView pmGauche ;
    TextView pmDroit ;
    int progresseDroit = 0;
    int progresseGauche = 0 ;
    TextView btetat ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent myIntent = getIntent(); // gets the previously created intent
        String firstKeyName = myIntent.getStringExtra("Connect"); // will return "FirstKeyValue"
        setContentView(R.layout.activity_app);
       pmgauche();
       pmdroit();
       if (firstKeyName == "true"){
           btetat = findViewById(R.id.btetat);
           btetat.setText("connecter");
       }else{
           btetat = findViewById(R.id.btetat);
           btetat.setText("deconnecter");
       }
    }
    public void pmdroit(){
        pmDroit = findViewById(R.id.pmdroit);
        SeekBar mdroit = findViewById(R.id.mdroit);
        mdroit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresseDroit = progress;
                pmDroit.setText(reverseDroit + progresseDroit);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
public void pmgauche(){
    pmGauche = findViewById(R.id.pmgauche);
    SeekBar mdgauche = findViewById(R.id.mgauche) ;
    mdgauche.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progresseGauche = progress;
            pmGauche.setText( reverseGauche + progresseGauche);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    });
}
    public  void revgauche(View v){
        if (countrevGauche == 0){
            countrevGauche = 1;
            reverseGauche="rev :";
        }else{
            countrevGauche = 0;
            reverseGauche="";
        }
        pmGauche.setText( reverseGauche + progresseGauche);
    }

    public  void revdroit(View v){

        if (countrevDroit== 0){
            countrevDroit = 1;
            reverseDroit="rev :";
        }else{
            countrevDroit = 0;
            reverseDroit ="";
        }
        pmDroit.setText(reverseDroit + progresseDroit);

    }
    public void connect(View v){
        Intent intent = new Intent(this, DeviceListActivity.class);
        startActivity(intent);
    }

}