package com.mysan.roman.romanmysan;

/**
 * Created by student on 21.03.17.
 */
public class ModelOceny {

    private String nazwa;
    private int ocena;

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
