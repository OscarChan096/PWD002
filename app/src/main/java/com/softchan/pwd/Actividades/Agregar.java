package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softchan.pwd.Datos.GeneratePassword;
import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

/**
 * Created by oscar on 8/02/20.
 */

public class Agregar extends AppCompatActivity {

    private DBAcces dbAcces;
    private Pswd pswd;

    @Override
    public void onCreate(Bundle saved){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(saved);
        setContentView(R.layout.agregareditar);

        final EditText nombre = findViewById(R.id.nombre);
        final EditText usuario = findViewById(R.id.usuario);
        final EditText password = findViewById(R.id.password);
        Button btnGenerarPass = findViewById(R.id.btnGenerarPassword);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        dbAcces = DBAcces.getInstance(getApplicationContext());

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
                pswd = new Pswd(n.toUpperCase(), p, u);
                dbAcces.add(pswd);
                nombre.setText("");
                password.setText("");
                usuario.setText("");
                Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
