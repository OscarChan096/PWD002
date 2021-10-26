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

    // verifica la existencia de contraseña
    public static boolean isExistsPswd() {
        File fileName = new File(Paths.pathInf.getAbsolutePath(), "pswd.inf");
        return (fileName.exists()) ? true : false;
    }

}
