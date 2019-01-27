package com.example.alumno.bibliotecaexam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BDHelper extends SQLiteOpenHelper{

    private Context contexto;

    //Constructor.
    public BDHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);

        this.contexto=contexto;
    }

    //Cuando se crea este objeto, se crea la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creaci?n de la tabla
        try
        {
            String linea;
            InputStream fraw = contexto.getResources().openRawResource(R.raw.tituloautor);

            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            linea = brin.readLine();
            while(linea!=null) {

                db.execSQL(linea);
                linea = brin.readLine();
            };
            fraw.close();

        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS LIBROS");

    }
}
