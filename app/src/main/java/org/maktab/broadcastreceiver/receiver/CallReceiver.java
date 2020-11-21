package org.maktab.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import org.maktab.broadcastreceiver.model.BroadCastLog;
import org.maktab.broadcastreceiver.repository.BroadCastLogsDBRepository;
import org.maktab.broadcastreceiver.repository.IRepository;

public class CallReceiver extends BroadcastReceiver {
    private IRepository mIRepository;

    @Override
    public void onReceive(Context context, Intent intent) {
        mIRepository = BroadCastLogsDBRepository.getInstance(context);

        try {
            System.out.println("Receiver start");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//                Toast.makeText(context, "Incoming Call State", Toast.LENGTH_SHORT).show();

                Long timestampLong = System.currentTimeMillis()/1000;
                String timestamp = timestampLong.toString();
                BroadCastLog broadCastLog = new BroadCastLog("Call","Input",timestamp);
                mIRepository.insertBroadCastLog(broadCastLog);
/*
            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
                Toast.makeText(context,"Call Received State",Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"Call ended State",Toast.LENGTH_SHORT).show();
            }*/
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

