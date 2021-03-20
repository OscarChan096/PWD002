package com.softchan.pwd.Objeto;

import java.io.Serializable;

public class UserInf implements Serializable {

    private String user;
    private String passwordApp;

    public UserInf(){}

    public UserInf(String user, String passwordApp){
        this.user = user;
        this.passwordApp = passwordApp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswordApp() {
        return passwordApp;
    }

    public void setPasswordApp(String passwordApp) {
        this.passwordApp = passwordApp;
    }
}
