package com.studio.chan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.chan.pwd.Adapters.adapter_item;
import com.studio.chan.pwd.Datos.Busqueda;
import com.studio.chan.pwd.Datos.Read;
import com.studio.chan.pwd.Dialogo.DialogoBusqueda;
import com.studio.chan.pwd.Objeto.Pswd;
import com.studio.chan.pwd.Objeto.pswdExtends;
import com.studio.chan.pwd.R;

import java.util.ArrayList;

/**
 * Created by oscar on 10/02/20.
 */

public class ListPwd extends AppCompatActivity implements DialogoBusqueda.OnDialogoBusquedaListener{

    private ListView listView;
    private ArrayList<pswdExtends> list;
    private adapter_item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Password");

        list = Read.getFiles();
        adapter = new adapter_item(this,list);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;
                pswdExtends pwd = list.get(posicion);
                Intent editar = new Intent(ListPwd.this, Editar.class);
                editar.putExtra("nombre",pwd.getTitulo());
                editar.putExtra("usuario",pwd.getUsuario());
                editar.putExtra("password",pwd.getPassword());
                editar.putExtra("nombreobj",pwd.getNameObj());
                startActivity(editar);
            }
        });

        if (!Read.isExistsPswd()) {
            Snackbar.make(findViewById(android.R.id.content), "Por seguridad debe agregarle una contraseña a la app", Snackbar.LENGTH_LONG).setAction("Agregar", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ListPwd.this,Configuraciones.class));
                }
            }).show();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(ListPwd.this, Agregar.class);
                startActivity(add);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter.UpdateList(Read.getFiles());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                Intent config = new Intent(ListPwd.this, Configuraciones.class);
                startActivity(config);
                return true;
            case R.id.search:
                FragmentManager fragmentManager = getSupportFragmentManager();
                new DialogoBusqueda().show(fragmentManager, "Buscar");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void Buscar(String nameFile) {
        pswdExtends pswd = Busqueda.Buscar(getApplicationContext(),nameFile);
        if (pswd != null){
            //Snackbar.make(findViewById(android.R.id.content),"Encontrado",Snackbar.LENGTH_SHORT).show();
            Intent actividadBusqueda = new Intent(ListPwd.this, ResBusqueda.class);
            actividadBusqueda.putExtra("objeto",pswd);
            startActivity(actividadBusqueda);
        }else{
            Snackbar.make(findViewById(android.R.id.content),"Nombre de cuenta no encontrado",Snackbar.LENGTH_SHORT).show();
        }
    }
}
