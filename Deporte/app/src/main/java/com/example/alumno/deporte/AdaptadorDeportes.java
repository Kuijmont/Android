package com.example.alumno.deporte;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.alumno.deporte.R.layout.*;

public class AdaptadorDeportes extends ArrayAdapter<Opcion> {

    Activity contexto;
    // Contructor del adaptador usando el contexto de la aplicacion actual
    AdaptadorDeportes(Activity contexto, Opcion[] datos) {
        // Llamamos al constructor de la clase superior
        //se le pasa el xml que genera la fila y el array de objetos
        super(contexto, R.layout.deportes, datos);
        this.contexto = contexto;
    }
    // Metodo que dibuja la Vista de cada Opcion
    // Se invoca cada vez que haya que mostrar un elemento de la lista.
    // Genera cada elemento del array

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = contexto.getLayoutInflater();
        View item = inflater.inflate(R.layout.deportes, null);
        //Cargamos cada uno de los objetos del array
        Opcion mielemento=getItem(position);

        ImageView imagen=(ImageView)item.findViewById(R.id.Imagen);
        TextView deporte = (TextView)item.findViewById(R.id.Deporte);
        CheckBox checkBox = (CheckBox)item.findViewById(R.id.checkbox);

        imagen.setImageResource(mielemento.getIcono());
        deporte.setText(mielemento.getNombre());
        checkBox.setChecked(mielemento.isCheckbox());
        // Devolvemos la Vista (nueva o reutilizada) que dibuja la opcion
        return(item);
    }

}
