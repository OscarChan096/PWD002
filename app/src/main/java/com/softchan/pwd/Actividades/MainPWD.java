package com.softchan.pwd.Actividades;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.softchan.pwd.Adapters.adapter_item;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.List;

public class MainPWD extends AppCompatActivity implements TextWatcher {

    private adapter_item adapter;
    private TextInputEditText busqueda;
    private ClipboardManager clipboard;
    private ClipData clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

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

    @SuppressLint("NewApi")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Pswd pswd = adapter.getPSWD(item.getGroupId());
        switch (item.getItemId()){
            case 0:
                Intent update = new Intent(getApplicationContext(), Editar.class);
                update.putExtra("id",pswd.getId()+"");
                update.putExtra("titulo",pswd.getTitulo());
                update.putExtra("usuario",pswd.getUsuario());
                update.putExtra("password",pswd.getPassword());
                startActivity(update);
                break;
            case 1:
                adapter.delete(item.getGroupId());
                break;
            case 2:
                clip = ClipData.newPlainText("simple text",pswd.getUsuario());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"# Usuario copiado al portapales",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                clip = ClipData.newPlainText("simple text",pswd.getPassword());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"# Password copiado al portapales",Toast.LENGTH_SHORT).show();
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
