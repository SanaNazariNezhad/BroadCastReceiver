package org.maktab.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.maktab.broadcastreceiver.model.BroadCastLog;
import org.maktab.broadcastreceiver.receiver.ConnectionReceiver;
import org.maktab.broadcastreceiver.receiver.ScreenReceiver;
import org.maktab.broadcastreceiver.repository.BroadCastLogsDBRepository;
import org.maktab.broadcastreceiver.repository.IRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConnectionReceiver mConnectionReceiver;
    private ScreenReceiver mScreenReceiver;
    private TextView mTextViewLogcat;
    private MutableLiveData<List<BroadCastLog>> mLiveDataLogcat;
    private IRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScreenReceiver = new ScreenReceiver();
        mConnectionReceiver = new ConnectionReceiver();
        mRepository = BroadCastLogsDBRepository.getInstance(getApplicationContext());
        mLiveDataLogcat = mRepository.getLiveDataLogcat();

        getPermission();
        findView();
        observers();

    }

    private void observers() {
        mLiveDataLogcat.observe(this, new Observer<List<BroadCastLog>>() {
            @Override
            public void onChanged(List<BroadCastLog> broadCastLogs) {
                StringBuilder stringLogcat = new StringBuilder();
                List<BroadCastLog> logList = mRepository.getBroadCastLog();
                for (int index = 0; index < logList.size(); index++) {
                    stringLogcat = new StringBuilder(logList.get(index).getPrimaryId() + "\t\t" +
                            logList.get(index).getEvent() + "\t\t" +
                            logList.get(index).getType() + "\t\t" +
                            logList.get(index).getTimestamp() + "\n\n");
                }
                mTextViewLogcat.setText(stringLogcat);
            }
        });
    }

    private void findView() {
        mTextViewLogcat = findViewById(R.id.textView_logcat);
    }

    private void getPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECEIVE_SMS},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(MainActivity.this, "Permission denied to read your Phone State", Toast.LENGTH_SHORT).show();
            }
            if (grantResults.length > 0
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(MainActivity.this, "Permission denied to read your SMS State", Toast.LENGTH_SHORT).show();
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenReceiver, intentFilter);

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(mConnectionReceiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mScreenReceiver);
        unregisterReceiver(mConnectionReceiver);
    }
}