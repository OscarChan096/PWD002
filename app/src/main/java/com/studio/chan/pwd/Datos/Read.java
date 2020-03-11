package com.studio.chan.pwd.Datos;

import com.studio.chan.pwd.Objeto.inf;
import com.studio.chan.pwd.Objeto.pswdExtends;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by oscar on 10/02/20.
 */

public class Read {

    public static ArrayList getFiles() {
        ArrayList<pswdExtends> arrayList = new ArrayList<>();
        String name;

        short nFile;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (path.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        File fileName = new File(path.getAbsolutePath(), name);
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                        pswdExtends aux = (pswdExtends) entrada.readObject();
                        String titulo = aux.getTitulo();
                        String usuario = aux.getUsuario();
                        String password = aux.getPassword();
                        String nameobj = aux.getNameObj();

                        pswdExtends pwd = new pswdExtends(titulo, password, usuario, nameobj);
                        arrayList.add(pwd);

                    } catch (FileNotFoundException ex) {
                    } catch (IOException e) {
                    } catch (ArrayIndexOutOfBoundsException e) {
                    } catch (ClassNotFoundException e) {
                    }
                } // end for
            }
        }
        return arrayList;
    }

    public static boolean isPassword(String password) {
        boolean aprobado = false;
        String name = "pswd.inf";
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
        File fileName = new File(path.getAbsolutePath(), name);
        if (fileName.exists()) {
            try {
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                inf aux = (inf) entrada.readObject();
                String paswd = aux.getPasswordInf();

                aprobado = (paswd.equals(password)) ? true : false;

            } catch (FileNotFoundException ex) {
            } catch (IOException e) {
            } catch (ArrayIndexOutOfBoundsException e) {
            } catch (ClassNotFoundException e) {
            }
        } else {
            aprobado = true;
        }

        return aprobado;
    }

    public static boolean isExistsPswd() {
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
        File fileName = new File(path.getAbsolutePath(), "pswd.inf");
        return (fileName.exists()) ? true : false;
    }

    public static boolean isUpdate() {
        boolean aprobado = false;
        String name = "infoApp.app";
        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
        File fileName = new File(path.getAbsolutePath(), name);

        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

            infoApp aux = (infoApp) entrada.readObject();
            aprobado = aux.isUpdate();

        } catch (FileNotFoundException ex) {
        } catch (IOException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (ClassNotFoundException e) {
        }


        return aprobado;
    }

}
