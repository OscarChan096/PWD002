package com.softchan.pwd.UI;

import android.app.Application;

public class LightDarkApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ThemeSetup.applyTheme(this);
    }

}
