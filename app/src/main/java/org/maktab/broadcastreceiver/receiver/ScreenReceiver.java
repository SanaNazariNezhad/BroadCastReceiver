package org.maktab.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.maktab.broadcastreceiver.model.BroadCastLog;
import org.maktab.broadcastreceiver.repository.BroadCastLogsDBRepository;
import org.maktab.broadcastreceiver.repository.IRepository;

public class ScreenReceiver extends BroadcastReceiver {
    private static final String TAG = "ScreenReceiver";
    private IRepository mIRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        mIRepository = BroadCastLogsDBRepository.getInstance(context);

        String state = intent.getAction();
        if (state.equalsIgnoreCase(Intent.ACTION_SCREEN_ON)){
//            Log.d(TAG, "Screen On received intent: " + intent.getAction());
//            Toast.makeText(context, "Screen On received",
//                    Toast.LENGTH_LONG).show();

            Long timestampLong = System.currentTimeMillis()/1000;
            String timestamp = timestampLong.toString();
            BroadCastLog broadCastLog = new BroadCastLog("Screen","On",timestamp);
            mIRepository.insertBroadCastLog(broadCastLog);

        }else if (state.equalsIgnoreCase(Intent.ACTION_SCREEN_OFF)){
//            Log.d(TAG, "Screen Off received intent: " + intent.getAction());
//            Toast.makeText(context, "Screen Off received",
//                    Toast.LENGTH_LONG).show();

            Long timestampLong = System.currentTimeMillis()/1000;
            String timestamp = timestampLong.toString();
            BroadCastLog broadCastLog = new BroadCastLog("Screen","Off",timestamp);
            mIRepository.insertBroadCastLog(broadCastLog);
        }
    }
}