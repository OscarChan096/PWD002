package com.softchan.pwd.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.softchan.pwd.Actividades.Agregar;
import com.softchan.pwd.Actividades.Editar;
import com.softchan.pwd.Adapters.adapter_item;
import com.softchan.pwd.Datos.Read;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.ArrayList;
import java.util.List;

public class PWD extends Fragment implements TextWatcher {

    private ListView listView;
    private List<Pswd> pswdList = new ArrayList<>();
    //private ArrayList<pswdExtends> list;
    private adapter_item adapter;
    private EditText busqueda;
    private DBAcces dbAcces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        //setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_main, group, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dbAcces = DBAcces.getInstance(getContext());

        busqueda = getActivity().findViewById(R.id.edt_busqueda);
        busqueda.addTextChangedListener(this);

        Button btnAdd = getActivity().findViewById(R.id.btn_add);

        pswdList = dbAcces.getPswd();
        //list = Read.getFiles();
        adapter = new adapter_item(getActivity(),pswdList);
        listView = getActivity().findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion = i;
                Pswd pwd = pswdList.get(posicion);
                Intent editar = new Intent(getActivity(), Editar.class);
                editar.putExtra("posicion",posicion+"");
                editar.putExtra("id",pwd.getId()+"");
                editar.putExtra("nombre",pwd.getTitulo());
                editar.putExtra("usuario",pwd.getUsuario());
                editar.putExtra("password",pwd.getPassword());
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getActivity(), Agregar.class);
                startActivity(add);
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String filtro = busqueda.getText().toString();
        //Log.d("on",filtro);
        adapter.filtrar(filtro.toUpperCase());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
