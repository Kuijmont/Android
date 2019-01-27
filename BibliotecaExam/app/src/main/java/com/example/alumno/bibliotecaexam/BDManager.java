package com.example.alumno.bibliotecaexam;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class BDManager {
    /*
     * Atributos
     */
    private BDHelper helper;
    public SQLiteDatabase sqLiteDatabase;
    public static final String CREATE_TABLE = "create table LIBROS (\n" +
            "_id INTEGER PRIMARY KEY,\n" +
            "TITULO TEXT,\n" +
            "PORTADA INTEGER,\n" +
            "FAVORITO INTEGER)";


    //Métodos
    public void conectado(Context contexto){
        if(sqLiteDatabase != null){
            Toast.makeText(contexto, "Conectado correctamente a la BBDD.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(contexto, "No conectado.", Toast.LENGTH_SHORT).show();
        }
    }
    //Método que cierra la conexión con la base de datos.
    public void close(){
        sqLiteDatabase.close();
    }

    public ArrayList<Libro> volcarDatosAL(){
        ArrayList<Libro> libro = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM LIBROS", null);
        if (c.moveToFirst()) {
            do {
                String id= c.getString(0);
                String titulo = c.getString(1);
                String autor = c.getString(2);
                int portada = c.getInt(3);
                int fav = c.getInt(4);
                libro.add(new Libro(Integer.valueOf(id),titulo,autor,portada,fav));
            } while(c.moveToNext());
        }
        return libro;
    }
}
