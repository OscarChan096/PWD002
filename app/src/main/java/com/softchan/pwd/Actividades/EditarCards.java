package com.softchan.pwd.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.softchan.pwd.Datos.GeneratePassword;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.Card;
import com.softchan.pwd.dbroom.DBAcces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditarCards extends AppCompatActivity {

    private TextInputEditText nombre_banco;
    private TextInputEditText num_cuenta;
    private TextInputEditText fecha;
    private TextInputEditText cvv;
    private TextInputEditText nip;
    private TextInputEditText user_app;
    private TextInputEditText password_app;
    private SwitchCompat tarjeta_virtual;
    private SwitchCompat banca_movil;
    private boolean flagMovil;
    private int id;

    @Override
    public void onCreate(Bundle saved){
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(saved);
        setContentView(R.layout.app_bar_addcard);
        Toolbar toolbar = findViewById(R.id.toolbar_addcard);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("id"));
        String strNombreBanco = i.getStringExtra("banco");
        String strNumCuenta = i.getStringExtra("numcuenta");
        String strFecha = i.getStringExtra("fecha");
        int intCvv = Integer.parseInt(i.getStringExtra("cvv"));
        int intNip = Integer.parseInt(i.getStringExtra("nip"));
        String strUserApp = i.getStringExtra("user");
        String strPasswordApp = i.getStringExtra("password");
        int intTarjetaVirtual = Integer.parseInt(i.getStringExtra("tarjeta"));

        nombre_banco = findViewById(R.id.nombre_banco);
        num_cuenta = findViewById(R.id.num_cuenta);
        fecha = findViewById(R.id.fecha);
        cvv = findViewById(R.id.cvv);
        nip = findViewById(R.id.nip);
        user_app = findViewById(R.id.user_app);
        password_app = findViewById(R.id.password_app);
        tarjeta_virtual = findViewById(R.id.tarjeta_virtual);
        banca_movil = findViewById(R.id.banca_movil);
        banca_movil.setChecked(false);

        nombre_banco.setText(strNombreBanco);
        num_cuenta.setText(strNumCuenta);
        fecha.setText(strFecha);
        cvv.setText(intCvv+"");
        nip.setText(intNip+"");
        if (intTarjetaVirtual == 1)
            tarjeta_virtual.setChecked(true);
        if(!strUserApp.equals("S/E")){
            banca_movil.setChecked(true);
            user_app.setText(strUserApp);
            password_app.setText(strPasswordApp);
            user_app.setEnabled(true);
            password_app.setEnabled(true);
            flagMovil = true;
        }

        banca_movil.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                user_app.setEnabled(true);
                password_app.setEnabled(true);
                flagMovil = true;
            }else{
                flagMovil = false;
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
        switch (idI){
            case R.id.action_save:
                DBAcces dbAcces = DBAcces.getInstance(getApplicationContext());
                String strNombreBanco = String.valueOf(nombre_banco.getText());
                String strNumCuenta = String.valueOf(num_cuenta.getText());
                String strFecha = String.valueOf(fecha.getText());
                String strCvv = String.valueOf(cvv.getText());
                String strNip = String.valueOf(nip.getText());
                String strUserApp = String.valueOf(user_app.getText());
                String strPasswordApp = String.valueOf(password_app.getText());
                strPasswordApp.replace(" ","");

                Pattern nombreBancoRgx = Pattern.compile("[A-Z0-9]*.*[A-Z0-9]");
                Pattern numCuentaRgx = Pattern.compile("[0-9]*");
                Pattern fechaRgx = Pattern.compile("[0-9]{1,2}/[0-9]{2,4}");
                Pattern cvvNipRgx = Pattern.compile("[0-9]*");
                Pattern userAppRgx = Pattern.compile("[A-Z0-9]*.*[A-Z0-9]*");

                Matcher nombreBancoMatch = nombreBancoRgx.matcher(strNombreBanco.toUpperCase());
                Matcher numCuentaMatch = numCuentaRgx.matcher(strNumCuenta);
                Matcher fechaMatch = fechaRgx.matcher(strFecha);
                Matcher cvvMatch = cvvNipRgx.matcher(strCvv);
                Matcher nipMatch = cvvNipRgx.matcher(strNip);
                Matcher userAppMatch = userAppRgx.matcher(strUserApp.toUpperCase());

                boolean bolNombreBanco = nombreBancoMatch.matches();
                boolean bolNumCuenta = numCuentaMatch.matches();
                boolean bolFecha = fechaMatch.matches();
                boolean bolCvv = cvvMatch.matches();
                boolean bolNip = nipMatch.matches();
                boolean bolUserApp = userAppMatch.matches();
                boolean bolPasswordApp = strPasswordApp.length() > 0;

                int intTarjetaDigital = tarjeta_virtual.isChecked() ? 1:0;

                // si el switch de la banca movil esta activo pedira todos los datos de lo contrario solo pedira banco,cuenta,fecha,cvv
                if (flagMovil) {
                    if (bolNombreBanco && bolNumCuenta && bolFecha && bolCvv && bolNip && bolUserApp && bolPasswordApp){
                        dbAcces.updateCard(id,strNombreBanco,strNumCuenta,Integer.parseInt(strNip),
                                strFecha,Integer.parseInt(strCvv),strUserApp,strPasswordApp,intTarjetaDigital);
                        nombre_banco.setText("");
                        num_cuenta.setText("");
                        fecha.setText("");
                        cvv.setText("");
                        nip.setText("");
                        user_app.setText("");
                        password_app.setText("");
                        tarjeta_virtual.setChecked(false);
                        banca_movil.setChecked(false);
                        user_app.setEnabled(false);
                        password_app.setEnabled(false);
                    }
                }else{ // no se actualiza el usuario
                    if (bolNombreBanco && bolNumCuenta && bolFecha && bolCvv && bolNip) {
                        dbAcces.updateCard(id,strNombreBanco,strNumCuenta,Integer.parseInt(strNip),
                                strFecha,Integer.parseInt(strCvv),"S/E","S/E",intTarjetaDigital);
                        nombre_banco.setText("");
                        num_cuenta.setText("");
                        fecha.setText("");
                        cvv.setText("");
                        nip.setText("");
                        tarjeta_virtual.setChecked(false);
                    }
                }

                Snackbar.make(findViewById(android.R.id.content), "Actualizado", Snackbar.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
