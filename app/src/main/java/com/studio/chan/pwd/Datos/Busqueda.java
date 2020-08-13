package com.studio.chan.pwd.Datos;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

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

    public static pswdExtends Buscar(Context context, String title){
        //Log.d("Busqueda static","inicia");
        String name;
        short nFile;
        pswdExtends fileSearch = null;

        //File path = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx"); // funcional - se esta probando una nueva //obtiene el acceso a la memoria interna y obtiene el directorio
        if (Paths.pathFilex.isDirectory()) {
            File[] arrayFile = new File(getExternalStorageDirectory(), "Android/data/com.studio.chan.pwd/filesx").listFiles(); // obtiene la lista de archivos que existen en el directorio
            if (arrayFile != null) {
                for (nFile = 0; nFile <= arrayFile.length; nFile++) {
                    try {
                        name = arrayFile[nFile].getName();
                        //Toast.makeText(context,name+" "+title,Toast.LENGTH_SHORT).show();
                        if(title.equals(name)){
                            //Log.d("busqueda static if","encontrado");
                            File fileName = new File(Paths.pathFilex.getAbsolutePath(), name);
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
