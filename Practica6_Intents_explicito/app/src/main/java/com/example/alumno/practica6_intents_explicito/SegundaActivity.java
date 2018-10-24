package com.example.alumno.practica6_intents_explicito;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_activity);

        //Declaro la variable tipo Button de la app ventana secundaria
        Button buttonAceptar = (Button) findViewById(R.id.buttonAcept);

        //Alta o Modifica dependiendo de la acción pulsada
        String boton = getIntent().getStringExtra("boton");

        switch (boton){
            case "Alta": //Pulsar boton de Altas
                buttonAceptar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //Declaramos variables de los EditTexts del nombre y del apelido
                    EditText nombre = (EditText) findViewById(R.id.editTextNombre);
                    EditText apellido = (EditText) findViewById(R.id.editTextApell);

                    //Compruebo que no se deja en blanco los EditText
                    if(nombre.getText().toString().equals("") || apellido.getText().toString().equals("")) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Rellena todos los campos.", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{ //Caso contrario, lo rellena correctamente, devolvemos los valores de los campos a la main activity

                    }
            }
        });
                break;
            case "Modf": //Pulsar boton de Modificar

                break;
        }
    }
}
