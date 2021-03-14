package com.softchan.pwd.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.Internet.Upload;
import com.softchan.pwd.R;

public class Configuraciones extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        return inflater.inflate(R.layout.configuraciones, group, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);

        final EditText passwordnew = getActivity().findViewById(R.id.config_paswd_edit);
        Button btn = getActivity().findViewById(R.id.btn_save_paswd_edit);
        Button btnUpload = getActivity().findViewById(R.id.btn_upload);
        Button btnSyncPin = getActivity().findViewById(R.id.btn_sync_pin);
        Button btnDownload = getActivity().findViewById(R.id.btn_download);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Write.SaveInf(passwordnew.getText().toString(),view);
            }
        });

        /*btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread sincronizacion = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Upload up = new Upload(getContext());
                        up.readAndUpload();
                    }
                });
                sincronizacion.start();
            }
        });

        btnSyncPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload up = new Upload(getContext());
                up.syncPin();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Sin funcionamiento",Toast.LENGTH_SHORT).show();
            }
        });*/

    }

}
