package com.softchan.pwd.Datos;

import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import com.softchan.pwd.Objeto.UserInf;
import com.softchan.pwd.Objeto.inf;
import com.softchan.pwd.Objeto.infoApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by oscar on 10/02/20.
 */

public class Write {

    // guardar/crear archivo de informacion de usuario
    public static boolean saveUserInf(String user, String password, int id) {
        boolean flag = false;
        try {
            File fileName = new File(Paths.pathInf.getAbsolutePath(), Paths.nameUserInfFile);
            if (id == 0) { // cuando el id es 0 significa que no se han creado datos de usuario
                if (!Paths.pathInf.isDirectory()) {
                    Paths.pathInf.mkdirs();
                    //Log.d("Write","mkdirs");
                } else {
                    if (!fileName.exists()) {
                        //Log.d("write","filenameexistls");
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);
                        UserInf pwd = new UserInf(user, password);
                        salida.writeObject(pwd);
                        flag = true;
                        //Log.d("Write",flag+" guardado");
                    }
                }
            } else {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);
                UserInf pwd = new UserInf();
                pwd.setPasswordApp(password);
                salida.writeObject(pwd);
                flag = true;
            }
        } catch (FileNotFoundException fnfe) {
            Log.d("FNFE",fnfe.getMessage());
        } catch (IOException ioe) {
            Log.d("IOE",ioe.getMessage());
        }

        return flag;
    }

    public static boolean saveUserInf(String data, String type) {
        boolean flag = false;
        List<String> userInf = Read.getUserInf();
        try {
            File fileName = new File(Paths.pathInf.getAbsolutePath(), Paths.nameUserInfFile);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);
            UserInf pwd = new UserInf();

            switch (type){
                case "user":
                    pwd.setUser(data);
                    pwd.setPasswordApp(userInf.get(1));
                    salida.writeObject(pwd);
                    flag = true;
                    break;
                case "password":
                    pwd.setUser(userInf.get(0));
                    pwd.setPasswordApp(data);
                    salida.writeObject(pwd);
                    flag = true;
                    break;
                default:
                    break;
            }

        } catch (FileNotFoundException fnfe) {
            Log.d("FNFE",fnfe.getMessage());
        } catch (IOException ioe) {
            Log.d("IOE",ioe.getMessage());
        }

        return flag;
    }

}
