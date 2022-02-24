package com.softchan.pwd.Actividades;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.softchan.pwd.Datos.GeneratePassword;
import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;


public class Agregar extends AppCompatActivity {

    private DBAcces dbAcces;
    private Pswd pswd;
    private EditText nombre;
    private EditText usuario;
    private EditText password;
    private EditText limpPwds;
    private TextView up;
    private TextView down;
    private CheckBox may;
    private CheckBox min;
    private CheckBox num;
    private CheckBox sim;
    private int limiteContrasenaContador;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.app_bar_nuevo);
        Toolbar toolbar = findViewById(R.id.toolbar_nuevo);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        nombre = findViewById(R.id.nombre);
        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        Button btnGenerarPass = findViewById(R.id.btnGenerarPassword);
        limpPwds = findViewById(R.id.lim_pwds);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        may = findViewById(R.id.chk_mayus);
        min = findViewById(R.id.chk_min);
        num = findViewById(R.id.chk_num);
        sim = findViewById(R.id.chk_sim);

        dbAcces = DBAcces.getInstance(getApplicationContext());

        limiteContrasenaContador = Integer.parseInt(limpPwds.getText().toString());

        btnGenerarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limiteContrasenaContador = Integer.parseInt(limpPwds.getText().toString());
                GeneratePassword gp = new GeneratePassword(may.isChecked(),min.isChecked(),num.isChecked(),sim.isChecked(),limiteContrasenaContador);
                password.setText(gp.generatePwd8());
            }
        });

        // subir el numero de caracteres de la contraseña
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (limiteContrasenaContador >= 8) {
                    limiteContrasenaContador += 1;
                    limpPwds.setText(limiteContrasenaContador+"");
                }
            }
        });

        // bajar el numero de caracteres a la contraseña
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (limiteContrasenaContador > 8){
                    limiteContrasenaContador -= 1;
                    limpPwds.setText(limiteContrasenaContador+"");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.action_save:
                String p = password.getText().toString();
                if (!p.equals("Seleccione una/varias opciones para contraseña")){
                    String n = nombre.getText().toString();
                    String u = usuario.getText().toString();
                    pswd = new Pswd(n.toUpperCase(), u, p);
                    dbAcces.add(pswd);
                    nombre.setText("");
                    password.setText("");
                    usuario.setText("");
                    Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"AGREGUE UNA CONTRASEÑA VAILDA",Snackbar.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
