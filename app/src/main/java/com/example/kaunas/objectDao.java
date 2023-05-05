package com.example.kaunas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface objectDao {
    @Query("SELECT * FROM Object")
    List<Object> getAllHotels();

    @Insert
    void insertAll(Object... hotels);

    @Delete
    void delete(List<Object> hotels);

    @Query("DELETE FROM Object")
    public void nukeTable();
}
