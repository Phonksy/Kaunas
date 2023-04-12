package com.example.kaunas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DB_hotels {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "hotel_name")
    public String hotel_name;

    @ColumnInfo(name = "street")
    public String street;

    @ColumnInfo(name = "rating")
    public int rating;

    public DB_hotels(String hotel_name, String street, int rating)
    {
        this.hotel_name = hotel_name;
        this.street = street;
        this.rating = rating;
    }
}
