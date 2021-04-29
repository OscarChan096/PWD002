package com.softchan.pwd.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<Pswd> pswdList = new ArrayList<>();
    private adapter_item adapter;
    private EditText busqueda;
    private DBAcces dbAcces;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        //setHasOptionsMenu(true);
        View layout = inflater.inflate(R.layout.activity_main, group, false);

        busqueda = layout.findViewById(R.id.edt_busqueda);
        busqueda.addTextChangedListener(this);

        Button btnAdd = layout.findViewById(R.id.btn_add);

        recyclerView = layout.findViewById(R.id.rview);
        dbAcces = DBAcces.getInstance(getContext());
        pswdList = dbAcces.getPswd();
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new adapter_item(getActivity(), pswdList);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getActivity(), Agregar.class);
                startActivity(add);
            }
        });

        return layout;//inflater.inflate(R.layout.activity_main, group, false);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Pswd pswd = adapter.getPSWD(item.getGroupId());
                Intent update = new Intent(getContext(), Editar.class);
                //Log.d("pwd edi+",pswd.getId()+"");
                update.putExtra("id",pswd.getId()+"");
                update.putExtra("titulo",pswd.getTitulo());
                update.putExtra("usuario",pswd.getUsuario());
                update.putExtra("password",pswd.getPassword());
                startActivity(update);
                break;
            case 1:
                adapter.delete(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
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
