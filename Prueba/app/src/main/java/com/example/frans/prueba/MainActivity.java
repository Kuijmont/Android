package com.example.frans.prueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView cabecera, intentos;
    private EditText valor;
    private Button adivina,res;
    private int cnt=1;
    private int aleatorio;
    //private final static String STATE_NUM_VECES = "numVeces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adivina);
        cabecera = (TextView)findViewById(R.id.TextViewCabecera);
        intentos = (TextView)findViewById(R.id.textView);
        valor = (EditText) findViewById(R.id.editTextNumero);
        adivina = (Button) findViewById(R.id.button);
        res = (Button) findViewById(R.id.buttonReset);
        aleatorio = generarAleatorio();

        adivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numeroUsuario;
                numeroUsuario = Integer.parseInt(valor.getText().toString());
                if(aleatorio==numeroUsuario){
                    cabecera.setText(getResources().getString(R.string.acierto));
                    adivina.setVisibility(View.INVISIBLE);
                    valor.setEnabled(false);
                    res.setVisibility(View.VISIBLE);
                    intentos.setText(getResources().getString(R.string.intentos)+" "+cnt);
                    valor.setText("");
                }else{

                    if(numeroUsuario<aleatorio) {
                        String format = getResources().getString(R.string.cabecera);
                        String cadFinal = String.format(format,Integer.parseInt(valor.getText().toString()));
                        cabecera.setText(cadFinal);
                        valor.setText("");
                    } else {
                        cabecera.setText(getResources().getString(R.string.cabecera2));
                        valor.setText("");
                    }
                    intentos.setText(getResources().getString(R.string.intentos) + " " + cnt);
                }cnt++;
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res.setVisibility(View.INVISIBLE);
                cnt=1;
                aleatorio = generarAleatorio();
                valor.setEnabled(true);
                adivina.setVisibility(View.VISIBLE);
                cabecera.setText(getResources().getString(R.string.cabeceraInicial));
                intentos.setText(getResources().getString(R.string.intentos) + " " + cnt);
            }
        });
    }



    private int generarAleatorio(){
        //return (int)(Math.random()*100+1);
        return 27;
    }

}