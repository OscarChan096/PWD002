package com.softchan.pwd.Datos;

import static com.softchan.pwd.Datos.Paths.nameUserInfFile;
import static com.softchan.pwd.Datos.Paths.pathInf;

import android.util.Log;

import com.softchan.pwd.Objeto.UserInf;

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

    // verifica si existen datos de usuario
    public static boolean isUserInf(){
        File file = new File(pathInf.getAbsolutePath(), nameUserInfFile);
        return file.exists();
    }

    // lee los datos del usuario
    public static ArrayList<String> getUserInf() {
        ArrayList<String> userInfList = new ArrayList<>();
        File fileName = new File(pathInf.getAbsolutePath(), nameUserInfFile);
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fileName));

            UserInf aux = (UserInf) entrada.readObject();
            String user = aux.getUser();
            String password = String.valueOf(aux.getPasswordApp());

            userInfList.add(user.toUpperCase());
            userInfList.add(password);

        } catch (FileNotFoundException ex) {
            Log.d("FNFE",ex.getMessage());
        } catch (IOException e) {
            Log.d("IOE",e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.d("AIOBE",e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.d("CNFE",e.getMessage());
        }

        return userInfList;
    }

}
