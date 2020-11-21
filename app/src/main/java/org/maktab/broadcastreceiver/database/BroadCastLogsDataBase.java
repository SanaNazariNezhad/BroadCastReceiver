package org.maktab.broadcastreceiver.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.maktab.broadcastreceiver.model.BroadCastLog;

@Database(entities = {BroadCastLog.class}, version = 1)
public abstract class BroadCastLogsDataBase extends RoomDatabase {

    public abstract BroadCastLogDatabaseDAO getBroadCastLogDatabaseDAO();
}
