package com.example.alumno.practica8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by Frans.
 */

public class Foto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_foto);

        //Se recoge el valor numérico que devuelve la actividad principal y se asigna la imagen correspondiente al valor en el objeto ImageView de la actividad.
        int num = getIntent().getIntExtra("ref",0);
        ImageView im = (ImageView)findViewById(R.id.imageView2);
        im.setImageResource(num);

        String titulo = getIntent().getStringExtra("titulo")+" ("+ (getIntent().getStringExtra("latin"))+")";
        setTitle(titulo);
    }

}
