package com.example.bnilist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponseModel extends ResponseModel {
    @SerializedName("data")
    @Expose
    private ArrayList<UserModel> data;

    public ArrayList<UserModel> getData() {
        return data;
    }

    public void setData(ArrayList<UserModel> data) {
        this.data = data;
    }
}
