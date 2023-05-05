package com.example.kaunas;

public class atsiliepimas {
    public String komentaras;

    public String vardas;

    public atsiliepimas() {

    }

    public atsiliepimas(String vardas, String komentaras)
    {
        this.vardas=vardas;
        this.komentaras=komentaras;
    }

    public String getVardas () {
        return vardas;
    }

    public String getKomentaras() {
        return komentaras;
    }
}
