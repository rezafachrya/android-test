package com.example.bnilist.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_AMS_APP = "spAmsApp";

    public static final String SP_HANDPHONE = "spHandphone";

    public static final String SP_USERNAME = "spUsername";

    public static final String SP_HAS_LOGIN = "spHasLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    @SuppressLint("CommitPrefEdits")
    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_AMS_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void SaveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpHandphone(){
        return sp.getString(SP_HANDPHONE, "");
    }

    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }

    public Boolean getSPHasLogin(){
        return sp.getBoolean(SP_HAS_LOGIN, false);
    }
}
