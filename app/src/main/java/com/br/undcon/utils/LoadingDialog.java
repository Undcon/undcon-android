package com.br.undcon.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.br.undcon.R;

public class LoadingDialog {
    Activity activity;
    AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void show(String message) {
        if (message == null) {
            message = "Carregando...";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_loader, null);
        TextView loadingText = (TextView) view.findViewById(R.id.loadingText);
        loadingText.setText(message);

        builder.setView(view);
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }



    public void hide() {
        dialog.dismiss();
    }
}
