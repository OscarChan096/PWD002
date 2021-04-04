package com.softchan.pwd.dbroom;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.softchan.pwd.dbroom.daos.PswdDAO;

import java.util.List;

public class DBAcces {

    private static DBAcces dbAcces;
    private PswdDAO pswdDAO;
    private AppDataBase appDataBase;

    private DBAcces (Context context){
        Context appContext = context.getApplicationContext();
        appDataBase = Room.databaseBuilder(appContext, AppDataBase.class, "dbpswd").allowMainThreadQueries().build();
        pswdDAO = appDataBase.getDAO();
    }

    public static DBAcces getInstance(Context context){
        if (dbAcces == null){
            dbAcces = new DBAcces(context);
        }
        return dbAcces;
    }

    public List<Pswd> getPswd(){
        return pswdDAO.getPswd();
    }

    public List<Pswd> findByTitulo(String titulo){
        return pswdDAO.findByTitulo(titulo);
    }

    public List<Pswd> findByUsuario(String usuario){
        return pswdDAO.findByUsuario(usuario);
    }

    public void add(Pswd pswd){
        pswdDAO.add(pswd);
    }

    public void delete(Pswd pswd){
        pswdDAO.delete(pswd);
    }

    public void deleteById(int id){
        pswdDAO.deleteById(id);
    }

    public void update(int id, String titulo, String usuario, String password){
        pswdDAO.update(id,titulo,usuario,password);
    }

    public void update(Pswd pswd){
        pswdDAO.updatex(pswd);
    }

}
