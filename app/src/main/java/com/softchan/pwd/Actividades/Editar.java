package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softchan.pwd.Datos.GeneratePassword;
import com.softchan.pwd.Datos.Paths;
import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.R;
import com.softchan.pwd.ScrollingActivity;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 10/02/20.
 */

public class Editar extends AppCompatActivity {

    private DBAcces dbAcces;
    private int id;
    private int posicion;
    private List<Pswd> pswdList = new ArrayList<>();

    @Override
    public void onCreate(Bundle saved){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(saved);
        setContentView(R.layout.agregareditar);

        dbAcces = DBAcces.getInstance(getApplicationContext());
        pswdList = dbAcces.getPswd();

        Intent i = getIntent();
        posicion = Integer.parseInt(i.getStringExtra("posicion"));
        id = Integer.parseInt(i.getStringExtra("id"));
        String nombreStr = i.getStringExtra("nombre");
        final String usuarioStr = i.getStringExtra("usuario");
        String passwordStr = i.getStringExtra("password");
        //Log.d("Editar",nombreObj+" 0x096");

        final EditText nombre = findViewById(R.id.nombre);
        final EditText usuario = findViewById(R.id.usuario);
        final EditText password = findViewById(R.id.password);
        Button btnGenerarPass = findViewById(R.id.btnGenerarPassword);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        nombre.setText(nombreStr);
        usuario.setText(usuarioStr);
        password.setText(passwordStr);

        btnGenerarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setText(GeneratePassword.getPassword());
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = nombre.getText().toString();
                String p = password.getText().toString();
                String u = usuario.getText().toString();
                dbAcces.update(id, n.toUpperCase(), p, u);
                Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    // --------------------------- botones del toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.eliminar) {
            dbAcces.delete(pswdList.get(posicion));
            Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_SHORT).show();
            Intent home_intent = new Intent(getApplicationContext(),
                    ScrollingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
