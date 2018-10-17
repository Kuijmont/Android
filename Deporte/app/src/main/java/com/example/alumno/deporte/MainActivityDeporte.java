package com.example.alumno.deporte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivityDeporte extends AppCompatActivity {

    private ListView LstOpciones;
    final String[] datos =
            new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_deporte);
        //Asignamos el objeto ListView con el id de la etiqueta ListView del
        //layout principal e inicializamos el boton
        final ListView list = (ListView)findViewById(R.id.miLista);
        Button boton = (Button) findViewById(R.id.button);

        //Declaramos enteros que apunten a las imágenes de drawable.
        int baloncesto = R.drawable.baloncesto;
        int futbol = R.drawable.futbol;
        int motociclismo = R.drawable.motociclismo;
        int natacion = R.drawable.natacion;
        int golf = R.drawable.golf;
        int atletismo = R.drawable.atletismo;
        int pingpong = R.drawable.pingpong;

        // Creamos los objetos y los guardamos en un array
        final Opcion [] opcions = new Opcion[7];
        opcions[0]=new Opcion("Baloncesto",baloncesto,false);
        opcions[1]=new Opcion("Fútbol",futbol,false);
        opcions[2]=new Opcion("Motociclismo",motociclismo,false);
        opcions[3]=new Opcion("Natación",natacion,false);
        opcions[4]=new Opcion("Golf",golf,false);
        opcions[5]=new Opcion("Atletismo",atletismo,false);
        opcions[6]=new Opcion("Ping Pong",pingpong,false);

        // Usamos un adaptador para dibujar las opciones de la lista
        final AdaptadorDeportes adaptador = new AdaptadorDeportes(this,opcions);

        // Establecemos el adaptador del Listview
        list.setAdapter(adaptador);

        //Al selecionar algún deporte de la ListView
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Si se hace clic sobre una opción mostramos un mensaje
                Toast toast1 = Toast.makeText(getApplicationContext(),"Seleccionado", Toast.LENGTH_SHORT);
                toast1.show();
                if(adaptador.getItem(i).isCheckbox()){
                    adaptador.getItem(i).setCheckbox(false);
                    list.setAdapter(adaptador);
                }else{
                    adaptador.getItem(i).setCheckbox(true);
                    list.setAdapter(adaptador);
                }
            }
        });
        //Cuando se pulsa el botón de aceptar:
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked=false;//Se crea una variable booleana para controlar si se ha seleccionado o no algún item del ListView.
                String like="Te gusta ";//Se crea un String para ver si se ha seleccionado algún elemento, la cadena devolverá un objeto Toast.
                //Recorremos el adaptador y comprobamos los elementos seleccionados, el String de like se irá modificando.
                for(int i=0;i<7;i++){
                    if(adaptador.getItem(i).isCheckbox()){//Comprueba si el elemento está seleccionado.
                        checked=true;
                        if(i!=3){
                            like=like+"el "+adaptador.getItem(i).getNombre()+",";
                        }else{
                            like=like+"la "+adaptador.getItem(i).getNombre()+",";
                        }
                    }
                }
                Toast toast1;//Declaramos un objeto tipo Toast para devolver un mensaje.
                if(checked){//Si algo ha sido seleccionado, comenzaremos a modificar "gustos".

                    String [] splitter = like.split(",");//Eliminamos las comas, que se habían utilizado como separador, del String "like" para crear un array de String con su contenido dividido.
                    for(int i=0;i<splitter.length;i++) {
                        if(i==0){
                            like=splitter[i];
                            if(splitter.length==1){
                                like=like+".";
                            }
                        }
                        if(splitter.length!=1) {
                            if(i!=0){
                                like=like+splitter[i];
                            }
                            if(i==splitter.length-2){
                                like = like + " y ";
                            }else{
                                if(i==splitter.length-1){
                                    like = like + ".";
                                } else{
                                    like = like + " ,";
                                }
                            }
                        }
                    }
                    //Finalmente se lanzá el mensaje de tipo Toast con el contenido de "like".
                    toast1 = Toast.makeText(getApplicationContext(), like, Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else//Si no hubiera seleccionado ningún elemento del ListView, se lanza otro mensaje de tipo Toast.
                {
                    toast1 = Toast.makeText(getApplicationContext(),"No has seleccionado ninguna opción.", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
    }
}
