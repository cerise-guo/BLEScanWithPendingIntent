package com.ceriseguo.demofunc;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.peripheral.logger.SimpleLogger;

import java.util.ArrayList;

public class BLEDiscoveryReceiver extends BroadcastReceiver {

    final String TAG_NAME = "BLEDiscoveryReceiver";

    static long lastLogTime = 0;
    final static long LOG_INTERVAL_SEC = 5; //log every 5 sec

    @Override
    public void onReceive(Context context, Intent intent) {

        if( null == SimpleLogger.logPath()){
            Log.d(TAG_NAME, "init logger in BroadcastReceiver");
            SimpleLogger.getInstance().init(context);
        }

        int bleCallbackType = intent.getIntExtra(BluetoothLeScanner.EXTRA_CALLBACK_TYPE, -1);
        if (bleCallbackType != -1) {

            //Save log every 5 sec to avoid too many redundant data.
            final long currentSeconds = System.currentTimeMillis()/1000;
            if( 0 == lastLogTime ){
                lastLogTime = currentSeconds;
                Log.d(TAG_NAME, "init millisecond : " + currentSeconds);
            }
            else {
                if ((lastLogTime + LOG_INTERVAL_SEC) > currentSeconds) {
                    Log.d(TAG_NAME, "< 5s, no log : " + lastLogTime + " > " + currentSeconds);
                    return;
                } else {
                    lastLogTime = currentSeconds;
                }
            }

            SimpleLogger.getInstance().log(TAG_NAME, "background scan callback: " + bleCallbackType);

            ArrayList<ScanResult> scanResults = intent.getParcelableArrayListExtra(
                    BluetoothLeScanner.EXTRA_LIST_SCAN_RESULT);
            for ( ScanResult result : scanResults) {
                SimpleLogger.getInstance().log(TAG_NAME,
                        "scan result: " + result.getRssi() + " , " + result.getDevice().getName());
            }
        }
    }
}
