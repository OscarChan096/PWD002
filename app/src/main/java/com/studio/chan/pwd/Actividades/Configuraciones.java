package com.studio.chan.pwd.Actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.studio.chan.pwd.Datos.Write;
import com.studio.chan.pwd.R;

/**
 * Created by oscar on 10/02/20.
 */

public class Configuraciones extends AppCompatActivity {

    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.configuraciones);

        final EditText passwordnew = findViewById(R.id.config_paswd_edit);
        Button btn = findViewById(R.id.btn_save_paswd_edit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Write.SaveInf(passwordnew.getText().toString(),view);
            }
        });
    }

}
