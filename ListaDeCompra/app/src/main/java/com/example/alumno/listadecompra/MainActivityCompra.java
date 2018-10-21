package com.example.alumno.listadecompra;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
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
import android.widget.AdapterView.AdapterContextMenuInfo;

import java.util.ArrayList;

public class MainActivityCompra extends AppCompatActivity implements Altas.EditNameDialogListener,Modificar.EditNameDialogListenerM{

    private final static String STATE_PRODUCTOS = "producto"; //Variables de guardado para giro de pantalla
    private final static String STATE_COMPRA = "comprado"; //Variables de guardado para giro de pantalla
    ArrayList<String> producto = new ArrayList<>(); //ArrayList para guardado cuando gire la pantalla
    ArrayList<Integer> comprado = new ArrayList<>(); //ArrayList para guardado cuando gire la pantalla
    private ListView listadoPrincipal; //Objeto ListView para trabajar con la ListView del main.
    Adaptador adaptador;
    ArrayList<Opcion> productos = new ArrayList<>();//ArrayList para almacenar objetos de tipo Opcion.
    FragmentManager fm = getSupportFragmentManager();//Objeto para llamar DialogFragment.
    int indice=0;//Variable auxiliar para almacenar un entero.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_compra);
        listadoPrincipal = (ListView) findViewById(R.id.ListaOpciones);
        if(savedInstanceState != null) {//Carga los valores al girar la pantalla.
            productos.clear();//Se resetea el ArrayList "productos".
            producto= savedInstanceState.getStringArrayList(STATE_PRODUCTOS);
            comprado=savedInstanceState.getIntegerArrayList(STATE_COMPRA);
            for(int i=0;i<producto.size();i++){
                boolean a;
                if(comprado.get(i)==1){
                    a=true;
                }else{
                    a=false;
                }
                productos.add(new Opcion(producto.get(i),a));
            }
        }else{
            //Inicializamos el ArrayList que contiene objetos de tipo Opción.
            productos.add(new Opcion("Pan",true));
            productos.add(new Opcion("Leche",false));
            productos.add(new Opcion("Periódico",true));
            productos.add(new Opcion("Fruta",false));
            productos.add(new Opcion("Carne",true));
        }

        //Creamos un adaptador al que aplicamos el ArrayList anterior.
        adaptador = new Adaptador(this,productos);
        listadoPrincipal.setAdapter(adaptador);
        registerForContextMenu(listadoPrincipal);//Aplica el menú contextual a los items del ArrayList.
        //Si se pulsa alguno de los items del ListView:
        listadoPrincipal.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast1;
                /*
                    -Se cambia la variable de tipo boolean asociada al objeto Opción del adaptador.
                    -Se muestra un mensaje informativo de tipo Toast.
                    -Se vuelve a asignar el adaptador, ya modificado, al ListView.
                 */
                if(adaptador.getItem(i).isComprado()){
                    adaptador.getItem(i).setComprado(false);//Se cambia la variable de tipo boolean asociada al objeto Opción del adaptador.
                    //Mensaje informativo.
                    toast1 = Toast.makeText(getApplicationContext(),"No has comprado '"+adaptador.getItem(i).getProducto()+"'", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else{
                    adaptador.getItem(i).setComprado(true);//Se cambia la variable de tipo boolean asociada al objeto Opción del adaptador.
                    //Mensaje informativo.
                    toast1 = Toast.makeText(getApplicationContext(),"Has comprado '"+adaptador.getItem(i).getProducto()+"'", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                //Se vuelve a asignar el adaptador.
                listadoPrincipal.setAdapter(adaptador);
            }
        });
    }

    //Asigna el menú ActionBar.
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    //Actúa según lo elegído en la ActionBar.
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Menu:
                //Si se pulsa la cruz, mostrará un diálogo para añadir un Producto.
                Altas dialogo = new Altas();
                dialogo.show(fm, "Dialog Fragment");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //Asigna el menú artículos a un objeto.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        if(v.getId() == R.id.ListaOpciones){ //Lo asigna al ListView.
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
            // Definimos la cabecera del menú de artículos
            menu.setHeaderTitle("Operaciones sobre "+productos.get(info.position).getProducto());//Asigna el título del menú de artículos.
            // Inflamos el menú artículos
            inflater.inflate(R.menu.menu_articulos, menu);
        }
    }

    //Asigna acciones a las opciones del menú de artículos.
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Objeto que ayuda a conseguir información de la opción escogida.
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            //Opción de borrar:
            case R.id.borrar:
                //Borra el producto del ArrayList, reinicia el adaptador y vuelve a asignar al ListView el adaptador reiniciado.
                productos.remove(info.position);
                adaptador = new Adaptador(this,productos);
                listadoPrincipal.setAdapter(adaptador);
                return true;
            //Si se selecciona la opción de editar:
            case R.id.editar:
                /*
                    -Se utiliza la variable auxiliar "indice" para guardar la posición en la que se encuentra el elemento del ListView.
                    -Se llama al diálogo respectivo y éste actúa en relación a la opción escogida por el usuario.
                 */
                indice=info.position;//Utilizamos la variable auxiliar.
                Modificar dialogo = new Modificar();//Se llama al diálogo de modificaciones.
                dialogo.show(fm, "Dialog Fragment");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void onSaveInstanceState(Bundle outInstance){//Permite guardar valores para que cuando se gire la pantalla no se resetee.
        super.onSaveInstanceState(outInstance);
        //Se resetean los ArrayList
        producto.clear();
        comprado.clear();
        for(int i=0;i<productos.size();i++){
            producto.add(productos.get(i).getProducto());
            if(productos.get(i).isComprado()){
                comprado.add(1);
            }else{
                comprado.add(0);
            }
        }
        outInstance.putIntegerArrayList(STATE_COMPRA,comprado);
        outInstance.putStringArrayList(STATE_PRODUCTOS,producto);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String name) {
        /*
            -Se comprueba qué diálogo ha sido llamado.
            -Se actúa en consecuencia.
         */
        //Comprobamos cuál de los dos diálogos ha llamado al método.
        if(dialog.getClass().toString().equals("class com.example.alumno.listadecompra.Modificar")){
            //Si Modificar ha sido el que ha llamado al método:
            /*
                -Guardamos el valor booleano asociado al elemento de tipo Articulo en el ListView, en la posición referenciada por la variable auxiliar "indice".
                -Se elimina el Articulo que ocupaba la posición "indice" del ArrayList "productos".
                -Se añade un nuevo Articulo a "productos" en la posición "indice".
                -Se reinicia el adaptador con "productos" actualizado.
                -Se asigna el adaptador actualizado al ListView.
             */
            boolean comprado = productos.get(indice).isComprado(); //Guarda valor booleano.
            productos.remove(indice);//Borra el Articulo en "indice".
            productos.add(indice,new Opcion(name,comprado));//Añade el nuevo Articulo.
            adaptador = new Adaptador(this,productos);//Reinicia el adaptador.
            listadoPrincipal.setAdapter(adaptador);//Asigna el adaptador al ListView.
        }
        else{
            //Si Altas ha sido el que ha llamado al método:
            adaptador.add(new Opcion(name,false));//Se añade un nuevo elemento al ListView.
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
