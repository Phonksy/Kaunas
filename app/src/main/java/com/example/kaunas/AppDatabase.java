package com.example.kaunas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Hotel.class}, version = 2)

public abstract class AppDatabase extends RoomDatabase {
    public abstract hotelDao hotelDao();


}
