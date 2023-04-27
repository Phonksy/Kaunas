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

    @ColumnInfo (name = "svetaine")
    public String svetaine;

    public Hotel (String pavadinimas, String adresas, String vertinimas, String svetaine)
    {
        this.pavadinimas=pavadinimas;
        this.adresas=adresas;
        this.vertinimas=vertinimas;
        this.svetaine=svetaine;
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
    public String getSvetaine () {
        return svetaine;
    }
}
