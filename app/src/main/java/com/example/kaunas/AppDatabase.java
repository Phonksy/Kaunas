package com.example.kaunas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DB_hotels.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DB_HotelsDao DB_hotelsDao();
}
