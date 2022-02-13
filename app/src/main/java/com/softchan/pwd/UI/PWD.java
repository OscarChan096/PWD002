package com.softchan.pwd.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.softchan.pwd.Actividades.Agregar;
import com.softchan.pwd.Actividades.Cards;
import com.softchan.pwd.Actividades.Editar;
import com.softchan.pwd.Actividades.Settings;
import com.softchan.pwd.Adapters.adapter_item;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.List;

public class PWD extends Fragment implements TextWatcher {

    private adapter_item adapter;
    private TextInputEditText busqueda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved){
        //setHasOptionsMenu(true);
        View layout = inflater.inflate(R.layout.activity_main, group, false);

        AppCompatButton btnAdd = layout.findViewById(R.id.add);
        ImageView btnCard = layout.findViewById(R.id.card);
        ImageView btnSettings = layout.findViewById(R.id.settings);
        busqueda = layout.findViewById(R.id.busqueda);
        busqueda.addTextChangedListener(this);

        RecyclerView recyclerView = layout.findViewById(R.id.rview);
        DBAcces dbAcces = DBAcces.getInstance(getContext());
        List<Pswd> pswdList = dbAcces.getPswd();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new adapter_item(getActivity(), pswdList);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(view -> {
            Intent add = new Intent(getActivity(), Agregar.class);
            startActivity(add);
        });

        btnCard.setOnClickListener(view -> {
            Intent card = new Intent(getActivity(), Cards.class);
            startActivity(card);
        });

        btnSettings.setOnClickListener(view -> {
            Intent settings = new Intent(getActivity(), Settings.class);
            startActivity(settings);
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
