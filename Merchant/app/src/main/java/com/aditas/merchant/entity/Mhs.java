package com.aditas.merchant.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mhs {
    @SerializedName("namaDpn")
    @Expose
    private String namaDpn;
    @SerializedName("namaBlkg")
    @Expose
    private String namaBlkg;
    @Expose(serialize = false) //hide umur di proses serialize
    private  int umur;
    @Expose
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
