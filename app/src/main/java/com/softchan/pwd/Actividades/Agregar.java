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
    private CheckBox may;
    private CheckBox min;
    private CheckBox num;
    private CheckBox sim;

    @Override
    public void onCreate(Bundle saved){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        may = findViewById(R.id.chk_mayus);
        min = findViewById(R.id.chk_min);
        num = findViewById(R.id.chk_num);
        sim = findViewById(R.id.chk_sim);

        dbAcces = DBAcces.getInstance(getApplicationContext());

        btnGenerarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneratePassword gp = new GeneratePassword(may.isChecked(),min.isChecked(),num.isChecked(),sim.isChecked());
                password.setText(gp.generatePwd8());
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
