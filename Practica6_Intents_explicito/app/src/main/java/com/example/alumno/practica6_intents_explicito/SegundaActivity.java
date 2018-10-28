package com.example.alumno.practica6_intents_explicito;

import android.content.Intent;
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
            case "Alta": //Altas
                //Pulsar boton de Aceptar
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
                        String resNombre = nombre.getText().toString();
                        String resApellidos = apellido.getText().toString();
                        String boton="Alta";
                        Intent respuesta = new Intent(); //Intent para la respuesta
                        respuesta.putExtra("boton", boton);
                        respuesta.putExtra("nombre", resNombre);
                        respuesta.putExtra("apellidos", resApellidos);
                        setResult(RESULT_OK, respuesta);
                        finish();
                    }
            }
        });
                break;
            case "Modf": //Modificar
                //Recogida de datos a modificar
                EditText nombre = (EditText) findViewById(R.id.editTextNombre);
                EditText apellido = (EditText) findViewById(R.id.editTextApell);
                String nom = getIntent().getStringExtra("nombre");
                String ape = getIntent().getStringExtra("apellidos");
                //Rellena las cajas de texto con los datos anteriores.
                nombre.setText(nom);
                apellido.setText(ape);
                //Pulsar boton de Aceptar
                buttonAceptar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
                        EditText apellido = (EditText) findViewById(R.id.editTextApell);
                        //Compruebo que no se deja en blanco las cajas de texto
                        if(nombre.getText().toString().equals("") || apellido.getText().toString().equals("")){
                            Toast toast1 = Toast.makeText(getApplicationContext(),"Rellena todos los campos.", Toast.LENGTH_SHORT);
                            toast1.show();
                        }else{//Caso contrario, lo rellena correctamente, devolvemos los valores de los campos a la main activity
                            String resNombre = nombre.getText().toString();
                            String resApellidos = apellido.getText().toString();
                            String boton="Alta";
                            Intent respuesta = new Intent(); //Intent para la respuesta
                            respuesta.putExtra("boton", boton);
                            respuesta.putExtra("nombre", resNombre);
                            respuesta.putExtra("apellidos", resApellidos);
                            setResult(RESULT_OK, respuesta);
                            finish();
                        }

                    }
                });
                break;
        }
    }
}
