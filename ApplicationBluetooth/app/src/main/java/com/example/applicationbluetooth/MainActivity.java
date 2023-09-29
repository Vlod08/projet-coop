package com.example.applicationbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

class MainActivity extends AppCompatActivity {

    TextView status;
    Button checkOn;
    BluetoothAdapter bt;

    @Override
    protected void onCreate(Bundle b){
        status = findViewById(R.id.statusView);
        checkOn = findViewById(R.id.checkOn);
        bt = BluetoothAdapter.getDefaultAdapter();
        checkOn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(bt.isEnabled()){
                    status.setText("Bluetooth is on");
                }

                else{status.setText("Bluetooth is off");}

            }
        });
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        ;}



}