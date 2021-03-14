package com.softchan.pwd.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.Pswd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 10/02/20.
 */

public class adapter_item extends BaseAdapter {

    private Activity activity;
    private List<Pswd> items;
    private List<Pswd> itemsCopia = new ArrayList<>();
    //private List<pswdExtends> items;
    //private List<pswdExtends> itemsCopia = new ArrayList<>();

    public adapter_item (Activity activity, List<Pswd> items) {
        this.activity = activity;
        this.items = items;
        this.itemsCopia.addAll(items);
    }

    /* Filtra los datos del adaptador */
    public void filtrar(String texto) {

        // Elimina todos los datos del ArrayList que se cargan en los
        // elementos del adaptador
        items.clear();

        // Si no hay texto: agrega de nuevo los datos del ArrayList copiado
        // al ArrayList que se carga en los elementos del adaptador
        if (texto.length() == 0) {
            items.addAll(itemsCopia);
        } else {

            // Recorre todos los elementos que contiene el ArrayList copiado
            // y dependiendo de si estos contienen el texto ingresado por el
            // usuario los agrega de nuevo al ArrayList que se carga en los
            // elementos del adaptador.
            for (Pswd item : itemsCopia) {

                if (item.getTitulo().contains(texto)) {
                    items.add(item);
                }
            }
        }

        // Actualiza el adaptador para aplicar los cambios
        this.notifyDataSetChanged();
    }

    public void UpdateList(List<Pswd> newlist) {
        items = newlist;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(List<Pswd> v) {
        for (int i = 0; i < v.size(); i++) {
            items.add(v.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.adapter_item, null);
        }

        Pswd dir = items.get(position);

        TextView nombre = v.findViewById(R.id.nombre_adapter);
        nombre.setText(dir.getTitulo());

        TextView usuario = v.findViewById(R.id.usuario_adapter);
        usuario.setText(dir.getUsuario());

        TextView password = v.findViewById(R.id.password_adapter);
        password.setText(dir.getPassword());

        return v;
    }

}