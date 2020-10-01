package com.studio.chan.pwd.Internet;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.studio.chan.pwd.Datos.Read;
import com.studio.chan.pwd.Objeto.pswdExtends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Upload{
    private StringRequest stringRequest;
    private Context context;

    public Upload(Context context){
        this.context = context;
    }

    // lee los datos pwd local en un ciclo para guardarlos en la base de datos
    public void readAndUpload(){
        ArrayList<pswdExtends> listDatosPWDLocal = Read.getFiles();
        for (pswdExtends pwd : listDatosPWDLocal){
            conectarWebService(pwd.getTitulo(),pwd.getUsuario(),pwd.getPassword());
        }
    }

    // conecta y guarda los datos en la base de datos
    private void conectarWebService(final String titulo, final String usuario, final String contrasenia) {

        String ip="http://192.168.0.9"; // el usuario ingresaria la ip

        String url=ip+"/pwds/registro.php?";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Toast.makeText(context,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                //Log.i("onRespinse else: ",""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"No se ha podido conectar readAndUpload",Toast.LENGTH_SHORT).show();
                //Log.d("onErrorResponse"," no se a podido conectar readAndUpload"+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("titulo",titulo);
                parametros.put("usuario",usuario);
                parametros.put("contrasenia",contrasenia);

                return parametros;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(stringRequest);
    }

    // sincroniza el pin del telefono a la base de datos
    public void syncPin(){
        final String pin = Read.getPassword(context);

        String ip="http://192.168.0.9"; // el usuario ingresaria la ip

        String url=ip+"/pwds/syncpin.php?";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                if (response.trim().equalsIgnoreCase("registra")){
                    Toast.makeText(context,"Sincronizado!",Toast.LENGTH_SHORT).show();
                    Log.d("onRespnse if","Sincronizado! "+response);
                }else{
                    //Toast.makeText(context,"Error al sincronizar pin ",Toast.LENGTH_SHORT).show();
                    //Log.i("onRespinse else: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"No se ha podido conectar pin",Toast.LENGTH_SHORT).show();
                //Log.d("onErrorResponse"," no se a podido conectar pin"+error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("pin",pin);

                return parametros;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(context).addToRequestQueue(stringRequest);

    }

}
