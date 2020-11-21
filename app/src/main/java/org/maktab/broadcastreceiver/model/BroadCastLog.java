package org.maktab.broadcastreceiver.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BroadCastLogTable")
public class BroadCastLog {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long primaryId;

    @ColumnInfo(name = "event")
    private String mEvent;

    @ColumnInfo(name = "type")
    private String mType;

    @ColumnInfo(name = "timestamp")
    private String mTimestamp;

    public BroadCastLog(String event, String type, String timestamp) {
        mEvent = event;
        mType = type;
        mTimestamp = timestamp;
    }

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        mEvent = event;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }
}
