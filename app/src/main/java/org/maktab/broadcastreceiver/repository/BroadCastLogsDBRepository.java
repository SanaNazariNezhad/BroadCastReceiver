package org.maktab.broadcastreceiver.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.maktab.broadcastreceiver.database.BroadCastLogDatabaseDAO;
import org.maktab.broadcastreceiver.database.BroadCastLogsDataBase;
import org.maktab.broadcastreceiver.model.BroadCastLog;

import java.util.ArrayList;
import java.util.List;

public class BroadCastLogsDBRepository implements IRepository {

    private static BroadCastLogsDBRepository sInstance;
    
    private BroadCastLogDatabaseDAO mLogDatabaseDAO;
    private List<BroadCastLog> mLogList;
    private Context mContext;
    private MutableLiveData<List<BroadCastLog>> mLiveDataLogcat;

    @Override
    public MutableLiveData<List<BroadCastLog>> getLiveDataLogcat() {
        return mLiveDataLogcat;
    }

    public static BroadCastLogsDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new BroadCastLogsDBRepository(context);

        return sInstance;
    }

    private BroadCastLogsDBRepository(Context context) {
        mContext = context.getApplicationContext();
        BroadCastLogsDataBase logsDataBase = Room.databaseBuilder(mContext,
                BroadCastLogsDataBase.class,
                "logs.db")
                .allowMainThreadQueries()
                .build();

        mLogList = new ArrayList<>();
        mLiveDataLogcat = new MutableLiveData<>();

        mLogDatabaseDAO = logsDataBase.getBroadCastLogDatabaseDAO();
    }

    @Override
    public void updateBroadCastLog(BroadCastLog broadCastLog) {
        mLogDatabaseDAO.updateBroadCastLog(broadCastLog);
    }

    @Override
    public void insertBroadCastLog(BroadCastLog broadCastLog) {
        mLogDatabaseDAO.insertBroadCastLog(broadCastLog);
        mLiveDataLogcat.postValue(mLogDatabaseDAO.getBroadCastLog());
    }

    @Override
    public void insertBroadCastLogs(BroadCastLog... broadCastLog) {
        mLogDatabaseDAO.insertBroadCastLog(broadCastLog);
    }

    @Override
    public void deleteBroadCastLog(BroadCastLog broadCastLog) {
        mLogDatabaseDAO.deleteBroadCastLog(broadCastLog);
    }

    @Override
    public List<BroadCastLog> getBroadCastLog() {
        return mLogDatabaseDAO.getBroadCastLog();
    }
}
