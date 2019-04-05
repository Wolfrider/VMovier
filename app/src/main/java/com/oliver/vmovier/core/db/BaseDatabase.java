package com.oliver.vmovier.core.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DailyCoverEntity.class}, version = 1, exportSchema = false)
public abstract class BaseDatabase extends RoomDatabase{
    public abstract DailyCoverDao getDailyCoverDao();
}
