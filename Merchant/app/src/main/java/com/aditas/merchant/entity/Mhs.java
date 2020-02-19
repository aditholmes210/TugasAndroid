package com.aditas.merchant.entity;

public class Mhs {
    private String namaDpn;
    private String namaBlkg;
    private int umur;
    private String jur;

    public Mhs(String namaDpn, String namaBlkg, int umur, String jur) {
        this.namaDpn = namaDpn;
        this.namaBlkg = namaBlkg;
        this.umur = umur;
        this.jur = jur;
    }

    public String getNamaDpn() {
        return namaDpn;
    }

    public String getNamaBlkg() {
        return namaBlkg;
    }

    public int getUmur() {
        return umur;
    }

    public String getJur() {
        return jur;
    }
}
