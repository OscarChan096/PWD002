package com.softchan.pwd.Dialogo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.softchan.pwd.R;

/**
 * Created by oscar on 31/03/20.
 */

public class DialogoBusqueda extends DialogFragment {

    public interface OnDialogoBusquedaListener{
        void Buscar(String nameFile);
    }

    OnDialogoBusquedaListener dialogLis;

    private static final String TAG = DialogoBusqueda.class.getSimpleName();

    public DialogoBusqueda() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createLoginDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Buscar");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialogo_busqueda, null);

        builder.setView(v);

        final EditText nameFile = v.findViewById(R.id.dialogBusqueda);
        Button buscar = v.findViewById(R.id.btnBusqueda);

        buscar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogLis.Buscar(nameFile.getText().toString()+".pwd");
                        dismiss();
                    }
                }

        );

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;
        if (context instanceof Activity){
            activity=(Activity) context;
        }

        try {
            dialogLis = (OnDialogoBusquedaListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSimpleDialogListener");

        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                dialogLis = (OnDialogoBusquedaListener) activity;

            } catch (ClassCastException e) {
                throw new ClassCastException(
                        activity.toString() +
                                " no implementó OnSimpleDialogListener");

            }
        }
    }

}
