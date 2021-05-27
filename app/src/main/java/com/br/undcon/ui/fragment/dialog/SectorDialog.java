package com.br.undcon.ui.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.br.undcon.R;

public class SectorDialog extends AppCompatDialogFragment {
    private EditText editTextSectorName;
    private SectorDialogListner listner;

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.sector_dialog, null);

        builder.setView(view).setTitle("Setor")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listner.setName(editTextSectorName.getText().toString());
                    }
                });

        editTextSectorName = view.findViewById(R.id.sectorName);
        editTextSectorName.setText(listner.sugestionName());

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner = (SectorDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e);
        }
    }

    public interface SectorDialogListner {
        void setName(String name);

        String sugestionName();
    }
}
