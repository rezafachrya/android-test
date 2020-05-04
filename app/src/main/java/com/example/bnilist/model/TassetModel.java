package com.example.bnilist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class TassetModel implements Serializable{
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("kpa")
    @Expose
    private String kpa;

    @SerializedName("npa")
    @Expose
    private String npa;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("provinsi")
    @Expose
    private String provinsi;

    @SerializedName("kota")
    @Expose
    private String kota;

    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;

    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;

    @SerializedName("kodepos")
    @Expose
    private String kodepos;

    @SerializedName("jmllantai")
    @Expose
    private String jmllantai;

    @SerializedName("luasbangunan")
    @Expose
    private String luasbangunan;

    @SerializedName("nop")
    @Expose
    private String nop;

    @SerializedName("doclegal")
    @Expose
    private String doclegal;

    @SerializedName("luastanah")
    @Expose
    private String luastanah;

    @SerializedName("noimb")
    @Expose
    private String noimb;

    @SerializedName("nib")
    @Expose
    private String nib;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("urlimage1")
    @Expose
    private String urlimage1;

    @SerializedName("urlimage2")
    @Expose
    private String urlimage2;

    @SerializedName("urlimage3")
    @Expose
    private String urlimage3;

    @SerializedName("urlimage4")
    @Expose
    private String urlimage4;

    @SerializedName("assettype")
    @Expose
    private String assettype;

    @SerializedName("detaildata")
    @Expose
    private ArrayList<TassetDetailModel> detaildata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKpa() {
        return kpa;
    }

    public void setKpa(String kpa) {
        this.kpa = kpa;
    }

    public String getNpa() {
        return npa;
    }

    public void setNpa(String npa) {
        this.npa = npa;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJmllantai() {
        return jmllantai;
    }

    public void setJmllantai(String jmllantai) {
        this.jmllantai = jmllantai;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNib() {
        return nib;
    }

    public void setNib(String nib) {
        this.nib = nib;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLuasbangunan() {
        return luasbangunan;
    }

    public void setLuasbangunan(String luasbangunan) {
        this.luasbangunan = luasbangunan;
    }

    public String getUrlimage1() {
        return urlimage1;
    }

    public void setUrlimage1(String urlimage1) {
        this.urlimage1 = urlimage1;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getNop() {
        return nop;
    }

    public void setNop(String nop) {
        this.nop = nop;
    }

    public String getUrlimage2() {
        return urlimage2;
    }

    public void setUrlimage2(String urlimage2) {
        this.urlimage2 = urlimage2;
    }

    public String getUrlimage3() {
        return urlimage3;
    }

    public void setUrlimage3(String urlimage3) {
        this.urlimage3 = urlimage3;
    }

    public String getUrlimage4() {
        return urlimage4;
    }

    public void setUrlimage4(String urlimage4) {
        this.urlimage4 = urlimage4;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getDoclegal() {
        return doclegal;
    }

    public void setDoclegal(String doclegal) {
        this.doclegal = doclegal;
    }

    public String getLuastanah() {
        return luastanah;
    }

    public void setLuastanah(String luastanah) {
        this.luastanah = luastanah;
    }

    public String getNoimb() {
        return noimb;
    }

    public void setNoimb(String noimb) {
        this.noimb = noimb;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }


    public ArrayList<TassetDetailModel> getDetaildata() {
        return detaildata;
    }

    public void setDetaildata(ArrayList<TassetDetailModel> detaildata) {
        this.detaildata = detaildata;
    }
}
