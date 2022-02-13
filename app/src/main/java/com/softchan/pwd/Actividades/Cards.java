package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.softchan.pwd.Adapters.adapter_item_cards;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.Card;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.List;

public class Cards extends AppCompatActivity {

    private adapter_item_cards adapter;

    @Override
    public void onCreate(Bundle saved){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(saved);
        setContentView(R.layout.app_bar_cards);
        Toolbar toolbar = findViewById(R.id.toolbar_cards);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.rview_cards);
        DBAcces dbAcces = DBAcces.getInstance(getApplicationContext());
        List<Card> cardsList = dbAcces.getCards();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new adapter_item_cards(getApplicationContext(),cardsList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Card card = adapter.getCards(item.getGroupId());
                Intent update = new Intent(getApplicationContext(), EditarCards.class);
                //Log.d("pwd edi+",pswd.getId()+"");
                update.putExtra("id",card.getId()+"");
                update.putExtra("banco",card.getBanco());
                update.putExtra("numcuenta",card.getNumCuenta()+"");
                update.putExtra("fecha",card.getFecha());
                update.putExtra("cvv",card.getCvv()+"");
                update.putExtra("nip",card.getNip()+"");
                update.putExtra("user",card.getUsuarioApp()+"");
                update.putExtra("password",card.getPasswordApp()+"");
                update.putExtra("tarjeta",card.getTarjetaVirtual()+"");
                startActivity(update);
                break;
            case 1:
                adapter.delete(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_add:
                Intent add = new Intent(getApplicationContext(),AddCard.class);
                startActivity(add);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
