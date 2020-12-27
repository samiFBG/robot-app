package com.example.robot;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class Bluetooth extends AppCompatActivity {

    private BluetoothAdapter bAdapter = null;
    private BluetoothClient bluetoothClient = null;
    private List<BluetoothDevice> knownDevices = null;
    private ListView listView;


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_ON:

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        bAdapter.enable();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:

                        break;
                }
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        setContentView(R.layout.listscan);



        listView = findViewById( R.id.listname);

        bAdapter = BluetoothAdapter.getDefaultAdapter();


        knownDevices = new ArrayList<>( bAdapter.getBondedDevices() );
        ArrayAdapter<BluetoothDevice> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, knownDevices);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener( deviceListListener );
    }

    private ListView.OnItemClickListener deviceListListener = new ListView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> adapter, View view, int arg2, long rowId) {
            BluetoothDevice device = knownDevices.get( (int) rowId );
            bluetoothClient = new BluetoothClient( device );
        }
    };


    private class BluetoothClient extends Thread {

        private BluetoothDevice bluetoothDevice;
        private BluetoothSocket bluetoothSocket;

        public BluetoothClient( BluetoothDevice device ) {
           bluetoothDevice = device ;
            try {
                bAdapter = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                bluetoothSocket.connect();//start connection


            } catch ( IOException exception ) {
                Log.e( "DEBUG", "Cannot establish connection", exception );
            }



        }


    }
}


