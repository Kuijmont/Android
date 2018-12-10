package com.example.alumno.mibiblioteca2018;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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



    /*
     * Constructor

    public BDManager(Context context){
        helper = new BDHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }*/
}
