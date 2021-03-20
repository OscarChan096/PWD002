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
import java.util.ArrayList;

/**
 * Created by oscar on 10/02/20.
 */

public class Write {

    /*public static void UpdatePwd(String titulo, String password, String usuario) {

        String nameObj = titulo;
        String ext = ".pwd";
        String nameFile = nameObj + ext;

        try {
            //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
            File fileName = new File(Paths.pathFilex.getAbsolutePath(), nameFile);
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            pswdExtends pwd = new pswdExtends(titulo, password, usuario, nameFile);

            salida.writeObject(pwd);

        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        }

    }


    public static void SavePwd(String name, String password, String usuario, View view) {

        String nameObj = name;
        String ext = ".pwd";
        String nameFile = nameObj + ext;

        try {
            //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
            File fileName = new File(Paths.pathFilex.getAbsolutePath(), nameFile);
            if (Paths.pathFilex.isDirectory()) {

                fileName = new File(Paths.pathFilex.getAbsolutePath(), nameFile);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                pswdExtends pwd = new pswdExtends(name, password, usuario, nameFile);

                salida.writeObject(pwd);

                //Snackbar.make(view, "Contraseña guardada con exito", Snackbar.LENGTH_SHORT).show();

            } else if (fileName.exists()) {
                Snackbar.make(view, "Existe la contraseña de este archivo", Snackbar.LENGTH_SHORT).show();
            }

        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        }

    }*/

    // guardar/crear archivo de informacion de usuario
    public static boolean saveUserInf(String user, String password, int id) {
        boolean flag = false;
        try {
            File fileName = new File(Paths.pathInf.getAbsolutePath(), Paths.nameUserInfFile);
            if(id == 0){
                if (!Paths.pathInf.isDirectory()){
                    Paths.pathInf.mkdirs();
                    //Log.d("Write","mkdirs");
                }else{
                    if (!fileName.exists()) {
                        //Log.d("write","filenameexistls");
                        FileOutputStream fileOut = new FileOutputStream(fileName);
                        ObjectOutputStream salida = new ObjectOutputStream(fileOut);
                        UserInf pwd = new UserInf(user,password);
                        salida.writeObject(pwd);
                        flag = true;
                        //Log.d("Write",flag+" guardado");
                    }
                }
            }else {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);
                UserInf pwd = new UserInf();
                pwd.setPasswordApp(password);
                salida.writeObject(pwd);
                flag = true;
            }
        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
            //Log.d("IOE",ioe.getMessage());
        }

        return flag;
    }

    public static void SaveInf(String passwordnew, View view) {
        String name = "pswd.inf";
        try {
            File fileName = new File(Paths.pathInf.getAbsolutePath(), name);
            if (!fileName.exists()) {

                if (!Paths.pathInf.isDirectory())
                    Paths.pathInf.mkdirs();

                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                inf pwd = new inf(passwordnew);

                salida.writeObject(pwd);

                Snackbar.make(view, "Contraseña guardada con exito", Snackbar.LENGTH_SHORT).show();

            } else {
                Snackbar.make(view, "Existe el archivo contraseña", Snackbar.LENGTH_SHORT).show();
            }

        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        }
    }

    public static void SaveInfApp(boolean update) {
        String name = "infoApp.app";
        try {
            //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
            File fileName = new File(Paths.pathInf.getAbsolutePath(), name);

            if (!Paths.pathInf.isDirectory())
                Paths.pathInf.mkdirs();

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            infoApp pwd = new infoApp();
            pwd.setUpdate(update);

            salida.writeObject(pwd);


        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) { }
    }

}
