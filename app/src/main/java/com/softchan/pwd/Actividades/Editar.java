package com.softchan.pwd.Actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.softchan.pwd.Datos.GeneratePassword;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;

public class Editar extends AppCompatActivity {

    private int id;
    private TextInputEditText nombre;
    private TextInputEditText usuario;
    private TextInputEditText password;
    private TextInputEditText limpPwds;
    private CheckBox may;
    private CheckBox min;
    private CheckBox num;
    private CheckBox sim;
    private int limiteContrasenaContador;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle saved){
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(saved);
        setContentView(R.layout.app_bar_nuevo);
        Toolbar toolbar = findViewById(R.id.toolbar_nuevo);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("id"));
        String nombreStr = i.getStringExtra("titulo");
        final String usuarioStr = i.getStringExtra("usuario");
        String passwordStr = i.getStringExtra("password");

        nombre = findViewById(R.id.nombre);
        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        Button btnGenerarPass = findViewById(R.id.btnGenerarPassword);
        may = findViewById(R.id.chk_mayus);
        min = findViewById(R.id.chk_min);
        num = findViewById(R.id.chk_num);
        sim = findViewById(R.id.chk_sim);
        TextView up = findViewById(R.id.up);
        TextView down = findViewById(R.id.down);
        limpPwds = findViewById(R.id.lim_pwds);

        nombre.setText(nombreStr);
        usuario.setText(usuarioStr);
        password.setText(passwordStr);

        limiteContrasenaContador = Integer.parseInt(limpPwds.getText().toString());

        btnGenerarPass.setOnClickListener(view -> {
            limiteContrasenaContador = Integer.parseInt(limpPwds.getText().toString());
            GeneratePassword gp = new GeneratePassword(may.isChecked(),min.isChecked(),num.isChecked(),sim.isChecked(),limiteContrasenaContador);
            password.setText(gp.generatePwd8());
        });

        // subir el numero de caracteres a la contraseña
        up.setOnClickListener(v -> {
            if (limiteContrasenaContador >= 8) {
                limiteContrasenaContador += 1;
                limpPwds.setText(limiteContrasenaContador+"");
            }
        });

        // bajar el numero de caracteres a la contraseña
        down.setOnClickListener(v -> {
            if (limiteContrasenaContador > 8){
                limiteContrasenaContador -= 1;
                limpPwds.setText(limiteContrasenaContador+"");
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
        int idI = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (idI == R.id.action_save) {
            DBAcces dbAcces = DBAcces.getInstance(getApplicationContext());
            String p = String.valueOf(password.getText());
            String n = String.valueOf(nombre.getText());
            String u = String.valueOf(usuario.getText());
            dbAcces.update(id, n.toUpperCase(), u, p);
            nombre.setText("");
            password.setText("");
            usuario.setText("");
            Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
