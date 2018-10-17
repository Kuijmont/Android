package com.example.javierrobles.pulsame4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


// import android.app.Activity;

import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        // Configuramos la ventana, añadiendo un botón
        // que llamará a nuestro método protegido.
        // Si es necesario, nos inicializamos a partir de un
        // estado anterior.
        if (savedInstanceState != null) {
            // Hay que reconstruirse. Recuperamos el valor de
            // la variable _numVeces en el momento de nuestra
            // destrucción.
            _numVeces = savedInstanceState.getInt(STATE_NUM_VECES);
            if (_numVeces != 0) {
                // Se ha pulsado el botón al menos una vez. Establecemos
                // la etiqueta del botón. Si no comprobáramos que no es 0
                // si se rota el móvil nada maś lanzarlo cambiaríamos el
                // "Púlsame" por "Pulsado 0 veces".
                Button b = (Button) findViewById(R.id.boton);
                b.setText(getEtiquetaBoton());
            }
        } // if (savedInstanceState != null)

    }

    /**
     * Método llamado cuando se pulsa sobre el botón
     * de la ventana. Es llamado a través de la clase
     * anónima del evento.
     */
    public void botonPulsado(View v) {

        // Incrementamos el contador...
        ++_numVeces;
        Button b = (Button) v;
        // ... y actualizamos la etiqueta del botón.
        b.setText(getEtiquetaBoton());

    } // botonPulsado

    /**
     * Método llamado por Android para darnos la oportunidad de
     * guardar nuestro estado en un "bundle" antes de que nos destruya.
     * Es probable que nos tengamos que reconstruir a partir de
     * la información que guardemos en él, y el usuario no debería
     * notar nada.
     *
     * @param outInstance Bundle donde guardar nuestro estado para
     * poder reconstruirnos. Metemos el valor del atributo
     * _numVeces en un campo de nombre STATE_NUM_VECES.
     */
    @Override
    public void onSaveInstanceState(Bundle outInstance) {

        // Llamamos al método de la superclase.
        super.onSaveInstanceState(outInstance);

        // Guardamos el único valor que nos interesa.
        outInstance.putInt(STATE_NUM_VECES, _numVeces);

    } // onSaveInstanceState

    private String getEtiquetaBoton() {

        android.content.res.Resources res = getResources();
        String numPulsados;
        numPulsados = res.getQuantityString(R.plurals.numPulsaciones,
                _numVeces, _numVeces);

        return numPulsados;

    } // getEtiquetaBoton
    /**
     * Número de veces que se ha pulsado el botón.
     */
    private int _numVeces;

    /**
     * Nombre del campo que metemos en el "bundle" donde guardamos
     * el estado para la variable _numVeces.
     */
    private final static String STATE_NUM_VECES = "numVeces";

}
