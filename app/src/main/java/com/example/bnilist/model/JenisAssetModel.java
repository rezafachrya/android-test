package com.example.bnilist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JenisAssetModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jnsasset")
    @Expose
    private String jnsasset;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJnsasset() {
        return jnsasset;
    }

    public void setJnsasset(String jnsasset) {
        this.jnsasset = jnsasset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
