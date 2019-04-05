package com.oliver.vmovier.core.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

public class VMovierDB {

    private static final String DB_NAME = "vmovier.db";

    private static BaseDatabase mDB;

    public static void init(@NonNull Context context) {
        if (null == mDB) {
            mDB = Room.databaseBuilder(context, BaseDatabase.class, DB_NAME).build();
        }
    }

    public static BaseDatabase getDB() {
        return mDB;
    }

}
