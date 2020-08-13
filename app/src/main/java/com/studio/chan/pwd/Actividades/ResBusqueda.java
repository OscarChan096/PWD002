package com.studio.chan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.studio.chan.pwd.Adapters.adapter_item;
import com.studio.chan.pwd.Objeto.pswdExtends;
import com.studio.chan.pwd.R;

import java.util.ArrayList;

/**
 * Created by oscar on 31/03/20.
 */

public class ResBusqueda extends AppCompatActivity {

    private ListView lv;
    private ArrayList<pswdExtends> list = new ArrayList<>();
    private adapter_item adapter;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.res_busqueda);

        Intent receptor = getIntent();
        pswdExtends pswd = (pswdExtends) receptor.getSerializableExtra("objeto");

        list.add(pswd);
        adapter = new adapter_item(this,list);
        lv = findViewById(R.id.listBusqueda);
        lv.setAdapter(adapter);

    }
}
