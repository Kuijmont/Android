package com.example.alumno.practica9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Frans.
 */

public class Foto extends AppCompatActivity {
    public static final String EXTRA_TEXTO =
            "imagen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detalle);

        //Se recoge el valor numérico que devuelve la actividad principal y se asigna la imagen correspondiente al valor en el objeto ImageView de la actividad.
        int num = getIntent().getIntExtra("ref",0);
        ImageView im = (ImageView)findViewById(R.id.imageView2);
        im.setImageResource(num);
        FragmentDetalle detalle =
                (FragmentDetalle)getSupportFragmentManager()
                        .findFragmentById(R.id.FrgDetalle);
        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
        String titulo = getIntent().getStringExtra("titulo")+" ("+ (getIntent().getStringExtra("latin"))+")";
        setTitle(titulo);
    }

}
