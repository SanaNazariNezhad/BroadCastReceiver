package org.maktab.broadcastreceiver.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab.broadcastreceiver.model.BroadCastLog;

import java.util.List;

public interface IRepository {

    void updateBroadCastLog(BroadCastLog broadCastLog);

    void insertBroadCastLog(BroadCastLog BroadCastLog);

    void insertBroadCastLogs(BroadCastLog... BroadCastLog);

    void deleteBroadCastLog(BroadCastLog BroadCastLog);

    List<BroadCastLog> getBroadCastLog();

    MutableLiveData<List<BroadCastLog>> getLiveDataLogcat();

}
