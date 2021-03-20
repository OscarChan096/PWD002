package com.softchan.pwd.Datos;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GeneratePassword {

    private boolean chkma;
    private boolean chkmi;
    private boolean chknu;
    private boolean chksi;

    public GeneratePassword(boolean chkma, boolean chkmi, boolean chknu, boolean chksi){
        this.chkma = chkma;
        this.chkmi = chkmi;
        this.chknu = chknu;
        this.chksi = chksi;
    }

    public String generatePwd8(){
        CharPasswords cp = new CharPasswords();
        String pwd = "";
        if (chkma && chkmi && chknu && chksi){
            // min-may-num-sim
            for (int i = 0; i < 2; i++){
                pwd += cp.generateMinMayNumSim();
            }
        }else if (chkmi && chkma && chknu){
            // min-may-num
            for (int i = 0; i < 3; i++){
                pwd += cp.generateMinMayNum();
            }
            pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkma && chknu && chksi){
            for (int i = 0; i < 3; i++){
                pwd += cp.generateMaNuSi();
            }
            pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkmi && chknu && chksi){
            for (int i = 0; i < 3; i++){
                pwd += cp.generateMinNumSim();
            }
            pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkmi && chkma && chksi) {
            for (int i = 0; i < 3; i++){
                pwd += cp.generateMinMaySim();
            }
            pwd = pwd.substring(0, pwd.length()-1);
        }else if (chkmi && chkma){
            for (int i = 0; i < 4; i++) {
                pwd += cp.generateMinMay();
            }
        }else if (chkmi && chksi){
            for (int i = 0; i < 4; i++) {
                pwd += cp.generateMinSim();
            }
        }else if (chkma && chksi){
            for (int i = 0; i < 4; i++) {
                pwd += cp.generateMaySim();
            }
        }else if (chknu && chksi){
            for (int i = 0; i < 4; i++) {
                pwd += cp.generateNumSim();
            }
        }else if (chkma && chknu) {
            // may-num
            for (int i = 0; i < 4; i++) {
                pwd += cp.generateMayNum();
            }
        } else if (chkmi && chknu) {
            // min-num
            for (int i = 0; i < 4; i++) {
                pwd += cp.generateMinNum();
            }
        } else if (chknu) {
            // num
            for (int i = 0; i < 8; i++) {
                pwd += cp.numeros();
            }
        } else if (chkma){
            for (int i = 0; i < 8; i++) {
                pwd += cp.letrasMayus();
            }
        }else if (chkmi){
            for (int i = 0; i < 8; i++) {
                pwd += cp.letrasMin();
            }
        }else if (chksi){
            for (int i = 0; i < 8; i++) {
                pwd += cp.simbolos();
            }
        }else{
            pwd = "Seleccione una/varias opciones para contraseña";
        }

        return pwd;
    }

    public static class CharPasswords{
        public CharPasswords(){}

        public char letrasMin(){
            char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            int random = (int) (Math.random()*(chars.length));
            Log.d("min",chars[random]+"");
            return chars[random];
        }

        public char letrasMayus(){
            char c = (char)(letrasMin() - (int) 'a' + (int) 'A');
            Log.d("mayuscula",c+"");
            return c;
        }

        public char simbolos(){
            char[] chars = {'@','-','_','#'};
            int random = (int)(Math.random()*(chars.length));
            Log.d("sim",chars[random]+"");
            return chars[random];
        }

        public int numeros(){
            int[] num = {0,1,2,3,4,5,6,7,8,9};
            int random = (int)(Math.random()*(num.length));
            Log.d("num",num[random]+"");
            return num[random];
        }

        // - - -  - - - - - - - devuelven 2 digitos - - - - - -  - - - - -  -
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
