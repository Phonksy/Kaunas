package com.example.kaunas;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface hotelDao {
    @Query("SELECT * FROM Hotel")
    List<Hotel> getAllHotels();

    @Insert
    void insertAll(Hotel... hotels);

    @Delete
    void delete(List<Hotel> hotels);

    @Query("DELETE FROM hotel")
    public void nukeTable();
}
