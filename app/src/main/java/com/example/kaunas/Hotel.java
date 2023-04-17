package com.example.kaunas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hotel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo (name = "pavadinimas")
    public String pavadinimas;

    @ColumnInfo (name = "adresas")
    public String adresas;

    @ColumnInfo (name = "vertinimas")
    public String vertinimas;

    public Hotel (String pavadinimas, String adresas, String vertinimas)
    {
        this.pavadinimas=pavadinimas;
        this.adresas=adresas;
        this.vertinimas=vertinimas;
    }

    public String getPavadinimas () {
        return pavadinimas;
    }

    public String getAdresas () {
        return adresas;
    }

    public String getVertinimas () {
        return vertinimas;
    }
}
