package com.example.alumno.bibliotecaexam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BDHelper sql;
    BDManager manager= new BDManager();
    ArrayList<Libro>al=new ArrayList();
    Adaptador adaptador;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.listalibros);
        //Inicializamos objetos para trabajar con BBDD.
        //manager = new BDManager(this);
        final ListView lista = (ListView) findViewById(R.id.listalibros);
        //Abrimos la base de datos en modo escritura
        sql = new BDHelper(this, "examen.db", null, 1);
        // Usamos un adaptador para dibujar las opciones de la lista
        // Volcamos los datos de la tabla notas en el ListView.

        adaptador = new Adaptador(this,manager.volcarDatosAL());
        //Se comprueba si la conexión ha sido correcta.
        //manager.conectado(this);
        // Establecemos el adaptador del Listview
        lista.setAdapter(adaptador);
        registerForContextMenu(lista);
        //Si se pulsa alguno de los items del ListView:
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast1;
                if(adaptador.getItem(i).getFav()==1){
                    adaptador.getItem(i).setFav(0);//Se cambia la variable de tipo boolean asociada al objeto Opción del adaptador.
                    //Mensaje informativo.
                    toast1 = Toast.makeText(getApplicationContext(),"Has quitado como favorito '"+adaptador.getItem(i).getTitulo()+"'", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else{
                    adaptador.getItem(i).setFav(1);//Se cambia la variable de tipo boolean asociada al objeto Opción del adaptador.
                    //Mensaje informativo.
                    toast1 = Toast.makeText(getApplicationContext(),"Has marcado como favorito '"+adaptador.getItem(i).getTitulo()+"'", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                //Se vuelve a asignar el adaptador.
                lista.setAdapter(adaptador);
            }

         //----Guardado de favs-----



        });

        //Cerramos conexión con base de datos.
//        manager.close();
    }

    //Asigna el menú ActionBar.
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    //Asigna el menú portada a un objeto.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MainActivity.super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        // Definimos la cabecera del menú de artículos
        menu.setHeaderTitle("Operaciones sobre ");//Asigna el título del menú de artículos.
        // Inflamos el menú artículos
        inflater.inflate(R.menu.menu_portada, menu);
        // -----Para hacer grande la portada----

    }
    //Asigna acciones a las opciones del menú de artículos.
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Objeto que ayuda a conseguir información de la opción escogida.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            //Opción de mostrar:
            /*case R.id.mostrar:
                //Mostra Portada
                //Si se clica en algún objeto del ListView:
                  lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Se llamará a una segunda actividad, a la que se enviará la referencia numérica de la imagen del item del ListView.
                        Intent intent = new Intent(MainActivity.this,Foto.class);
                        intent.putExtra("ref",adaptador.getItem(i).getRef());
                        intent.putExtra("titulo",adaptador.getItem(i).getNombre());
                        intent.putExtra("latin",adaptador.getItem(i).getLatin());
                        startActivityForResult(intent,SECONDARY_ACTIVITY_TAG);

                    }
            });return true;*/

            default:
                return super.onContextItemSelected(item);
        }
    }
}

