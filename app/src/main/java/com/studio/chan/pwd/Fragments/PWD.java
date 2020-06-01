package com.studio.chan.pwd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.chan.pwd.Actividades.Agregar;
import com.studio.chan.pwd.Actividades.Configuraciones;
import com.studio.chan.pwd.Actividades.Editar;
import com.studio.chan.pwd.Actividades.ListPwd;
import com.studio.chan.pwd.Adapters.adapter_item;
import com.studio.chan.pwd.Datos.Read;
import com.studio.chan.pwd.Objeto.pswdExtends;
import com.studio.chan.pwd.R;

import java.util.ArrayList;

public class PWD extends Fragment {

    private ListView listView;
    private ArrayList<pswdExtends> list;
    private adapter_item adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        //setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_main, group, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = Read.getFiles();
        adapter = new adapter_item(getActivity(),list);
        listView = getActivity().findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;
                pswdExtends pwd = list.get(posicion);
                Intent editar = new Intent(getActivity(), Editar.class);
                editar.putExtra("nombre",pwd.getTitulo());
                editar.putExtra("usuario",pwd.getUsuario());
                editar.putExtra("password",pwd.getPassword());
                editar.putExtra("nombreobj",pwd.getNameObj());
                startActivity(editar);
            }
        });

        if (!Read.isExistsPswd()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Por seguridad debe agregarle una contraseña a la app", Snackbar.LENGTH_LONG).setAction("Agregar", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(),Configuraciones.class));
                }
            }).show();
        }

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getActivity(), Agregar.class);
                startActivity(add);
            }
        });

    }

}
