package com.softchan.pwd.Actividades;

import static com.softchan.pwd.Datos.Paths.typePassword;
import static com.softchan.pwd.Datos.Paths.typeUser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.R;

public class Settings extends AppCompatActivity{

    private TextInputEditText userPwd;
    private TextInputEditText passwordPwd;

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.app_bar_settings);
        Toolbar toolbar = findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);

        Button btnInterfazVisual = findViewById(R.id.btn_ui);
        userPwd = findViewById(R.id.user_pwd);
        userPwd.addTextChangedListener(new Watcher(userPwd.getId()));
        passwordPwd = findViewById(R.id.password_pwd); // falta extraer datos y guardar cambios
        passwordPwd.addTextChangedListener(new Watcher(passwordPwd.getId()));
        Button btnUpdate = findViewById(R.id.btn_save_paswd_edit);
        //Button backup = findViewById(R.id.backup);
        //Button btnSyncPin = findViewById(R.id.syncpin);
        //Button btnDownload = getActivity().findViewById(R.id.btn_download);

        btnInterfazVisual.setOnClickListener(view -> SettingsActivity.start(getApplicationContext()));

        btnUpdate.setOnClickListener(view -> {
            boolean succes = false;
            Watcher watcher = new Watcher();
            if (watcher.isBolUser() && watcher.isBolPassword())
                succes = Write.saveUserInf(String.valueOf(userPwd.getText()),String.valueOf(passwordPwd.getText()),1);
            else if(watcher.isBolUser())
                succes = Write.saveUserInf(String.valueOf(userPwd.getText()),typeUser);
            else if(watcher.isBolPassword())
                succes = Write.saveUserInf(String.valueOf(passwordPwd.getText()),typePassword);

            if (succes)
                Snackbar.make(findViewById(android.R.id.content),"Datos actualizados",Snackbar.LENGTH_SHORT).show();
            else
                Snackbar.make(findViewById(android.R.id.content),"Error al actualizar los datos",Snackbar.LENGTH_SHORT).show();
        });

        /*backup.setOnClickListener(view -> {
            DBAcces dbAcces = DBAcces.getInstance(getApplicationContext());
            Thread sincronizacion = new Thread(() -> {
                Upload up = new Upload(getApplicationContext(),dbAcces.getPswd());
                up.initBackup();
            });
            sincronizacion.start();
        });*/

        /*btnSyncPin.setOnClickListener(view -> {
            Upload up = new Upload(getApplicationContext());
            up.syncPin();
        });*/

        /*btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Sin funcionamiento",Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    class Watcher implements TextWatcher{

        private int id;
        private boolean bolUser;
        private boolean bolPassword;

        public Watcher(){}

        public Watcher(int id){
            this.id = id;
        }

        public boolean isBolUser() {
            return bolUser;
        }

        public void setBolUser(boolean bolUser) {
            this.bolUser = bolUser;
        }

        public boolean isBolPassword() {
            return bolPassword;
        }

        public void setBolPassword(boolean bolPassword) {
            this.bolPassword = bolPassword;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch (id){
                case R.id.user_pwd:
                    Log.d("R.id.user_wd",id+"");
                    bolUser = count > 0;
                    break;
                case R.id.password_pwd:
                    Log.d("R.id.passowrd_pwd",id+"");
                    bolPassword = count > 0;
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
