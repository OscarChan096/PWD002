package com.softchan.pwd.SyncData;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.softchan.pwd.Datos.Read;
import com.softchan.pwd.dbroom.Pswd;

import org.json.JSONObject;

import java.util.List;

public class Upload implements Response.Listener<JSONObject>,Response.ErrorListener {
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private Context context;
    private List<Pswd> dataList;

    public Upload(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public Upload(Context context, List<Pswd> dataList){
        this.context = context;
        this.dataList = dataList;
        requestQueue = Volley.newRequestQueue(context);
    }

    // lee los datos pwd local en un ciclo para guardarlos en la base de datos
    public void initBackup(){
        for (Pswd pwd : dataList){
            String password = pwd.getPassword().replaceAll("#","SHARP");
            conectarWebService(pwd.getId()+"",pwd.getTitulo(),pwd.getUsuario(),password,"0","0","0");
            //Log.d("init",pwd.getId()+" "+pwd.getTitulo()+" "+pwd.getUsuario()+" "+password);
        }
    }

    // conecta y guarda los datos en la base de datos
    private void conectarWebService(String id, String titulo, String usuario, String password, String dd, String mm, String yyyy) {

        String url="http://192.168.0.9/pwsd/php/registro.php?id="+id+"&title="+titulo+"&user="+usuario+"&password="+password+"&day="+dd+"&month="+mm+"&year="+yyyy;
        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);

    }

    // sincroniza pin y nombre de usuario del telefono a la base de datos
    public void syncPin(){
        final List<String> userInf = Read.getUserInf();

        String url="http://192.168.0.9/pwsd/php/syncuserinf.php?id=1"+"&user="+userInf.get(0)+"&password="+userInf.get(1);
        url = url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("onErrorResponse",error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("onResponse",response.toString());
    }
}
