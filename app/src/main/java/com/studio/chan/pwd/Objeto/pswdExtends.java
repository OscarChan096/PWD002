package com.studio.chan.pwd.Objeto;

import java.io.Serializable;

/**
 * Created by oscar on 10/02/20.
 */

public class pswdExtends implements Serializable {

    private String titulo;
    private String password;
    private String nameObj;
    private String usuario;

    public pswdExtends(String titulo, String password, String usuario, String nameObj){
        this.titulo = titulo;
        this.password = password;
        this.usuario = usuario;
        this.nameObj = nameObj;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameObj() {
        return nameObj;
    }

    public void setNameObj(String nameObj) {
        this.nameObj = nameObj;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
