package com.example.alumno.practica6_intents_explicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaro las variables tipo Button de la app ventana principal
        Button buttonAlta = (Button) findViewById(R.id.buttonAlta);
        Button buttonModf = (Button) findViewById(R.id.buttonMod);
        final TextView resNom = (TextView) findViewById(R.id.textViewNom);
        final TextView resApe = (TextView) findViewById(R.id.textViewAp);

        //Pulsar botón de Altas
        buttonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se llama a la seginda actividad y se le manda el boton pulsado.
                String boton="Alta";
                Intent i = new Intent(MainActivity.this,SegundaActivity.class);
                i.putExtra("boton", boton);
                startActivityForResult(i,SECONDARY_ACTIVITY_TAG);

            }
        });

        //Pulsar botón de Modificar
        buttonModf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se llama a la seginda actividad y se le manda el boton pulsado.
                String boton="Modf";
                String resNombre = resNom.getText().toString();
                String resApellidos = resApe.getText().toString();
                Intent i = new Intent(MainActivity.this,SegundaActivity.class);
                i.putExtra("boton", boton);
                i.putExtra("nombre", resNombre);
                i.putExtra("apellidos", resApellidos);
                startActivityForResult(i,SECONDARY_ACTIVITY_TAG);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Si se cancela la operación de la actividad secundaria se mostrará un mensaje informativo de tipo Toast.
        if (resultCode == RESULT_CANCELED){
            String respuesta = "Se ha cancelado, has salido sin pulsar el botón 'Aceptar'";
            Toast toast1 = Toast.makeText(getApplicationContext(),respuesta, Toast.LENGTH_SHORT);
            toast1.show();
        }
        else {
            //Si se sale pulsando el botón de aceptar de la actividad secundaria mostraremos un mensaje informativo según el estado de la actividad principal.
            // Además cogeremos los datos que nos ha enviado y cambiaremos al segundo estado de la actividad principal.
            if(data.getStringExtra("boton").equals("Alta")) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Alta del contacto hecha correctamente.", Toast.LENGTH_SHORT);
                toast1.show();
            }else
            {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Modificación del contacto hecha correctamente.", Toast.LENGTH_SHORT);
                toast1.show();
            }
            String respuestaNombre = data.getStringExtra("nombre");
            String respuestaApellidos = data.getStringExtra("apellidos");

            Button buttonAlta = (Button) findViewById(R.id.buttonAlta);
            Button buttonModf = (Button) findViewById(R.id.buttonMod);
            final TextView vacio = (TextView) findViewById(R.id.textView2);
            final TextView nom = (TextView) findViewById(R.id.textView3);
            final TextView ape = (TextView) findViewById(R.id.textView4);
            final TextView resNom = (TextView) findViewById(R.id.textViewNom);
            final TextView resApe = (TextView) findViewById(R.id.textViewAp);

            vacio.setVisibility(View.GONE);
            nom.setVisibility(View.VISIBLE);
            ape.setVisibility(View.VISIBLE);
            resNom.setText(respuestaNombre);
            resApe.setText(respuestaApellidos);
            buttonAlta.setEnabled(false);
            buttonModf.setEnabled(true);
        }
    }


    /**
     * "Etiqueta" que usamos para llamar a la segunda actividad, y que
     * esperamos recibir como primer parámetro de vuelta en el
     * callback onActivityResult().
     */
    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
