package com.softchan.pwd;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softchan.pwd.Actividades.MainPWD;
import com.softchan.pwd.Actividades.WelcomeToApp;
import com.softchan.pwd.Datos.Read;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executor;


public class ScrollingActivity extends AppCompatActivity implements TextWatcher {

    private EditText password;
    private TextView mensajeBienvenida;
    private ArrayList<String> userInfList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        firstSettings();

        userInfList = Read.getUserInf();
        //Log.d("list",userInfList.size()+"");

        if (userInfList.contains("none")){
            Intent main = new Intent(this, MainPWD.class);
            startActivity(main);
            finish();
        }

        mensajeBienvenida = findViewById(R.id.tvbienvenida);
        mensajeBienvenida.setText("HOLA, "+userInfList.get(0));
        new Handler().postDelayed(new Runnable(){
            public void run(){
                mensajeBienvenida.setText(dtn());
            };
        }, 1500);

        password = findViewById(R.id.inicio_password);
        password.addTextChangedListener(this);

    }

    private void firstSettings() {
        if (!Read.isUserInf()) {
            Intent welcome = new Intent(getApplicationContext(), WelcomeToApp.class);
            startActivity(welcome);
        }
    }

    public void VerificarDatos(String password){
        if (userInfList.contains(password)){
            Intent main = new Intent(this, MainPWD.class);
            startActivity(main);
            finish();
        }else{
            mensajeBienvenida.setText("Contraseña incorrecta");
            mensajeBienvenida.setTextColor(getResources().getColor(R.color.error));
            new Handler().postDelayed(new Runnable(){
                public void run(){
                    mensajeBienvenida.setTextColor(getResources().getColor(R.color.textBtn));
                    mensajeBienvenida.setText(dtn());
                };
            }, 1000);
        }
    }

    public String dtn(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int am_pm = calendar.get(Calendar.AM_PM); // pm = 1 --- am = 0

        String dtn = "";

        //Log.d("dtn","Hora: "+hour+" am_pm "+am_pm);

        if((hour == 0 && am_pm == 0) || (hour >= 1 && am_pm == 0))
            dtn = "BUENOS DIAS";
        else if ((hour == 12 && am_pm == 1) || ((hour >= 1 && hour <= 6) && am_pm == 1))
            dtn = "BUENAS TARDES";
        else if(hour >= 7 && am_pm == 1)
            dtn = "BUENAS NOCHES";

        return dtn;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() == 4)
            VerificarDatos(password.getText().toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
