package com.example.alumno.listadecompra;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Opcion> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual
    Adaptador(Activity contexto, ArrayList<Opcion> datos) {
        // Llamamos al constructor de la clase superior
        //se le pasa el xml que genera la fila y el array de objetos
        super(contexto, R.layout.producto, datos);
        this.contexto = contexto;
    }

    // Metodo que dibuja la Vista de cada Opcion
    // Se invoca cada vez que haya que mostrar un elemento de la lista.
    // Genera cada elemento del array
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = contexto.getLayoutInflater();
        View item = inflater.inflate(R.layout.producto, null);
        //Cargamos cada uno de los objetos del array
        Opcion mielemento=getItem(position);

        TextView producto = (TextView)item.findViewById(R.id.textoArticulo);

        producto.setText(mielemento.getProducto());

        if (mielemento.isComprado()) {
            producto.setPaintFlags(producto.getPaintFlags() |
                    Paint.STRIKE_THRU_TEXT_FLAG);
            producto.setTextColor(Color.parseColor("#00FF00"));
        } else {
            producto.setPaintFlags(producto.getPaintFlags()
                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
            producto.setTextColor(Color.parseColor("#FF0000"));
        }

        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return(item);
    }
}
