package com.example.bnilist.helper;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;
import com.example.bnilist.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogHelper {
    public static void onClickedErrorDialog(Context context, String message) {
        new MaterialAlertDialogBuilder(context, R.style.ErrorDialogTheme_Center)
                .setTitle(message)
                .setIcon(R.drawable.ic_error_outline_white_24dp)
                .setPositiveButton("Ok", null)
                .show();
    }

    public static void onClickedSuccessDialog(Context context, String message) {
        new MaterialAlertDialogBuilder(context, R.style.SuccessDialogTheme_Center)
                .setTitle(message)
                .setIcon(R.drawable.ic_done_white_24dp)
                .setPositiveButton("Ok", null)
                .show();
    }

    public static void onClickedLogoutDialog(Context context, String message) {
        new MaterialAlertDialogBuilder(context, R.style.ErrorDialogTheme_Center)
                .setTitle(message)
                .setIcon(R.drawable.ic_error_outline_white_24dp)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }
}
