package com.example.alumno.adivinaminumero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainAdivina extends AppCompatActivity {

    private final TextView  intentos = null;
    private Button adivina,res;
    int numIntentos;
    private final EditText valor = null;
    private final TextView cabecera = null;
    private int numElegido;
    private static boolean estado = false;
    private final static String STATE_NUM_ELEGIDO = "numElegido";
    private final static String STATE_NUM_INTENTOS = "numIntentos";
    private final static String STATE_MSG_CABECERA = "msgCabecera";
    private final static String STATE_MSG_INTENTOS = "msgIntentos";
    private final static String STATE_RESET = "estado";
    String msgCabecera = "He pensado un número entre 1 y 100. \n ¡Adivina cual es!";;
    String msgIntentos = "¡Intentalo!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adivina);

        final TextView cabecera = (TextView) findViewById(R.id.TextViewCabecera);
        final TextView intentos = (TextView) findViewById(R.id.textView);
        final EditText valor = (EditText) findViewById(R.id.editTextNumero);
        adivina = (Button) findViewById(R.id.button); //Boton Probar
        res = (Button) findViewById(R.id.buttonReset);
        numIntentos = 0;
        numElegido = generarAleatorio();

        if (savedInstanceState != null) {//Carga los valores al girar la pantalla.
            numElegido = savedInstanceState.getInt(STATE_NUM_ELEGIDO);
            numIntentos = savedInstanceState.getInt(STATE_NUM_INTENTOS);
            msgCabecera = savedInstanceState.getString(STATE_MSG_CABECERA);
            cabecera.setText(msgCabecera);
            msgIntentos = savedInstanceState.getString(STATE_MSG_INTENTOS);
            intentos.setText(msgIntentos);
            estado = savedInstanceState.getBoolean(STATE_RESET);
            res.setEnabled(estado);
        }

        adivina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int numeroUsuario;
                    numeroUsuario = Integer.valueOf(valor.getText().toString());
                    int val = Integer.valueOf(String.valueOf(valor.getText()));
                    if(numElegido == numeroUsuario){
                        adivina.setVisibility(View.INVISIBLE);
                        adivina.setEnabled(estado);
                        valor.setEnabled(estado);
                        res.setEnabled(!estado);
                        res.setVisibility(View.VISIBLE);
                    }
                    if (numeroUsuario > 0 && numeroUsuario < 101) {
                        numIntentos++;//Añadimos 1 en el contador de veces.
                        if (numeroUsuario < numElegido) {
                           String format = getResources().getString(R.string.cabecera);
                           String cadFinal = String.format(format, val);
                           cabecera.setText(cadFinal);
                           valor.setText("");
                           intentos.setText(getResources().getString(R.string.intentos) + " " + numIntentos);
                           msgCabecera = "" + cabecera.getText();
                           msgIntentos = "" + intentos.getText();

                        } else {
                            if (numeroUsuario > numElegido) {
                                String format = getResources().getString(R.string.cabecera2);
                                String cadFinal = String.format(format, val);
                                cabecera.setText(cadFinal);
                                valor.setText("");
                                intentos.setText(getResources().getString(R.string.intentos) + " " + numIntentos);
                                msgCabecera = "" + cabecera.getText();
                                msgIntentos = "" + intentos.getText();
                            } else {
                                if (numElegido == numeroUsuario) {
                                    String format = getResources().getString(R.string.acierto);
                                    String cadFinal = String.format(format, numIntentos);
                                    cabecera.setText(cadFinal);
                                    msgCabecera = "" + cabecera.getText();


                                    intentos.setText(getResources().getString(R.string.intentos) + " " + numIntentos);
                                    msgIntentos = "" + intentos.getText();
                                    valor.setText("");

                                    res.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            res.setVisibility(View.INVISIBLE);
                                            numIntentos = 0;
                                            numElegido = generarAleatorio();
                                            valor.setEnabled(!estado);
                                            adivina.setEnabled(!estado);
                                            adivina.setVisibility(View.VISIBLE);
                                            cabecera.setText(getResources().getString(R.string.cabeceraInicial));
                                            msgCabecera = "" + cabecera.getText();
                                            intentos.setText(getResources().getString(R.string.intentos) + " " + numIntentos);
                                            msgIntentos = "" + intentos.getText();

                                        }
                                    });
                                }
                            }
                        }
                    } else {
                        Toast toast2 = Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast2), Toast.LENGTH_SHORT);//Configuramos el mensaje tipo "Toast".
                        toast2.show();//Mostramos mensaje.
                    }
                } catch (Exception e) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast1), Toast.LENGTH_SHORT);//Configuramos el mensaje tipo "Toast".
                    toast1.show();//Mostramos mensaje.
                }
            }
        });



    }

    public int generarAleatorio(){
       return (int)(Math.random()*100+1);
    }


    public void onSaveInstanceState(Bundle outState){//Guarda los valores al girar la pantalla
       super.onSaveInstanceState(outState);
        outState.putInt(STATE_NUM_INTENTOS, numIntentos);
        outState.putInt(STATE_NUM_ELEGIDO,numElegido);
        outState.putString(STATE_MSG_CABECERA,msgCabecera);
        outState.putString(STATE_MSG_INTENTOS,msgIntentos);
        outState.putBoolean(STATE_RESET, estado);
    }

 }

