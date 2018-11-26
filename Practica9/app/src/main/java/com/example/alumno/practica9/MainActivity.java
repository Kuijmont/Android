package com.example.alumno.practica9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements FragmentListado.FMarinaListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
        FragmentListado frgListado =
                (FragmentListado) getSupportFragmentManager()
                        .findFragmentById(R.id.FrgListado);

        frgListado.setFMarinaListener(this);
    }

    @Override
    public void onFMarinaSeleccionado(FaunaMarina fm) {
        if(findViewById(R.id.FrgDetalle) != null) {
            ((FragmentDetalle)getSupportFragmentManager()
                    .findFragmentById(R.id.FrgDetalle)).mostrarDetalle(String.valueOf(fm.getRef()));
        }
        else {
            Intent i = new Intent(this, Foto.class);
            i.putExtra(Foto.EXTRA_TEXTO,fm.getRef());
            startActivity(i);
        }
    }
}
