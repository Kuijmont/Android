package com.example.alumno.bibliotecaexam;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Portada extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_portada);

        //Se recoge el valor numérico que devuelve la actividad principal y se asigna la imagen correspondiente al valor en el objeto ImageView de la actividad.
        int num = getIntent().getIntExtra("ref",0);
        ImageView im = (ImageView)findViewById(R.id.imagenPortada);
        im.setImageResource(num);

        String titulo = getIntent().getStringExtra("titulo");
        setTitle(titulo);
    }
}
