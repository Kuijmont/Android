package com.example.alumno.bibliotecaexam;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Libro> {

    //Declaramos un arrayList que contendrá objetos de la clase Libro.
    private ArrayList<Libro> al;
    Activity contexto;
    // Contructor del adaptador
    public Adaptador(Activity contexto, ArrayList<Libro> al) {
        // Llamamos al constructor de la clase superior y le pasamos el xml que genera la fila y el arraylist de objetos
        super(contexto, R.layout.list_item, al);
        //guardamos el parametro en la variable de clase.

        //this.al = al;
        this.contexto=contexto;
    }

//Esto es lo que se invoca cada vez que haya que mostrar un elemento de la lista

  /*  public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        item = inflater.inflate(R.layout.list_item, null);
        //Mediante getItem cargamos cada uno de los objetos del array
        Libro elemento = getItem(position);

        //ImageView imagen = (ImageView) item.findViewById(R.id.i);
        TextView tit = (TextView) item.findViewById(R.id.LblTitulo);
        TextView aut = (TextView) item.findViewById(R.id.LblAutor);

        //Guarda titulo y autor
        tit.setText(elemento.getTitulo());
        aut.setText(elemento.getAutor());
        //Según el fav lo dibuja y tacha
        if (elemento.getFav()==1) {
            tit.setPaintFlags(tit.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            tit.setTextColor(Color.parseColor("#00FF00"));
        } else {
            tit.setPaintFlags(tit.getPaintFlags()
                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
            tit.setTextColor(Color.parseColor("#FF0000"));
        }
        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }*/

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = contexto.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_item, null);

        //Mediante getItem cargamos cada uno de los objetos del array
        Libro mielemento = getItem(position);

        //ImageView imagen = (ImageView) item.findViewById(R.id.imagenPortada);
        TextView tvl1 = (TextView) item.findViewById(R.id.LblTitulo);
        TextView tvl2 = (TextView) item.findViewById(R.id.LblAutor);

        tvl1.setText(mielemento.getTitulo());
        tvl2.setText(mielemento.getAutor());
       // if(mielemento.getPortada()==1)

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }
}
