package com.softchan.pwd.Adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.softchan.pwd.R;
import com.softchan.pwd.dbroom.Card;
import com.softchan.pwd.dbroom.DBAcces;
import com.softchan.pwd.dbroom.Pswd;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by oscar on 10/02/20.
 */

public class adapter_item_cards extends RecyclerView.Adapter<adapter_item_cards.ViewHolder> {

    private List<Card> cardsDataList;
    private List<Card> cardsDataListCopia = new ArrayList<>();
    private Context context;

    public adapter_item_cards(Context context, List<Card> cardsDataList){
        this.context = context;
        this.cardsDataList = cardsDataList;
        this.cardsDataListCopia.addAll(cardsDataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_cards, parent, false);
        return new adapter_item_cards.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cardsDataList.get(position);
        if (card.getTarjetaVirtual() == 0) {
            final String categoria = "f\ni\ns\ni\nc\na";
            holder.bck_categoria.setBackgroundResource(R.color.categoriaFisica);
            holder.tipo_tarjeta.setText(categoria);
            holder.nombre_banco.setText(card.getBanco().toUpperCase(Locale.ROOT));
            holder.num_cuenta.setText(card.getNumeroDeCuenta());
            holder.fecha.setText(card.getFecha());
            holder.cvv.setText(card.getCvv() + "");
            holder.nip.setText(card.getNip() + "");
            if (card.getUsuarioApp() == null)
                holder.info.setText("");
            holder.usuario_app.setText(card.getUsuarioApp());
            holder.password_app.setText(card.getPasswordApp());
        }else{
            final String categoria = "d\ni\ng\ni\nt\na\nl";
            holder.bck_categoria.setBackgroundResource(R.color.categoriaDigital);
            holder.tipo_tarjeta.setText(categoria);
            holder.nombre_banco.setText(card.getBanco().toUpperCase());
            holder.num_cuenta.setText(card.getNumeroDeCuenta());
            holder.fecha.setText(card.getFecha());
            holder.cvv.setText(card.getCvv() + "");
            holder.nip.setText(card.getNip() + "");
            if (card.getUsuarioApp() == null)
                holder.info.setText("");
            holder.usuario_app.setText(card.getUsuarioApp());
            holder.password_app.setText(card.getPasswordApp());
        }
    }

    @Override
    public int getItemCount() {
        return cardsDataList.size();
    }

    public void delete(int position){
        Card card = cardsDataList.get(position);
        DBAcces dbAcces = DBAcces.getInstance(context);
        dbAcces.deleteCard(card);
        notifyDataSetChanged();
    }

    public Card getCards(int position){
        return cardsDataList.get(position);
    }

    /* Filtra los datos del adaptador */
    /*public void filtrar(String texto) {

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
            for (Card item : cardsDataListCopia) {

                if (item.getTitulo().contains(texto)) {
                    cardsDataList.add(item);
                }
            }
        }

        // Actualiza el adaptador para aplicar los cambios
        this.notifyDataSetChanged();
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView nombre_banco;
        TextView num_cuenta;
        TextView fecha;
        TextView cvv;
        TextView nip;
        TextView usuario_app;
        TextView password_app;
        TextView tipo_tarjeta;
        TextView info;
        LinearLayout bck_categoria;
        CardView cardView;
        ClipboardManager clipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bck_categoria = itemView.findViewById(R.id.bck_categoria);
            tipo_tarjeta = itemView.findViewById(R.id.tipo_de_tarjeta);
            nombre_banco = itemView.findViewById(R.id.nombre_banco);
            num_cuenta = itemView.findViewById(R.id.num_cuenta_item);
            fecha = itemView.findViewById(R.id.fecha_item);
            cvv = itemView.findViewById(R.id.cvv_item);
            nip = itemView.findViewById(R.id.nip_item);
            info = itemView.findViewById(R.id.info);
            usuario_app = itemView.findViewById(R.id.userapp_item);
            password_app = itemView.findViewById(R.id.passwordapp_item);
            cardView = itemView.findViewById(R.id.cardview);
            cardView.setOnCreateContextMenuListener(this);
            cardView.setOnClickListener(v -> {
                ClipData clip = ClipData.newPlainText("simple text",num_cuenta.getText().toString());
                clipboard.setPrimaryClip(clip); // probar funcion
                Toast.makeText(context,"# cuenta copiado al portapapeles",Toast.LENGTH_SHORT).show();
                //Log.d("clipboard","copiado al portapapeles");
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(nombre_banco.getText().toString());
            menu.add(this.getAdapterPosition(),0,0,"Editar");
            menu.add(this.getAdapterPosition(),1,1,"Eliminar");
        }
    }

}
