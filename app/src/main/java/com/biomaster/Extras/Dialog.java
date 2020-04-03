package com.biomaster.Extras;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.biomaster.R;

public class Dialog {

    public static void showAlertDialog(Context cntx, String title, String message, DialogInterface.OnClickListener okAction){
        AlertDialog.Builder dialog = new AlertDialog.Builder(cntx);

        dialog.setTitle(title);
        dialog.setMessage(message);

        dialog.setPositiveButton(cntx.getResources().getString(R.string.btnAceptDefault), okAction);

        dialog.create().show();
    }

}
