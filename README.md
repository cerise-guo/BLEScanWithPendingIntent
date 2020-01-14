# BLE Scan with PendingIntent

This demo app uses Android BLE scan API "**[BluetoothLeScanner::startScan (filters, settings, callbackIntent)](http://https://developer.android.com/reference/android/bluetooth/le/BluetoothLeScanner#startScan(java.util.List%3Candroid.bluetooth.le.ScanFilter%3E,%20android.bluetooth.le.ScanSettings,%20android.app.PendingIntent))**"
 in background mode and provides my test results on 3 android phones.

Since this demo app is to estimate BLE discovery capability in background or doze mode, to avoid saving redundant data, any log within 5 sec interval will be ignored.

Log file can be pulled by adb command: </br> 
adb pull /storage/emulated/0/Android/data/com.ceriseguo.demofunc/files/<file-name>

BLE advertisements are issued every 1s by Nordic nRF52840 dongle.

# My Test Results
In all tests, app is in background, phone screen is off, phone faces off on table, no cable plug, no wireless charging.

## Pixel 3 with Android 10 ##
Tested 18.5 hours from 22:30PM to 17:09PM on 2nd day.
Battery consumption from 99% to 74%

Total 6480 BLE discovery events:</br>

- 5227 events <= 10 sec, 80.7%
- 1245 events > 10 sec & <= 30 sec, 19.2%
- 3 events > 30 sec & <= 60 sec, 0.1%
- 4 events > 60 sec & < 93 sec, 0.1%

Conclusion: 99.9% BLE discovery within 30 sec. </br>
[https://github.com/cerise-guo/BLEScanWithPendingIntent/blob/master/MyTestResult/Pixel3.JPG](https://github.com/cerise-guo/BLEScanWithPendingIntent/blob/master/MyTestResult/Pixel3.JPG)

## Samsung S9 with Android 8.0.0 ##
Tested 18 hours from 1:43AM to 20:14PM
Battery consumption from 71% to 53%

Total 13270 BLE discovery events:</br>

- 13264 events <= 10 sec, 99.96%
- 8 events > 10 sec & <= 45 sec, 0.04%

Conclusion: 99.9% BLE discovery within 10 sec.</br>
[https://github.com/cerise-guo/BLEScanWithPendingIntent/blob/master/MyTestResult/SamsungS9.JPG](https://github.com/cerise-guo/BLEScanWithPendingIntent/blob/master/MyTestResult/SamsungS9.JPG)

## Nexus 6P with Android 8.1.0 ##
Tested 18.5 hours from 22:30PM to 17:09PM on 2nd day.
Battery consumption from 99% to 74%

Total 13650 BLE discovery events:

- 13646 events <= 10 sec, 99.97%
- 3 events > 10 sec & <= 60 sec, 0.02%
- 1 event > 60 sec & <= 90 sec, 0.01%

Conclusion: 99.9% BLE discovery within 10 sec.</br>
[https://github.com/cerise-guo/BLEScanWithPendingIntent/blob/master/MyTestResult/Nexus6P.JPG](https://github.com/cerise-guo/BLEScanWithPendingIntent/blob/master/MyTestResult/Nexus6P.JPG)