package com.softchan.pwd.Functions;

import android.util.Log;

public class Splits {

    public Splits(){}

    public String numCuenta(String str){
        String numTarget = "";
        String groupFour = "";
        for(int i=0 ; i<str.length(); i++){
            char c = str.charAt(i);
            groupFour += c;
            int aux = i+1;
            if((aux%4)==0){
                groupFour += " ";
                numTarget += groupFour;
                groupFour = "";
            }
        }
        //Log.d("split",numTarget);
        return numTarget;
    }

}
