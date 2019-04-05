package com.oliver.vmovier.core.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;
import io.reactivex.Single;

@Dao
public interface DailyCoverDao {

    @Query("SELECT * FROM daily_cover WHERE read_date = :date")
    Single<DailyCoverEntity> findByDate(@NonNull String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(@NonNull DailyCoverEntity entity);

}
