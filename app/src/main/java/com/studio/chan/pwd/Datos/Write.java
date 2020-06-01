package com.studio.chan.pwd.Datos;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.studio.chan.pwd.Objeto.inf;
import com.studio.chan.pwd.Objeto.pswdExtends;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by oscar on 10/02/20.
 */

public class Write {

    public static void UpdatePwd(String titulo, String password, String usuario) {

        String nameObj = titulo;
        String ext = ".pwd";
        String nameFile = nameObj + ext;

        try {
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
            File fileName = new File(path.getAbsolutePath(), nameFile);
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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx");
            File fileName = new File(path.getAbsolutePath(), nameFile);
            if (path.isDirectory()) {

                fileName = new File(path.getAbsolutePath(), nameFile);
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream salida = new ObjectOutputStream(fileOut);

                pswdExtends pwd = new pswdExtends(name, password, usuario, nameFile);

                salida.writeObject(pwd);

                Snackbar.make(view, "Contraseña guardada con exito", Snackbar.LENGTH_SHORT).show();

            } else if (fileName.exists()) {
                Snackbar.make(view, "Existe la contraseña de este archivo", Snackbar.LENGTH_SHORT).show();
            }

        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        }

    }

    public static void SaveInf(String passwordnew, View view) {
        String name = "pswd.inf";
        try {
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
            File fileName = new File(path.getAbsolutePath(), name);
            if (!fileName.exists()) {

                if (!path.isDirectory())
                    path.mkdirs();

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
            File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
            File fileName = new File(path.getAbsolutePath(), name);

            if (!path.isDirectory())
                path.mkdirs();

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);

            infoApp pwd = new infoApp();
            pwd.setUpdate(update);

            salida.writeObject(pwd);


        } catch (FileNotFoundException fnfe) {
        } catch (IOException ioe) {
        }
    }

}
