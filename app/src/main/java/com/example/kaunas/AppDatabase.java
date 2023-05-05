package com.example.kaunas;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Object.class}, version = 3)

public abstract class AppDatabase extends RoomDatabase {
    public abstract objectDao objectDao();


}
