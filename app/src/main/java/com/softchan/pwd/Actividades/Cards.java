package com.softchan.pwd.Actividades;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softchan.pwd.Adapters.adapter_item_cards;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.Card;
import com.softchan.pwd.dbroom.DBAcces;

import java.util.List;

public class Cards extends AppCompatActivity {

    private adapter_item_cards adapter;
    private ClipData clip;
    private ClipboardManager clipboard;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.app_bar_cards);
        Toolbar toolbar = findViewById(R.id.toolbar_cards);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
        clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

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
        Card card = adapter.getCards(item.getGroupId());
        switch (item.getItemId()){
            case 0:
                Intent update = new Intent(getApplicationContext(), EditarCards.class);
                //Log.d("pwd edi+",pswd.getId()+"");
                update.putExtra("id",card.getId()+"");
                update.putExtra("banco",card.getBanco());
                update.putExtra("numcuenta",card.getNumeroDeCuenta()+"");
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
            case 2:
                clip = ClipData.newPlainText("simple text",card.getNumeroDeCuenta().replaceAll(" ",""));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"# numero de cuenta copiado",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                if (!card.getUsuarioApp().equals("S/E")){
                clip = ClipData.newPlainText("simple text",card.getUsuarioApp());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(),"# Usuario App copiado al portapaples",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"# N/A - no copiado",Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (!card.getPasswordApp().equals("S/E")) {
                    clip = ClipData.newPlainText("simple text", card.getPasswordApp());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "# Contraseña App copiado al portapapeles", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"# N/A - no copiado",Toast.LENGTH_SHORT).show();
                }
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
        if (menuItem.getItemId() == R.id.action_add) {
            Intent add = new Intent(getApplicationContext(), AddCard.class);
            startActivity(add);
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
