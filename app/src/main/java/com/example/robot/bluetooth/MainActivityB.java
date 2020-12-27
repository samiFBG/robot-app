package com.example.robot.bluetooth;


import com.example.robot.R;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivityB extends AppCompatActivity {

    private String mDeviceAddress;
    protected CommunicationsTask mBluetoothConnection;

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
        setContentView(R.layout.activity_app);
        Intent newint = getIntent();
        mDeviceAddress = newint.getStringExtra(DeviceListActivity.EXTRA_ADDRESS);
        mBluetoothConnection = new CommunicationsTask(this, mDeviceAddress);
        mBluetoothConnection.execute();

        pmgauche();
        pmdroit();

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