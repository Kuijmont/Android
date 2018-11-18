package com.example.alumno.practica8;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declaramos dos ArrayList que van a ser utilizados para manipular el contenido del ListView.
    ArrayList<FaunaMarina> peces = new ArrayList<>();
    ArrayList <FaunaMarina> algas = new ArrayList<>();
    //Guardamos en una variable la actividad actual.
    final Activity a = this;
    //Se declara el adaptador del ListView.
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adaptador = new Adaptador(this,peces);
        final ListView lista = (ListView) findViewById(R.id.lista);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final EditText title = (EditText) findViewById(R.id.editText);
        //Se rellenan los dos ArrayList que van a ser utilizados para manipular el contenido del ListView.
        rellenarPeces();
        rellenarAlgInver();
        //Se establece el adaptador por defecto del ListView.
        adaptador = new Adaptador(this,peces);
        lista.setAdapter(adaptador);
        //Si se cambia la opción del Spinner:
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
                else{
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
                //Se llamará a una segunda actividad, a la que se enviará la referencia numérica de la imagen del item del ListView.
                Intent intent = new Intent(MainActivity.this,Foto.class);
                intent.putExtra("ref",adaptador.getItem(i).getRef());
                startActivityForResult(intent,SECONDARY_ACTIVITY_TAG);

            }
        });
    }
    //Método que rellena el ArrayList de peces con los valores del txt correspondiente.
    private void rellenarPeces() {
        String linea="ERROR.";
        try{
            InputStream is = getResources().openRawResource(R.raw.peces);
            BufferedReader bfr =  new BufferedReader(new InputStreamReader(is));
            linea=bfr.readLine();
            while (linea!=null){
                String [] splitter= linea.split(",");
                peces.add(new FaunaMarina(asignarImagen(splitter[0]),splitter[1],splitter[2],splitter[3],splitter[4]));
                linea = bfr.readLine();
            }
            bfr.close();
            is.close();
        } catch (Exception ex) {
            Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
        }
    }
    //Método que rellena el ArrayList de peces con los valores del txt correspondiente.
    private void rellenarAlgInver() {
        String linea="ERROR.";
        try{
            InputStream is = getResources().openRawResource(R.raw.algaseinvertebrados);
            BufferedReader bfr =  new BufferedReader(new InputStreamReader(is));
            linea=bfr.readLine();
            while (linea!=null){
                String [] splitter= linea.split(",");
                algas.add(new FaunaMarina(asignarImagen(splitter[0]),splitter[1],splitter[3],splitter[4],splitter[5]));
                linea = bfr.readLine();
            }
            bfr.close();
            is.close();
        } catch (Exception ex) {
            Toast.makeText(this, linea, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Sin resultados.", Toast.LENGTH_SHORT).show();
                break;
        }
        return 0;
    }
    //Método que gestiona el flujo entre actividades de la app.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String respuesta="";
        //Si se cancela la operación de la actividad secundaria se mostrará un mensaje informativo de tipo Toast.
        if (resultCode == RESULT_CANCELED){
            respuesta = "Has salido de la vista de la foto en pantalla completa.";
            Toast toast1 = Toast.makeText(getApplicationContext(),respuesta, Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    private static final int SECONDARY_ACTIVITY_TAG = 1;
}
