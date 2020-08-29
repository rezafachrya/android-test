package com.example.bnilist.helper;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.bnilist.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.core.content.res.ResourcesCompat;

import cn.pedant.SweetAlert.SweetAlertDialog;

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

    public static void showSuccessDialog(final Context c, String title, String content) {
        SweetAlertDialog sweetDialog = new SweetAlertDialog(c, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setCustomView(createCustomText(c, content))
                .setConfirmText("CLOSE");
        sweetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((SweetAlertDialog) dialog).getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(ResourcesCompat.getDrawable(c.getResources(), R.drawable.border_orange_dark_rounded_big_fill, null));
                ((SweetAlertDialog) dialog).getButton(SweetAlertDialog.BUTTON_CONFIRM).setTextSize(TypedValue.COMPLEX_UNIT_PX, c.getResources().getDimension(R.dimen._8ssp));
            }
        });
        sweetDialog.show();
    }

    public static void showErrorDialog(final Context c, String title, String content) {
        SweetAlertDialog sweetDialog = new SweetAlertDialog(c, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("")
                .setCustomView(createCustomText(c, content))
                .setConfirmText("CLOSE");
        sweetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ((SweetAlertDialog) dialog).getButton(SweetAlertDialog.BUTTON_CONFIRM).setBackground(ResourcesCompat.getDrawable(c.getResources(), R.drawable.border_orange_dark_rounded_big_fill, null));
                ((SweetAlertDialog) dialog).getButton(SweetAlertDialog.BUTTON_CONFIRM).setTextSize(TypedValue.COMPLEX_UNIT_PX, c.getResources().getDimension(R.dimen._8ssp));
            }
        });
        sweetDialog.show();
    }

    public static TextView createCustomText(Context c, String content) {
        final TextView text = new TextView(c);
        text.setGravity(Gravity.CENTER);

        text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(params);
        text.setText(content.replace("500 - ", "").replace("403 - ", "") + "\n");

        return text;
    }
}
