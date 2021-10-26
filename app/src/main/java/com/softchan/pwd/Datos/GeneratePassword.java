package com.softchan.pwd.Datos;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GeneratePassword {

    private boolean chkma;
    private boolean chkmi;
    private boolean chknu;
    private boolean chksi;
    private int lim;

    public GeneratePassword(boolean chkma, boolean chkmi, boolean chknu, boolean chksi, int lim){
        this.chkma = chkma;
        this.chkmi = chkmi;
        this.chknu = chknu;
        this.chksi = chksi;
        this.lim = lim;
    }

    public String generatePwd8(){
        CharPasswords cp = new CharPasswords();
        String pwd = "";
        if (chkma && chkmi && chknu && chksi){
            // min-may-num-sim - 2
            for (int i = 0; i < (lim/4); i++)
                pwd += cp.generateMinMayNumSim();
            for (int j = 0; j < (lim%4); j++)
                pwd += cp.numeros();
        }else if (chkmi && chkma && chknu){
            // min-may-num
            for (int i = 0; i < (lim/3); i++)
                pwd += cp.generateMinMayNum();
            for (int j = 0; j < (lim%3); j++)
                pwd += cp.letrasMayus();
            //pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkma && chknu && chksi){
            for (int i = 0; i < (lim/3); i++)
                pwd += cp.generateMaNuSi();
            for (int j = 0; j < (lim%3); j++)
                pwd += cp.simbolos();
            //pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkmi && chknu && chksi){
            for (int i = 0; i < (lim/3); i++)
                pwd += cp.generateMinNumSim();
            for (int j = 0; j < (lim%3); j++)
                pwd += cp.letrasMin();
            //pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkmi && chkma && chksi) {
            for (int i = 0; i < (lim/3); i++)
                pwd += cp.generateMinMaySim();
            for (int j = 0; j < (lim%3); j++)
                pwd += cp.letrasMayus();
            //pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkmi && chkma){
            for (int i = 0; i < (lim/2); i++)
                pwd += cp.generateMinMay();
            for (int j = 0; j < (lim%2); j++)
                pwd += cp.letrasMayus();
        }else if (chkmi && chksi){
            for (int i = 0; i < (lim/2); i++)
                pwd += cp.generateMinSim();
            for (int j = 0; j < (lim%2); j++)
                pwd += cp.letrasMin();
        }else if (chkma && chksi){
            for (int i = 0; i < (lim/2); i++)
                pwd += cp.generateMaySim();
            for (int j = 0; j < (lim%2); j++)
                pwd += cp.letrasMayus();
        }else if (chknu && chksi){
            for (int i = 0; i < (lim/2); i++)
                pwd += cp.generateNumSim();
            for (int j = 0; j < (lim%2); j++)
                pwd += cp.numeros();
        }else if (chkma && chknu) {
            // may-num
            for (int i = 0; i < (lim/2); i++)
                pwd += cp.generateMayNum();
            for (int j = 0; j < (lim%2); j++)
                pwd += cp.letrasMayus();
        } else if (chkmi && chknu) {
            // min-num
            for (int i = 0; i < (lim/2); i++)
                pwd += cp.generateMinNum();
            for (int j = 0; j < (lim%2); j++)
                pwd += cp.numeros();
        } else if (chknu) {
            // num
            for (int i = 0; i < lim; i++) {
                pwd += cp.numeros();
            }
        } else if (chkma){
            for (int i = 0; i < lim; i++) {
                pwd += cp.letrasMayus();
            }
        }else if (chkmi){
            for (int i = 0; i < lim; i++) {
                pwd += cp.letrasMin();
            }
        }else if (chksi){
            for (int i = 0; i < lim; i++) {
                pwd += cp.simbolos();
            }
        }else{
            pwd = "Seleccione una/varias opciones para contraseña";
        }

        return pwd;
    }

    // clase contenedor de diccionario para generar contraseñas
    public static class CharPasswords{
        public CharPasswords(){}

        public char letrasMin(){
            char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            int random = (int) (Math.random()*(chars.length));
            //Log.d("min",chars[random]+"");
            return chars[random];
        }

        public char letrasMayus(){
            char c = (char)(letrasMin() - (int) 'a' + (int) 'A');
            //Log.d("mayuscula",c+"");
            return c;
        }

        public char simbolos(){
            char[] chars = {'@','-','_'};
            int random = (int)(Math.random()*(chars.length));
            //Log.d("sim",chars[random]+"");
            return chars[random];
        }

        public int numeros(){
            int[] num = {0,1,2,3,4,5,6,7,8,9};
            int random = (int)(Math.random()*(num.length));
            //Log.d("num",num[random]+"");
            return num[random];
        }

        // - - -  - - - - - - - devuelven 2 digitos - - - - - - - - - - -  -
        public String generateMayNum(){
            return letrasMayus()+""+numeros()+"";
        }

        public String generateNumSim(){
            return numeros()+""+simbolos()+"";
        }

        public String generateMaySim(){
            return letrasMayus()+""+simbolos()+"";
        }

        public String generateMinSim(){
            return letrasMin()+""+simbolos()+"";
        }

        public String generateMinNum(){
            return letrasMin()+""+numeros()+"";
        }

        public String generateMinMay(){
            return letrasMin()+""+letrasMayus()+"";
        }


        // - - - - - - - -  - - - devuelven 3 digitos - - - -  - - - -  -
        public String generateMinNumSim(){
            return letrasMin()+""+numeros()+""+simbolos()+"";
        }

        public String generateMinMaySim(){
            return letrasMin()+""+letrasMayus()+""+simbolos()+"";
        }

        public String generateMaNuSi(){
            return letrasMayus()+""+numeros()+""+simbolos()+"";
        }

        public String generateMinMayNum(){
            return letrasMin()+""+letrasMayus()+""+numeros()+"";
        }

        public String generateMinMayNumSim(){
            return numeros()+""+letrasMin()+""+letrasMayus()+""+simbolos()+""; // devuelve 4 digitos
        }

    }

}
