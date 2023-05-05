package com.example.kaunas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Object {

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

    @ColumnInfo (name = "foto")
    public int foto;

    @ColumnInfo (name = "nuorodos")
    public String nuorodos;

    public Object(String pavadinimas, String adresas, String vertinimas, String svetaine, int foto, String nuorodos)
    {
        this.pavadinimas=pavadinimas;
        this.adresas=adresas;
        this.vertinimas=vertinimas;
        this.svetaine=svetaine;
        this.foto=foto;
        this.nuorodos=nuorodos;
    }

    public Object () {

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

    public int getFoto () {
        return foto;
    }
    public String getNuorodos () {
        return nuorodos;
    }
}
