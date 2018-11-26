package com.example.alumno.practica9;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FragmentListado extends Fragment {

    //Declaramos dos ArrayList que van a ser utilizados para manipular el contenido del ListView.
    ArrayList<FaunaMarina> peces = new ArrayList<>();
    ArrayList <FaunaMarina> algas = new ArrayList<>();
    //Guardamos en una variable la actividad actual.
    final Fragment a = this;
    //Se declara el adaptador del ListView.
    Adaptador adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        //Declaramos dos objetos, uno de tipo ListView y otro de tipo Spinner, para manipular el contenido de la app.
        final ListView lista = (ListView)getView().findViewById(R.id.lista);
        final Spinner spinner = (Spinner)getView().findViewById(R.id.spinner);
        final EditText title = (EditText)getView().findViewById(R.id.editText);
        //Se rellena el Spinner.
        contenidoSpinner(spinner);
        //Se rellenan los dos ArrayList que van a ser utilizados para manipular el contenido del ListView.
        rellenarPeces();
        rellenarAlgInver();
        //Se establece el adaptador por defecto del ListView.
        adaptador = new Adaptador(this,peces);
        lista.setAdapter(adaptador);
        //Si se cambia la opción del Spinner:
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Si la opcion es "Peces" se borrará el contenido del ListView y se establecerá como su nuevo contenido el correspondiente al ArrayList "peces".
                if(position==0){
                    title.setText("Peces del Parque");
                    adaptador = new Adaptador(a,peces);
                    lista.setAdapter(adaptador);
                }
                //Si la opcion es "Algas e invertebrados" se borrará el contenido del ListView y se establecerá como su nuevo contenido el correspondiente al ArrayList "alginvert".
                else
                {
                    title.setText("Algas e invertebrados del Parque");
                    adaptador = new Adaptador(a,algas);
                    lista.setAdapter(adaptador);
                }
            }
            @Override
            //Si no se selecciona nada en el Spinner, no se hará nada.
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        //Si se clica en algún objeto del ListView:
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (listener!=null) {
                    listener.onFMarinaSeleccionado(
                            (FaunaMarina) lista.getAdapter().getItem(i));
                }
            }
        });
    }

    //Método que inserta dos valores predeterminados en un spinner.
    public void contenidoSpinner(Spinner s){
        String [] array = {"Peces","Algas e invertebrados"};
        s.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, array));
    }

    //Método que rellenar el ArrayList peces con los valores del txt correspondiente.
    public void rellenarPeces(){
        String linea="ERROR.";
        try
        {
            InputStream fraw =
                    getResources().openRawResource(R.raw.peces);

            BufferedReader brin =
                    new BufferedReader(new InputStreamReader(fraw));
            linea=brin.readLine();
            while (linea!=null){
                String [] splitter= linea.split(",");
                peces.add(new FaunaMarina(asignarImagen(splitter[0]),splitter[1],splitter[2],splitter[3],splitter[4]));

                linea = brin.readLine();
            }
            brin.close();
            fraw.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), linea, Toast.LENGTH_SHORT).show();
        }
    }

    //Método que rellenar el ArrayList alginvert con los valores del txt correspondiente.
    public void rellenarAlgInver(){
        String linea="ERROR.";
        try
        {
            InputStream fraw =
                    getResources().openRawResource(R.raw.algaseinvertebrados);

            BufferedReader brin =
                    new BufferedReader(new InputStreamReader(fraw));

            linea=brin.readLine();
            while (linea!=null){
                String [] splitter= linea.split(",");
                algas.add(new FaunaMarina(asignarImagen(splitter[0]),splitter[1],splitter[2],splitter[3],splitter[4]));
                linea = brin.readLine();
            }
            brin.close();
            fraw.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), linea, Toast.LENGTH_SHORT).show();
        }
    }

    //Método que devuelve la referencia a una imagen según el String que reciba.
    public int asignarImagen(String nombre){
        switch(nombre){
            case "anemona":
                return R.drawable.anemona;
            case "bigaro":
                return R.drawable.bigaro;
            case "cangrejocorredor":
                return R.drawable.cangrejocorredor;
            case "cangrejo":
                return R.drawable.cangrejo;
            case "caracola":
                return R.drawable.caracola;
            case "coralnaranja":
                return R.drawable.coralnaranja;
            case "cystoseira":
                return R.drawable.cystoseira;
            case "erizoblanco":
                return R.drawable.erizoblanco;
            case "erizocomun":
                return R.drawable.erizocomun;
            case "erizovioleta":
                return R.drawable.erizovioleta;
            case "esponja":
                return R.drawable.esponja;
            case "estrellaroja":
                return R.drawable.estrellaroja;
            case "holoturia":
                return R.drawable.holoturia;
            case "lapa":
                return R.drawable.lapa;
            case "lijo":
                return R.drawable.lijo;
            case "medusa":
                return R.drawable.medusa;
            case "medusahuevo":
                return R.drawable.medusahuevo;
            case "nacra":
                return R.drawable.nacra;
            case "ofiura":
                return R.drawable.ofiura;
            case "padina":
                return R.drawable.padina;
            case "pulpo":
                return R.drawable.pulpo;
            case "tomatedemar":
                return R.drawable.tomatedemar;
            case "babosa":
                return R.drawable.babosa;
            case "baila":
                return R.drawable.baila;
            case "castanuela":
                return R.drawable.castanuela;
            case "castanuelaalevin":
                return R.drawable.castanuelaalevin;
            case "corvallo":
                return R.drawable.corvallo;
            case "doncella":
                return R.drawable.doncella;
            case "espeton":
                return R.drawable.espeton;
            case "espetonalevin":
                return R.drawable.espetonalevin;
            case "herrera":
                return R.drawable.herrera;
            case "mero":
                return R.drawable.mero;
            case "meroalevin":
                return R.drawable.meroalevin;
            case "mojarra":
                return R.drawable.mojarra;
            case "morena":
                return R.drawable.morena;
            case "oblada":
                return R.drawable.oblada;
            case "pezderoca":
                return R.drawable.pezderoca;
            case "pezverde":
                return R.drawable.pezverde;
            case "pezverdehembra":
                return R.drawable.pezverdehembra;
            case "rascacio":
                return R.drawable.rascacio;
            case "raspallon":
                return R.drawable.raspallon;
            case "reyezuelo":
                return R.drawable.reyezuelo;
            case "salpa":
                return R.drawable.salpa;
            case "sargo":
                return R.drawable.sargo;
            case "sargosoldadooreal":
                return R.drawable.sargosoldadooreal;
            case "serrano":
                return R.drawable.serrano;
            case "tordo":
                return R.drawable.tordo;
            case "vaquilla":
                return R.drawable.vaquilla;
            default:
                Toast.makeText(getActivity(), "Sin resultados.", Toast.LENGTH_SHORT).show();
                break;
        }
        return 0;
    }

    private FMarinaListener listener;

    public interface FMarinaListener {
        void onFMarinaSeleccionado(FaunaMarina fm);
    }

    public void setFMarinaListener(FMarinaListener listener) {
        this.listener=listener;
    }

}
