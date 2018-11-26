package com.example.alumno.practica9;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<FaunaMarina> {
    Activity contexto;

    Adaptador(Fragment contexto, ArrayList<FaunaMarina> datos) {
        // Llamamos al constructor de la clase superior
        //se le pasa el xml que genera la fila y el array de objetos
        super(contexto.getActivity(), R.layout.layout_lista, datos);
        this.contexto = contexto.getActivity();
    }
    //Esto es lo que se invoca cada vez que haya que mostrar un elemento de la lista

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = contexto.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_lista, null);

        //Mediante getItem cargamos cada uno de los objetos del array
        FaunaMarina elemento = getItem(position);

        ImageView imagen = (ImageView) item.findViewById(R.id.imageView);
        TextView principal = (TextView) item.findViewById(R.id.textViewNombre);
        TextView latin = (TextView) item.findViewById(R.id.textViewNombreLatin);
        TextView tam = (TextView) item.findViewById(R.id.textViewLongitud);
        TextView habitat = (TextView) item.findViewById(R.id.textViewHabitat);

        imagen.setImageResource(elemento.getRef());
        principal.setText(elemento.getNombre());
        latin.setText(elemento.getLatin());
        tam.setText(elemento.getTamano());
        habitat.setText(elemento.getHabitat());

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return (item);
    }
}
