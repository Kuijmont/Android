package com.example.alumno.listadecompra;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Modificar extends DialogFragment {
    public interface EditNameDialogListenerM {
        public void onDialogPositiveClick(DialogFragment dialog, String name);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    public Modificar() {
        // Empty constructor required for DialogFragment
    }

    Modificar.EditNameDialogListenerM mListener;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Modificar.EditNameDialogListenerM) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ShareDialogListener");
        }
    }
    LayoutInflater l;
    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        l = getActivity().getLayoutInflater();
        v = (l.inflate(R.layout.altas, null));
        TextView texto1 = (TextView) v.findViewById(R.id.text1);
        texto1.setText("Modifica Artículo");
        TextView texto2 = (TextView)v.findViewById(R.id.text2);
        texto2.setText("Modifica el artículo");
        final TextView texto = (TextView) v.findViewById(R.id.introducirTexto);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(v)
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast toast1 = Toast.makeText(getActivity(), "Edición cancelada.", Toast.LENGTH_SHORT);
                        toast1.show();
                        dialog.cancel();
                    }
                })
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String nombre = texto.getText().toString();
                        mListener.onDialogPositiveClick(Modificar.this, nombre);
                    }
                });

        return builder.create();
    }
}
