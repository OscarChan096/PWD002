package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.softchan.pwd.Adapters.adapter_item;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.List;

public class MainPWD extends AppCompatActivity implements TextWatcher {

    private adapter_item adapter;
    private TextInputEditText busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnAdd = findViewById(R.id.add);
        ImageView btnCard = findViewById(R.id.card);
        ImageView btnSettings = findViewById(R.id.settings);
        busqueda = findViewById(R.id.busqueda);
        busqueda.addTextChangedListener(this);

        RecyclerView recyclerView = findViewById(R.id.rview);
        DBAcces dbAcces = DBAcces.getInstance(getApplicationContext());
        List<Pswd> pswdList = dbAcces.getPswd();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new adapter_item(getApplicationContext(), pswdList);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(view -> {
            Intent add = new Intent(getApplicationContext(), Agregar.class);
            startActivity(add);
        });

        btnCard.setOnClickListener(view -> {
            Intent card = new Intent(getApplicationContext(), Cards.class);
            startActivity(card);
        });

        btnSettings.setOnClickListener(view -> {
            Intent settings = new Intent(getApplicationContext(), Settings.class);
            startActivity(settings);
        });

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Pswd pswd = adapter.getPSWD(item.getGroupId());
                Intent update = new Intent(getApplicationContext(), Editar.class);
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
