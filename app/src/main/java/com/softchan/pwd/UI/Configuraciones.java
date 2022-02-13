package com.softchan.pwd.UI;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.softchan.pwd.Datos.Write;
import com.softchan.pwd.SyncData.Upload;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;

public class Configuraciones extends Fragment {

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        return inflater.inflate(R.layout.configuraciones, group, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);

        final EditText passwordnew = getActivity().findViewById(R.id.config_paswd_edit);
        Button btn = getActivity().findViewById(R.id.btn_save_paswd_edit);
        Button backup = getActivity().findViewById(R.id.backup);
        Button btnSyncPin = getActivity().findViewById(R.id.syncpin);
        //Button btnDownload = getActivity().findViewById(R.id.btn_download);

        btn.setOnClickListener(view -> {
            String sPasswordUsuario = passwordnew.getText().toString();
            Write.saveUserInf("",sPasswordUsuario,1);
        });

        backup.setOnClickListener(view -> {
            DBAcces dbAcces = DBAcces.getInstance(getContext());
            Thread sincronizacion = new Thread(() -> {
                Upload up = new Upload(getContext(),dbAcces.getPswd());
                up.initBackup();
            });
            sincronizacion.start();
        });

        btnSyncPin.setOnClickListener(view -> {
            Upload up = new Upload(getContext());
            up.syncPin();
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Sin funcionamiento",Toast.LENGTH_SHORT).show();
            }
        });

    }*/

}
