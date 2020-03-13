package com.example.bnilist.helper;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;
import com.example.bnilist.R;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogHelper {
    public static void showMessage(Context view, String msg) {
        AlertDialog.Builder attention = new AlertDialog.Builder(view);
        //        attention.setIcon(R.drawable.img_attention);
        attention.setTitle("Info..!");
        attention.setMessage(msg);
        attention.setCancelable(false);
        attention.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = attention.create();
        alert.show();
    }



}
