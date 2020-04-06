package com.example.bnilist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TassetResponseModel extends ResponseModel {

    @SerializedName("data")
    @Expose
    private ArrayList<TassetModel> data;

    public ArrayList<TassetModel> getData() {
        return data;
    }

    public void setData(ArrayList<TassetModel> data) {
        this.data = data;
    }
}
