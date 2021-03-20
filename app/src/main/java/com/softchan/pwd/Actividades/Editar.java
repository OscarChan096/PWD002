package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
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


public class Editar extends AppCompatActivity {

    private DBAcces dbAcces;
    private int id;
    private int posicion;
    private List<Pswd> pswdList = new ArrayList<>();
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

        dbAcces = DBAcces.getInstance(getApplicationContext());
        pswdList = dbAcces.getPswd();

        Intent i = getIntent();
        posicion = Integer.parseInt(i.getStringExtra("posicion"));
        id = Integer.parseInt(i.getStringExtra("id"));
        String nombreStr = i.getStringExtra("nombre");
        final String usuarioStr = i.getStringExtra("usuario");
        String passwordStr = i.getStringExtra("password");
        //Log.d("Editar",nombreObj+" 0x096");

        nombre = findViewById(R.id.nombre);
        usuario = findViewById(R.id.usuario);
        password = findViewById(R.id.password);
        Button btnGenerarPass = findViewById(R.id.btnGenerarPassword);
        may = findViewById(R.id.chk_mayus);
        min = findViewById(R.id.chk_min);
        num = findViewById(R.id.chk_num);
        sim = findViewById(R.id.chk_sim);

        nombre.setText(nombreStr);
        usuario.setText(usuarioStr);
        password.setText(passwordStr);

        btnGenerarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneratePassword gp = new GeneratePassword(may.isChecked(),min.isChecked(),num.isChecked(),sim.isChecked());
                password.setText(gp.generatePwd8());
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
        switch (id){
            case R.id.action_save:
                String p = password.getText().toString();
                if (!p.equals("Seleccione una/varias opciones para contraseña")){
                    String n = nombre.getText().toString();
                    String u = usuario.getText().toString();
                    dbAcces.update(id, n.toUpperCase(), p, u);
                    nombre.setText("");
                    password.setText("");
                    usuario.setText("");
                    Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(findViewById(android.R.id.content),"AGREGUE UNA CONTRASEÑA VAILDA",Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.eliminar:
                dbAcces.delete(pswdList.get(posicion));
                Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_SHORT).show();
                Intent home_intent = new Intent(getApplicationContext(),
                        MainPWD.class);//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home_intent);
                break;
        }
        /*if (id == R.id.eliminar) {
            dbAcces.delete(pswdList.get(posicion));
            Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_SHORT).show();
            Intent home_intent = new Intent(getApplicationContext(),
                    ScrollingActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home_intent);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

}
