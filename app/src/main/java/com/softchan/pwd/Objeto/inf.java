package com.softchan.pwd.Objeto;

import java.io.Serializable;

/**
 * Created by oscar on 10/02/20.
 */

public class inf implements Serializable {

    private String passwordInf;

    public inf(String passwordInf){
        this.passwordInf = passwordInf;
    }

    public void setPasswordInf(String passwordInf) {
        this.passwordInf = passwordInf;
    }

    public String getPasswordInf() {
        return passwordInf;
    }

}
