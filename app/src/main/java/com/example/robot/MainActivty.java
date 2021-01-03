package com.example.robot;





import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.robot.bluetooth.CommunicationsActivity;

public class MainActivty extends CommunicationsActivity {

    private static final String TAG = "test";
    int countrevGauche = 0;
    int countrevDroit=0;
    String reverseGauche ="";
    String reverseDroit ="";
    TextView pmGauche ;
    TextView pmDroit ;
    int progresseDroit = 0;
    int progresseGauche = 0 ;
    TextView btetat ;
    private String btstate;
int droit ;
int gauche ;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        btetat = findViewById(R.id.btetat);
        super.onCreate(savedInstanceState);

          Intent newint = getIntent();
              btetat = findViewById(R.id.btetat);
              btetat.setText("Connecter");



       pmgauche();
       pmdroit();

    }







    public void pmdroit(){
        pmDroit = findViewById(R.id.pmdroit);
        SeekBar mdroit = findViewById(R.id.mdroit);
        mdroit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                droit = 1 ;
                progresseDroit = progress;
                pmDroit.setText(reverseDroit + progresseDroit);

                if (fromUser==true) {
                    for (byte b : String.valueOf(droit).getBytes()) {

                        mBluetoothConnection.write(b);
                    }
                    for (byte b : String.valueOf(countrevDroit).getBytes()) {

                        mBluetoothConnection.write(b);
                    }
                    for (byte b : String.format("%03d",progress).getBytes()) {

                        mBluetoothConnection.write(b);
                    }


                    mBluetoothConnection.write((byte)'.');
                }
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
            gauche = 0;

            progresseGauche = progress;
            pmGauche.setText( reverseGauche + progresseGauche);
            if (fromUser==true) {

                for (byte b : String.valueOf(gauche).getBytes()) {

                    mBluetoothConnection.write(b);
                }

                for (byte b : String.valueOf(countrevGauche).getBytes()) {

                    mBluetoothConnection.write(b);
                }
                for (byte b : String.format("%03d",progress).getBytes()) {

                    mBluetoothConnection.write(b);
                }


                mBluetoothConnection.write((byte)'.');
            }

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

}