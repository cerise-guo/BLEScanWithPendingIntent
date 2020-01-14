package com.ceriseguo.demofunc;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.peripheral.logger.SimpleLogger;

public class MainActivity extends AppCompatActivity {

    public final static String TAG_NAME = "DemoFunc";

    private BLEManager bleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBLEPermission();

        SimpleLogger.getInstance().init(this);
        bleManager = new BLEManager(this);
    }

    public void onClickA( View view)
    {
        //Log.d(TAG_NAME, "Enter onClickA : start scan");
        SimpleLogger.getInstance().log(TAG_NAME, "Enter onClickA : start scan");

        bleManager.startScan();
    }


    public void onClickB( View view)
    {
        SimpleLogger.getInstance().log(TAG_NAME, "Enter onClickB : stop scan");

        bleManager.stopScan();
    }


    public void onClickC( View view)
    {
        SimpleLogger.getInstance().log(TAG_NAME, "Enter onClickC");
    }

    private void checkBLEPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            }
        }

        BluetoothAdapter btAdapter = ((BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
        if (btAdapter != null && !btAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,100);
        }
    }
}
