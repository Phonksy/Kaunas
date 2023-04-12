package com.example.kaunas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DB_HotelsDao {
    @Query("SELECT * FROM DB_hotels")
    List<DB_hotels> getAll();

    @Insert
    void insertAll(DB_hotels... all_hotels);

    @Delete
    void delete(DB_hotels hotel);
}
