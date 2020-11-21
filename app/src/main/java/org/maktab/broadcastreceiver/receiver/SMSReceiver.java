package org.maktab.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.maktab.broadcastreceiver.model.BroadCastLog;
import org.maktab.broadcastreceiver.repository.BroadCastLogsDBRepository;
import org.maktab.broadcastreceiver.repository.IRepository;

public class SMSReceiver extends BroadcastReceiver {
    private IRepository mIRepository;

    private static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        mIRepository = BroadCastLogsDBRepository.getInstance(context);

//        Log.d(TAG, "SMS received intent: " + intent.getAction());
//        Toast.makeText(context, "SMS received",
//                Toast.LENGTH_LONG).show();

        Long timestampLong = System.currentTimeMillis()/1000;
        String timestamp = timestampLong.toString();
        BroadCastLog broadCastLog = new BroadCastLog("SMS","In",timestamp);
        mIRepository.insertBroadCastLog(broadCastLog);

    }
}
