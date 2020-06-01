package com.studio.chan.pwd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.studio.chan.pwd.Actividades.ListPwd;
import com.studio.chan.pwd.Actividades.ResBusqueda;
import com.studio.chan.pwd.Adapters.adapter_item;
import com.studio.chan.pwd.Objeto.pswdExtends;
import com.studio.chan.pwd.R;

import java.util.ArrayList;

public class Busqueda extends Fragment {

    private ListView lv;
    private ArrayList<pswdExtends> list = new ArrayList<>();
    private adapter_item adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        return inflater.inflate(R.layout.res_busqueda, group, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle){
        super.onActivityCreated(bundle);

        final EditText nombrecuenta = (EditText)getActivity().findViewById(R.id.resbusqueda);
        Button btnBuscar = (Button)getActivity().findViewById(R.id.btnresbusqueda);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pswdExtends pswd = com.studio.chan.pwd.Datos.Busqueda.Buscar(getContext(),nombrecuenta.getText().toString()+".pwd");
                if (pswd != null){
                    //Log.d("busqueda","if btnBuscar");
                    //Snackbar.make(findViewById(android.R.id.content),"Encontrado",Snackbar.LENGTH_SHORT).show();
                    list.add(pswd);
                    adapter = new adapter_item(getActivity(),list);
                    lv = getActivity().findViewById(R.id.listBusqueda);
                    lv.setAdapter(adapter);
                    //Intent actividadBusqueda = new Intent(ListPwd.this, ResBusqueda.class);
                    //actividadBusqueda.putExtra("objeto",pswd);
                    //startActivity(actividadBusqueda);
                }else{
                    //Log.d("Busqueda","else btnBuscar");
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Nombre de cuenta no encontrado",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

}
