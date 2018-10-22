package com.example.usuario.dosactivitiesforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        // Establecemos el contenido de la actividad a partir
        // del XML.
        setContentView(R.layout.activity_secondary);

        // Recuperamos una copia del intent que se ha usado para
        // que Android nos lance, y sacamos de él el elemento
        // indexado por la cadena "nombre" (el mismo índice que
        // se usó en la actividad invocante).
        String nombre = getIntent().getStringExtra("nombre");

        // Obtenemos el saludo del recurso (%1$s sitúa el lugar donde
        // tendrá que aparecer el nombre).
        String saludo = getResources().getString(R.string.holaNombre);
        // Sustituímos la marca de posición por el nombre sacado
        // del intent.
        saludo = String.format(saludo, nombre);
        // Buscamos la etiqueta de la actividad donde pondremos el saludo.
        TextView etiqueta = (TextView) findViewById(R.id.etSaludo);
        // Ponemos el saludo
        etiqueta.setText(saludo);
    }


    public void onResponder(View v) {

        // Sacamos del cuadro de texto la respuesta al saludo
        // escrita por el usuario.
        String respuesta;
        EditText et = (EditText) findViewById(R.id.etRespuesta);
        respuesta = et.getText().toString();

        // Creamos un nuevo intent donde enviaremos de vuelta
        // los datos de nuestra ejecución.
        Intent datos = new Intent();
        // Metemos como datos extra del intent la respuesta del
        // usuario.
        datos.putExtra("respuesta", respuesta);
        // Le decimos a Android que estamos preparados para acabar
        // con éxito...
        setResult(RESULT_OK, datos);

        // ... y le pedimos que nos cierre.
        finish();

    } // onResponder
}
