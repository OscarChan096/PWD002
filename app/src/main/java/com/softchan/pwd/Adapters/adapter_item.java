package com.softchan.pwd.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.softchan.pwd.Actividades.Editar;
import com.softchan.pwd.Actividades.MainPWD;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscar on 10/02/20.
 */

public class adapter_item extends RecyclerView.Adapter<adapter_item.ViewHolder> {

    private List<Pswd> pswdDataList;
    private List<Pswd> pswdDataListCopia = new ArrayList<>();
    private Activity context;

    public adapter_item(Activity context, List<Pswd> pswdDataList){
        this.context = context;
        this.pswdDataList = pswdDataList;
        this.pswdDataListCopia.addAll(pswdDataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new adapter_item.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pswd pswd = pswdDataList.get(position);
        holder.id.setText(pswd.getId()+"");
        holder.titulo.setText(pswd.getTitulo());
        holder.usuario.setText(pswd.getUsuario());
        holder.password.setText(pswd.getPassword());
    }

    @Override
    public int getItemCount() {
        return pswdDataList.size();
    }

    public void delete(int position){
        Pswd pswd = pswdDataList.get(position);
        DBAcces dbAcces = DBAcces.getInstance(context);
        dbAcces.delete(pswd);
        notifyDataSetChanged();
    }

    public Pswd getPSWD(int position){
        return pswdDataList.get(position);
    }

    /* Filtra los datos del adaptador */
    public void filtrar(String texto) {

        // Elimina todos los datos del ArrayList que se cargan en los
        // elementos del adaptador
        pswdDataList.clear();

        // Si no hay texto: agrega de nuevo los datos del ArrayList copiado
        // al ArrayList que se carga en los elementos del adaptador
        if (texto.length() == 0) {
            pswdDataList.addAll(pswdDataListCopia);
        } else {

            // Recorre todos los elementos que contiene el ArrayList copiado
            // y dependiendo de si estos contienen el texto ingresado por el
            // usuario los agrega de nuevo al ArrayList que se carga en los
            // elementos del adaptador.
            for (Pswd item : pswdDataListCopia) {

                if (item.getTitulo().contains(texto)) {
                    pswdDataList.add(item);
                }
            }
        }

        // Actualiza el adaptador para aplicar los cambios
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView id;
        TextView titulo;
        TextView usuario;
        TextView password;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_id);
            titulo = itemView.findViewById(R.id.nombre_adapter);
            usuario = itemView.findViewById(R.id.usuario_adapter);
            password = itemView.findViewById(R.id.password_adapter);
            cardView = itemView.findViewById(R.id.cardview);
            cardView.setOnCreateContextMenuListener(this);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(titulo.getText().toString());
            menu.add(this.getAdapterPosition(),0,0,"Editar");
            menu.add(this.getAdapterPosition(),1,1,"Eliminar");
        }
    }

    /*private Activity activity;
    private List<Pswd> items;
    private List<Pswd> itemsCopia = new ArrayList<>();

    public adapter_item (Activity activity, List<Pswd> items) {
        this.activity = activity;
        this.items = items;
        this.itemsCopia.addAll(items);
    }

    /* Filtra los datos del adaptador */
    /*public void filtrar(String texto) {

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
    }*/

    /*public void UpdateList(List<Pswd> newlist) {
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

    public class ViewHolder {
    }*/

}
