package com.studio.chan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.studio.chan.pwd.Datos.GeneratePassword;
import com.studio.chan.pwd.Datos.Write;
import com.studio.chan.pwd.R;
import com.studio.chan.pwd.ScrollingActivity;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by oscar on 10/02/20.
 */

public class Editar extends AppCompatActivity {

    private String nombreObj;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.agregareditar);

        Intent i = getIntent();
        String nombreStr = i.getStringExtra("nombre");
        final String usuarioStr = i.getStringExtra("usuario");
        String passwordStr = i.getStringExtra("password");
        nombreObj = i.getStringExtra("nombreobj");
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
                Write.SavePwd(nombre.getText().toString(), password.getText().toString(), usuario.getText().toString(), view);
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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
            File fn = new File(path.getAbsolutePath(), nombreObj);
            fn.delete();
            Intent home_intent = new Intent(getApplicationContext(),
                    ScrollingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
