package com.studio.chan.pwd.Datos;

import com.studio.chan.pwd.Objeto.Pswd;

import java.util.ArrayList;

/**
 * Created by oscar on 10/02/20.
 */

public class UpdateDate {

    public static void update(){
        int nFile;
        ArrayList<Pswd> list = Read.getFiles();

        for (nFile = 0; nFile <= list.size(); nFile++) {
            try {

                Pswd aux = (Pswd) list.get(nFile);
                String titulo = aux.getNombre();
                String password = aux.getPassword();

                Write.UpdatePwd(titulo,password,"");

                Write.SaveInfApp(true);

            } catch (ArrayIndexOutOfBoundsException e) {}
        } // end for

    }

}
