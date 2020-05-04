package com.example.bnilist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TassetDetailModel implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("perolehan")
    @Expose
    private String perolehan;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("nilaibuku")
    @Expose
    private String nilaibuku;

    @SerializedName("massetcompfk")
    @Expose
    private String massetcompfk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerolehan() {
        return perolehan;
    }

    public void setPerolehan(String perolehan) {
        this.perolehan = perolehan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNilaibuku() {
        return nilaibuku;
    }

    public void setNilaibuku(String nilaibuku) {
        this.nilaibuku = nilaibuku;
    }

    public String getMassetcompfk() {
        return massetcompfk;
    }

    public void setMassetcompfk(String massetcompfk) {
        this.massetcompfk = massetcompfk;
    }
}
