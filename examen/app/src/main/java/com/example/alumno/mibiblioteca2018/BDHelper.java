package com.example.alumno.mibiblioteca2018;

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
    public BDHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(contexto, nombre, factory, version);

        this.contexto=contexto;
    }

    //Creación de la base de datos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        try{
            String linea;
            InputStream is = contexto.getResources().openRawResource(R.raw.tituloautor);

            BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
            linea = bfr.readLine();
            while(linea!=null) {
                sqLiteDatabase.execSQL(linea);
                linea = bfr.readLine();
            }
            is.close();

        }catch (Exception ex){
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");;
        }
    }

    //Si existe la tabla la borra y crea una nueva
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LIBROS");
        sqLiteDatabase.execSQL(BDManager.CREATE_TABLE);
    }
}
