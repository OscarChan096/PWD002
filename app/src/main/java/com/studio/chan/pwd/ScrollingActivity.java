package com.studio.chan.pwd;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studio.chan.pwd.Actividades.ListPwd;
import com.studio.chan.pwd.Actividades.MainPWD;
import com.studio.chan.pwd.Datos.Read;

import java.io.File;
import static android.os.Environment.getExternalStorageDirectory;

public class ScrollingActivity extends AppCompatActivity {

    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0; // codigo para el permiso de escritura

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        permissionStorageExternal();

        //Log.d("Error","SplashScreen");

        final EditText password = findViewById(R.id.inicio_password);
        Button iniciar = findViewById(R.id.iniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificarDatos(password.getText().toString());
                finish();
            }
        });

    }

    public void VerificarDatos(String password){
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
        if (path.isDirectory()){
            if (Read.isPassword(password)){
                Intent main = new Intent(this, MainPWD.class);
                startActivity(main);
            }
        }else{
            path.mkdirs(); // crea la carpeta
            Intent main = new Intent(this, MainPWD.class);
            startActivity(main);
        }
    }

    //--------------- codigo para el dialogo de permiso de escritura
    void permissionStorageExternal(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Sin el permiso no se podra usar la aplicacion",
                    SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
        }
    }

    public static void solicitarPermiso(final String permiso, String justificacion,
                                        final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }})
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == SOLICITUD_PERMISO_WRITE_CALL_LOG) {
            if (grantResults.length== 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionStorageExternal();
            } else {
                Toast.makeText(this, "Sin el permiso, no puedo realizar la " +
                        "acción", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
