package com.studio.chan.pwd.Datos;

import com.studio.chan.pwd.Objeto.inf;
import com.studio.chan.pwd.Objeto.infoApp;
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

    // obtiene los datos guardados en psdExtends para visualizar en el listView
    public static ArrayList getFiles() {
        ArrayList<pswdExtends> arrayList = new ArrayList<>();
        ArrayList<String> nameListArray = new ArrayList<>();
        String name;
        short nFile;

        //File pathlist = new File(getExternalStorageDirectory(),"Android/data/com.studio.chan.pwd/filesx/inf");
        File fileNameList = new File(Paths.pathInf.getAbsolutePath(),"nameList.nl");
        //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (Paths.pathFilex.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        // --------------------------
                        if (!fileNameList.exists())
                            nameListArray.add(name);


                        File fileName = new File(Paths.pathFilex.getAbsolutePath(), name);
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
                if (!fileNameList.exists())
                    Write.fileNameList(nameListArray);
            }
        }
        return arrayList;
    }

    // se verifica la contraseña si es correcta para ingresar a la app
    public static boolean isPassword(String password) {
        boolean aprobado = false;
        String name = "pswd.inf";
        //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
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

    // verifica la existencia de contraseña
    public static boolean isExistsPswd() {
        //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
        File fileName = new File(Paths.pathInf.getAbsolutePath(), "pswd.inf");
        return (fileName.exists()) ? true : false;
    }


    public static boolean isUpdate() {
        boolean aprobado = false;
        String name = "infoApp.app";
        //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx/inf");
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
    }

    // actualizar lista de titulos de contraseñas
    public static void updateListNameArray(){
        ArrayList<String> nameListArray = new ArrayList<>();

        File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx").listFiles(); // obtiene la lista de archivos que existen en el directorio
        if (arrayFile != null) {
            for (short nFile = 0; nFile <= arrayFile.length; nFile++) {
                String name = arrayFile[nFile].getName();
                nameListArray.add(name);
            } // end for
            Write.fileNameList(nameListArray);
        }
    }

}
