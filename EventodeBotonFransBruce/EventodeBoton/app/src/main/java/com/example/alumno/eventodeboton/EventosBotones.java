package com.example.alumno.eventodeboton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class EventosBotones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_botones);
    }
    public void Calcular (View v) {
        TextView textoAMostrar = findViewById(R.id.textView);
        EditText valor = (EditText) findViewById(R.id.editText2);
        RadioButton radioBoton = (RadioButton) findViewById(R.id.radioButton);
        RadioButton radioBotonD = (RadioButton) findViewById(R.id.radioButton2);
        try {
            float distancia = Float.parseFloat(valor.getText().toString());
            if (radioBoton.isChecked()) {
                float millas = convertirAMillas(distancia);
                textoAMostrar.setText("Kms son " + String.valueOf(millas)+ " millas");
            } else if (radioBotonD.isChecked()) {
                float km = convertirAKm(distancia);
                textoAMostrar.setText("Millas son " +String.valueOf(km)+ " kilometros");
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Por favor, introduce un numero",Toast.LENGTH_SHORT).show();
        }
    }

    private float convertirAKm(float distancia) {
        double valorKM=0.6214;
        float resultado = distancia / (float)valorKM;
        return resultado;
    }

    private float convertirAMillas(Float km) {
        double valorMilla=0.6214;
        float resultado = km * (float)valorMilla;
        return resultado;
    }
}
