package com.softchan.pwd.Datos;

import android.content.Context;
import android.widget.Toast;

import com.softchan.pwd.Objeto.UserInf;
import com.softchan.pwd.Objeto.inf;
import com.softchan.pwd.Objeto.infoApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by oscar on 10/02/20.
 */

public class Read {

    // obtiene los datos guardados en psdExtends para visualizar en el listView
    /*public static ArrayList getFiles() {
        ArrayList<pswdExtends> arrayList = new ArrayList<>();
        ArrayList<String> nameListArray = new ArrayList<>();
        String name;
        short nFile;
        File fileNameList = new File(Paths.pathInf.getAbsolutePath(),"nameList.nl");
        if (Paths.pathFilex.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 80; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        Log.d("getFiles",name+" "+nFile);

                        File fileName = new File(Paths.pathFilex.getAbsolutePath(), name);
                        Log.d("file","file");
                        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));
                        Log.d("entrada","entrada");

                        pswdExtends aux = (pswdExtends) entrada.readObject();
                        Log.d("titulo","aux");
                        String titulo = aux.getTitulo();
                        Log.d("titulo",titulo);
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
    }f*/

    public static boolean isUserInf(){
        File file = new File(Paths.pathInf.getAbsolutePath(), Paths.nameUserInfFile);
        if (file.exists())
            return true;
        else
            return false;
    }

    // lee los datos del usuario
    public static ArrayList<String> getUserInf() {
        ArrayList<String> userInfList = new ArrayList<>();
        File fileName = new File(Paths.pathInf.getAbsolutePath(), Paths.nameUserInfFile);
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

            UserInf aux = (UserInf) entrada.readObject();
            String user = aux.getUser();
            String password = String.valueOf(aux.getPasswordApp());

            userInfList.add(user.toUpperCase());
            userInfList.add(password);

        } catch (FileNotFoundException ex) {
        } catch (IOException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (ClassNotFoundException e) {
        }

        return userInfList;
    }

    // se verifica la contraseña si es correcta para ingresar a la app
    /*public static boolean isPassword(String password) {
        boolean aprobado = false;
        String name = "pswd.inf";
        File fileName = new File(Paths.pathInf.getAbsolutePath(), name);
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

    public static String getPassword(Context context){
        String paswd = "";
        String name = "pswd.inf";
        File fileName = new File(Paths.pathInf.getAbsolutePath(), name);
        if (fileName.exists()) {
            try {
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

                inf aux = (inf) entrada.readObject();
                paswd = aux.getPasswordInf();
            } catch (FileNotFoundException ex) {
            } catch (IOException e) {
            } catch (ArrayIndexOutOfBoundsException e) {
            } catch (ClassNotFoundException e) {
            }
        } else {
            Toast.makeText(context,"No existe pin para sincronizar", Toast.LENGTH_SHORT).show();
        }
        return paswd;
    }*/

    // verifica la existencia de contraseña
    public static boolean isExistsPswd() {
        //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
        File fileName = new File(Paths.pathInf.getAbsolutePath(), "pswd.inf");
        return (fileName.exists()) ? true : false;
    }


    /*public static boolean isUpdate() {
        boolean aprobado = false;
        String name = "infoApp.app";
        File fileName = new File(Paths.pathInf.getAbsolutePath(), name);

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
    }*/

}
