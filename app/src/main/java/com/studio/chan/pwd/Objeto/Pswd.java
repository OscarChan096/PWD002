package com.studio.chan.pwd.Objeto;

import java.io.Serializable;

/**
 * Created by oscar on 10/02/20.
 */

public class Pswd implements Serializable {

    private String nombre;
    private String password;
    private String nameObj;

    public Pswd(String nombre, String password, String nameObj){
        this.nombre = nombre;
        this.password = password;
        this.nameObj = nameObj;
    }

    public String getNombre(){
        return nombre;
    }

    public String getPassword(){
        return password;
    }

    public String getNameObj(){
        return nameObj;
    }

}
