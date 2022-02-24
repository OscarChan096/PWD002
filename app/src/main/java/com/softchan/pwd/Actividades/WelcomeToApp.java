package com.softchan.pwd.Actividades;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.R;

public class WelcomeToApp extends AppCompatActivity {

    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0; // codigo para el permiso de escritura

    @Override
    public void onCreate(Bundle saved){
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(saved);
        setContentView(R.layout.welcome);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        permissionStorageExternal();

        final EditText nombreUsuario = findViewById(R.id.ed_welcome_usuario);
        final EditText passordUsuario = findViewById(R.id.ed_welcome_password);
        Button guardarUsuario = findViewById(R.id.btn_welcome_guardar_usuario);

        guardarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sNombreUsuario = nombreUsuario.getText().toString();
                //Log.d("usuario",sNombreUsuario);
                if (sNombreUsuario.length() > 0) {
                    sNombreUsuario.toUpperCase();
                }else if (sNombreUsuario.length() == 0){
                    sNombreUsuario = "";
                }
                String sPasswordUsuario = passordUsuario.getText().toString();
                //Log.d("usuario pwd",sPasswordUsuario);
                if (sPasswordUsuario.length() == 0)
                    sPasswordUsuario = "none";
                if(Write.saveUserInf(sNombreUsuario,sPasswordUsuario,0)){
                    Snackbar.make(findViewById(android.R.id.content),"GUARDADO",Snackbar.LENGTH_LONG).show();
                    finish();
                }
            }
        });

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
