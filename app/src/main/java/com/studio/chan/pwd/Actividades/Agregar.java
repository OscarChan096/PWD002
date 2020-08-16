package com.studio.chan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.studio.chan.pwd.Datos.GeneratePassword;
import com.studio.chan.pwd.Datos.Write;
import com.studio.chan.pwd.R;

/**
 * Created by oscar on 8/02/20.
 */

public class Agregar extends AppCompatActivity {

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
                Intent intent = new Intent(getApplicationContext(), MainPWD.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
