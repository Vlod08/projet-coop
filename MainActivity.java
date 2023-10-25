package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView label;
    Button con_btn;
    Button button;
    BluetoothAdapter bth;
    ListView bondedList;
    int discoveryresult;


    BroadcastReceiver statusBT = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(bth.ACTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, bth.ERROR);
                check_status();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        label = findViewById(R.id.lbl);
        button = findViewById(R.id.check_status);
        con_btn = findViewById(R.id.con);
        bth = BluetoothAdapter.getDefaultAdapter();
        discoveryresult = 1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void check_status() {
        if (bth.isEnabled()) {
            label.setText("Bluetooth is enabled");
        } else {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            IntentFilter enableBTFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(statusBT, enableBTFilter);

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "BLUETOOTH_CONNECT permission denied", Toast.LENGTH_LONG).show();
                return;
            }
            startActivity(enableBT);
        }
    }

    public void connect() {

    }

    public void update_list() {

        check_status();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Set<BluetoothDevice> bondedDevices = bth.getBondedDevices();



    }

}
