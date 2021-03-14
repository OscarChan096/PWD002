package com.softchan.pwd.Objeto;

import java.io.Serializable;

/**
 * Created by oscar on 10/02/20.
 */

public class infoApp implements Serializable {

    private boolean update;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

}
