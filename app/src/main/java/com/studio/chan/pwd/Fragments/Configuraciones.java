package com.studio.chan.pwd.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.studio.chan.pwd.Datos.Write;
import com.studio.chan.pwd.R;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Write.SaveInf(passwordnew.getText().toString(),view);
            }
        });
    }

}
