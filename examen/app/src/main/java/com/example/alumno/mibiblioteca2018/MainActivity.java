package com.example.alumno.mibiblioteca2018;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BDHelper sql;
    ArrayList<Libro>al=new ArrayList();
    Adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lista = (ListView) findViewById(R.id.listalibros);
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        sql = new BDHelper(this, "examen.db", null, 1);
        // Usamos un adaptador para dibujar las opciones de la lista
        adaptador = new Adaptador(this, al);
        registerForContextMenu(lista);



    }
}
