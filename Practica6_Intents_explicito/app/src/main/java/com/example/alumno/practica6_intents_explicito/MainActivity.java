package com.example.alumno.practica6_intents_explicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaro las variables tipo Button de la app ventana principal
        Button buttonAlta = (Button) findViewById(R.id.buttonAlta);
        Button buttonModf = (Button) findViewById(R.id.buttonMod);

        //Pulsar botón de Altas
        buttonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se llama a la seginda actividad y se le manda el boton pulsado.
                String accion="Alta";
                Intent i = new Intent(MainActivity.this,SegundaActivity.class);
                i.putExtra("boton", accion);
                startActivityForResult(i,SECONDARY_ACTIVITY_TAG);
            }
        });


    }
    /**
     * "Etiqueta" que usamos para llamar a la segunda actividad, y que
     * esperamos recibir como primer parámetro de vuelta en el
     * callback onActivityResult().
     */
    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
