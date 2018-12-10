package com.example.alumno.mibiblioteca2018;

import android.app.Activity;
import android.R;
import android.R.*;
import android.view.LayoutInflater;
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

        this.al = al;
        this.contexto=contexto;
    }
//Esto es lo que se invoca cada vez que haya que mostrar un elemento de la lista

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = contexto.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_lista, null);

        //Mediante getItem cargamos cada uno de los objetos del array
        Libro elemento = getItem(position);

        ImageView imagen = (ImageView) item.findViewById(R.id.imageView);
        TextView principal = (TextView) item.findViewById(R.id.textViewNombre);
        TextView latin = (TextView) item.findViewById(R.id.textViewNombreLatin);
        TextView tam = (TextView) item.findViewById(R.id.textViewLongitud);
        TextView habitat = (TextView) item.findViewById(R.id.textViewHabitat);

        imagen.setImageResource(elemento.getId());
        principal.setText(elemento.getTitulo());
        latin.setText(elemento.getAutor());
        tam.setText(elemento.getPortada());
        habitat.setText(elemento.getFav());

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }

}
