package com.softchan.pwd.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.softchan.pwd.Adapters.adapter_item;
import com.softchan.pwd.R;

import java.util.ArrayList;

public class Busqueda extends Fragment {// implements TextWatcher{

    private ListView lv;
    //private ArrayList<pswdExtends> list = new ArrayList<>();
    private adapter_item adapter;
    private EditText nombrecuenta;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        return inflater.inflate(R.layout.res_busqueda, group, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle){
        super.onActivityCreated(bundle);

        nombrecuenta = (EditText)getActivity().findViewById(R.id.resbusqueda);
        //nombrecuenta.addTextChangedListener(this);
        Button btnBuscar = (Button)getActivity().findViewById(R.id.btnresbusqueda);

        //list = Read.getFiles();
        //adapter = new adapter_item(getActivity(),list);
        //lv = getActivity().findViewById(R.id.listBusqueda);
        //lv.setAdapter(adapter);

        /*btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pswdExtends pswd = com.studio.chan.pwd.Datos.Busqueda.Buscar(getContext(),nombrecuenta.getText().toString()+".pwd");
                if (pswd != null){
                    //Log.d("busqueda","if btnBuscar");
                    //Snackbar.make(findViewById(android.R.id.content),"Encontrado",Snackbar.LENGTH_SHORT).show();
                    list.add(pswd);
                    adapter = new adapter_item(getActivity(),list);
                    lv = getActivity().findViewById(R.id.listBusqueda);
                    lv.setAdapter(adapter);
                    //Intent actividadBusqueda = new Intent(ListPwd.this, ResBusqueda.class);
                    //actividadBusqueda.putExtra("objeto",pswd);
                    //startActivity(actividadBusqueda);
                }else{
                    //Log.d("Busqueda","else btnBuscar");
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"Nombre de cuenta no encontrado",Snackbar.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    //@Override
    //public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    // }

    //@Override
    //public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    //    String filtro = nombrecuenta.getText().toString();
    //    Log.d("on",filtro);
    //    adapter.filtrar(filtro);
    //}

    //@Override
    //public void afterTextChanged(Editable editable) {
    // }
}
