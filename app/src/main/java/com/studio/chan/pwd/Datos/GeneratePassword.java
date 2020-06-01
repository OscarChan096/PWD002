package com.studio.chan.pwd.Datos;

import java.util.ArrayList;

/**
 * Created by oscar on 10/02/20.
 */

public class GeneratePassword {

    public static String getPassword(){
        String[] arrayStr = {"096","-","@","#","1996","9609","_","96","16","O","S","C","A","R","0","1","2","3","4","5","6","7","8","9"};
        int longitud = (int)(Math.random()*15+8);
        ArrayList<String> list = new ArrayList<>();
        String password = "";
        while(password.length()<longitud){
            int numRandom = (int) (Math.random()*(arrayStr.length));
            String str = arrayStr[numRandom];
            if (!list.contains(str)){
                password = password + str;
                list.add(str);
            }
        }
        return password;
    }

}
