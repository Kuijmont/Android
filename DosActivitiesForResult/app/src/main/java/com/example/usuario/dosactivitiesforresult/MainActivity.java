package com.example.usuario.dosactivitiesforresult;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Método llamado cuando se pulsa el botón.
     * Se lanza la segunda actividad pasándole como
     * dato el nombre escrito por el usuario. Se utiliza
     * startActivityForResult() para que Android nos envíe
     * de vuelta un resultado desde la actividad que hemos
     * lanzado.
     *
     * @param v Vista que ha ocasionado el evento.
     */
    public void onSaludar(View v) {

        // Recuperamos el cuadro de texto donde el usuario
        // habrá escrito su nombre.
        EditText edit = (EditText) findViewById(R.id.etNombre);
        String nombre = edit.getText().toString().trim();

        if (nombre.equals("")) {
            // El nombre está vacío. Avisamos al usuario e
            // ignoramos la pulsación del botón.
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, R.string.nombreInvalido, duration);
            toast.show();
        }
        else {
            // Creamos un intent para invocación explícita,
            // especificando la clase de la actividad que queremos
            // poner en marcha.
            Intent i = new Intent(this, SecondaryActivity.class);
            // Añadimos como datos extra el nombre (segundo parámetro).
            // Los datos extra recuerdan a una tabla hash (un "map" de C++,
            // un array de PHP o JavaScript) indexada usando una cadena.
            i.putExtra("nombre", nombre);
            // Pedimos a Android que nos lance la actividad y
            // gestione su ciclo de vida.
            startActivityForResult(i, SECONDARY_ACTIVITY_TAG);
            // Borramos el cuadro de texto para la vuelta.
            edit.setText("");
        } // if-else

    } // onSaludar
    /**
     * Método invocado por Android cuando una de las actividades
     * que hemos lanzado desde ésta usando startActivityForResult()
     * termina.
     *
     * @param requestCode Valor del segundo parámetro que pasamos a
     * startActivityForResult() para lanzar la actividad que ahora
     * termina.
     * @param resultCode Código indicando el éxito o fracaso de la
     * actividad que ha terminado (RESULT_CANCELED o RESULT_OK).
     * @param data Intent con los datos devueltos por la actividad que
     * ha terminado (llamando a setResult()).
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String respuesta;

        // Como solo lanzamos una actividad, requestCode siempre será
        // SECONDARY_ACTIVITY_TAG. No hace falta que lo comprobemos.

        if ((resultCode == RESULT_CANCELED) ||
                (data.getStringExtra("respuesta").equals("")))
            // O el usuario ha pulsado "volver" en la segunda actividad
            // o ha pulsado el botón de aceptar sin escribir un nombre.
            // Nos quejamos.
            respuesta = getResources().getString(R.string.antipatico);
        else
            // Tenemos una respuesta. La recuperamos del intent que nos
            // ha llegado desde la segunda actividad.
            respuesta = data.getStringExtra("respuesta");

        // Mostramos un toast con la respuesta o con la queja.
        Context contexto = getApplicationContext();
        Toast.makeText(contexto, respuesta, Toast.LENGTH_LONG).show();

    } // onActivityResult
    /**
     * "Etiqueta" que usamos para llamar a la segunda actividad, y que
     * esperamos recibir como primer parámetro de vuelta en el
     * callback onActivityResult().
     */
    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
