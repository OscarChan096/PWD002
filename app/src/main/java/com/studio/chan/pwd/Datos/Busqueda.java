package com.studio.chan.pwd.Datos;

import com.studio.chan.pwd.Objeto.pswdExtends;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by oscar on 28/03/20.
 */

public class Busqueda {

    public static pswdExtends Buscar(String title){
        String name;
        short nFile;
        pswdExtends fileSearch = null;

        File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx"); // obtiene el acceso a la memoria interna y obtiene el directorio
        if (path.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        if(title.equals(name)){
                            File fileName = new File(path.getAbsolutePath(), name);
                            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));
                            fileSearch = (pswdExtends) entrada.readObject();
                        }
                    } catch (FileNotFoundException ex) {
                    } catch (IOException e) {
                    } catch (ArrayIndexOutOfBoundsException e) {
                    } catch (ClassNotFoundException e) {
                    }
                } // end for
            }
        }
        return fileSearch;
    }

}
