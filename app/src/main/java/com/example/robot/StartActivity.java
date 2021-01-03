package com.example.robot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.robot.bluetooth.DeviceListActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

            public void start(View v) {
                Intent intent = new Intent(this, DeviceListActivity.class);
                startActivity(intent);

    }

}