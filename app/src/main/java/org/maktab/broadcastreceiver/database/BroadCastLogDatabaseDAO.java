package org.maktab.broadcastreceiver.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.maktab.broadcastreceiver.model.BroadCastLog;

import java.util.List;

@Dao
public interface BroadCastLogDatabaseDAO {

    @Update
    void updateBroadCastLog(BroadCastLog broadCastLog);

    @Insert
    void insertBroadCastLog(BroadCastLog broadCastLog);

    @Insert
    void insertBroadCastLog(BroadCastLog... broadCastLog);

    @Delete
    void deleteBroadCastLog(BroadCastLog broadCastLog);

    @Query("SELECT * FROM BroadCastLogTable")
    List<BroadCastLog> getBroadCastLog();

}
