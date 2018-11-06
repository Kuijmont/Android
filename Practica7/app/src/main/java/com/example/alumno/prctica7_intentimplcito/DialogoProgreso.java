package com.example.alumno.prctica7_intentimplcito;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class DialogoProgreso extends DialogFragment {
    private int miProgreso;
    private Handler miProgresoHandler;
    private static final int PROGRESO_MAX = 100;
    private ProgressDialog miProgresoDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        miProgresoDialog = new ProgressDialog(getActivity());
        miProgreso = 0;
        miProgresoDialog.setProgress(0);

        miProgresoDialog.setIcon(android.R.drawable.ic_dialog_info);
        miProgresoDialog.setTitle("Enviando...");
        miProgresoDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        miProgresoDialog.setMax(PROGRESO_MAX);
        miProgresoDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                "Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        Toast.makeText(getActivity(), "El envío ha sido cancelado.", Toast.LENGTH_SHORT).show();
                    }
                });
        // Controlador (hilo) que simula un cambio en el progreso.
        // Es como un temporizador que usamos para dibujar la barra de progreso
        // en una ventana de diálogo.
        miProgresoHandler = new Handler(){
            @Override
            public void handleMessage(Message msj) {
                super.handleMessage(msj);
                if (miProgreso >= PROGRESO_MAX) {
                    Toast.makeText(getContext(), "El perfil se ha enviado correctamente.", Toast.LENGTH_SHORT).show();
                    miProgresoDialog.dismiss();
                } else {
                    miProgreso++;
                    miProgresoDialog.incrementProgressBy(1);
                    miProgresoHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        };
        miProgresoHandler.sendEmptyMessage(0);

        return miProgresoDialog;

    }
    // Cuando desaparece la ventana de Diálogo paramos el Controlador (hilo) que simula el progreso
    @Override
    public void onStop() {
        super.onStop();
        miProgresoHandler.removeCallbacksAndMessages(null);
    }
}
