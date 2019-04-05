package com.oliver.vmovier.core.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "daily_cover")
public class DailyCoverEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "read_date")
    private String mDate = "";

    public String getDate() {
        return mDate;
    }

    public void setDate(@NonNull String date) {
        mDate = date;
    }

}
