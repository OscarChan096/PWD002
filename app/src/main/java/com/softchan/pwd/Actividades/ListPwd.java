package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.softchan.pwd.Adapters.adapter_item;
import com.softchan.pwd.Datos.Read;
import com.softchan.pwd.Dialogo.DialogoBusqueda;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 10/02/20.
 */

// sin funcion esta clase
public class ListPwd extends AppCompatActivity implements DialogoBusqueda.OnDialogoBusquedaListener{

    private ListView listView;
    private List<Pswd> pswdDataList = new ArrayList<>();
    //private ArrayList<pswdExtends> list;
    private adapter_item adapter;
    private EditText busqueda;
    private DBAcces dbAcces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Password");

        dbAcces = DBAcces.getInstance(getApplicationContext());

        pswdDataList = dbAcces.getPswd();
        //list = Read.getFiles();
        //adapter = new adapter_item(this,pswdDataList);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;
                Pswd pwd = pswdDataList.get(posicion);
                Intent editar = new Intent(ListPwd.this, Editar.class);
                editar.putExtra("id",pwd.getId()+"");
                editar.putExtra("nombre",pwd.getTitulo());
                editar.putExtra("usuario",pwd.getUsuario());
                editar.putExtra("password",pwd.getPassword());
                startActivity(editar);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        //adapter.UpdateList(Read.getFiles());
    }


    @Override
    public void Buscar(String nameFile) {
        //pswdExtends pswd = Busqueda.Buscar(getApplicationContext(),nameFile);
        //if (pswd != null){
            //Snackbar.make(findViewById(android.R.id.content),"Encontrado",Snackbar.LENGTH_SHORT).show();
            //Intent actividadBusqueda = new Intent(ListPwd.this, ResBusqueda.class);
            //actividadBusqueda.putExtra("objeto",pswd);
            //startActivity(actividadBusqueda);
        //}else{
          //  Snackbar.make(findViewById(android.R.id.content),"Nombre de cuenta no encontrado",Snackbar.LENGTH_SHORT).show();
        //}
    }
}
