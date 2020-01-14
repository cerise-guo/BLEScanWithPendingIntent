package com.ceriseguo.demofunc;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.peripheral.logger.SimpleLogger;

import java.util.ArrayList;
import java.util.List;

public class BLEManager extends BluetoothGattCallback {

    final static String TAG_NAME = "BLEManager";

    final private String TARGET_DEVICE_NAME = "Nordic_Blinky";

    private Context mContext;

    BluetoothAdapter btAdapter = null;

    public BLEManager( Context context ){

        mContext = context;

        btAdapter =
                ((BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();

        if (null == btAdapter || !btAdapter.isEnabled()) {
            SimpleLogger.getInstance().log(TAG_NAME, "failed to get bt adapter");
            throw new RuntimeException("Failed to get BLE adapter");
        }

    }

    public void startScan(){
        SimpleLogger.getInstance().log(TAG_NAME, "start scan");
        List<ScanFilter> filters = new ArrayList<>();

        ScanFilter nameFilter = new ScanFilter.Builder().setDeviceName(TARGET_DEVICE_NAME).build();
        filters.add( nameFilter );

        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
                .setNumOfMatches(ScanSettings.MATCH_NUM_FEW_ADVERTISEMENT)
                //.setNumOfMatches(ScanSettings.MATCH_NUM_MAX_ADVERTISEMENT)
                //CALLBACK_TYPE_FIRST_MATCHE is not supported by Samsung phones
                //.setCallbackType(ScanSettings.CALLBACK_TYPE_FIRST_MATCH)
                .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                .build();

        Intent intent = new Intent(mContext, BLEDiscoveryReceiver.class);
        intent.putExtra("o-scan", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        btAdapter.getBluetoothLeScanner().startScan(filters, settings, pendingIntent);
    }

    public void stopScan(){
        SimpleLogger.getInstance().log(TAG_NAME, "stop scan");

        Intent intent = new Intent(mContext, BLEDiscoveryReceiver.class);
        intent.putExtra("o-scan", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        btAdapter.getBluetoothLeScanner().stopScan(pendingIntent);
    }

}
