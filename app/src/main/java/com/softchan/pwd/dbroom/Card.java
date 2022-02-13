package com.softchan.pwd.dbroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "card")
public class Card {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "banco")
    private String banco;

    @ColumnInfo(name = "numCuenta")
    private int numCuenta;

    @ColumnInfo(name = "numeroDeCuenta")
    private String numeroDeCuenta;

    @ColumnInfo(name = "nip")
    private int nip;

    @ColumnInfo(name = "fecha")
    private String fecha;

    @ColumnInfo(name = "cvv")
    private int cvv;

    @ColumnInfo(name = "usuarioApp")
    private String usuarioApp;

    @ColumnInfo(name = "passwordApp")
    private String passwordApp;

    @ColumnInfo(name = "tarjetaVirtual")
    private int tarjetaVirtual;

    public Card(){}

    @Ignore
    public Card(String banco, int numCuenta, int nip, String fecha, int cvv,
                String usuarioApp, String passwordApp, int tarjetaVirtual){
        this.banco = banco;
        this.numCuenta = numCuenta;
        this.nip = nip;
        this.fecha = fecha;
        this.cvv = cvv;
        this.usuarioApp = usuarioApp;
        this.passwordApp = passwordApp;
        this.tarjetaVirtual = tarjetaVirtual;
    }

    @Ignore
    public Card(String banco, int numCuenta, int nip, String fecha, int cvv, int tarjetaVirtual){
        this.banco = banco;
        this.numCuenta = numCuenta;
        this.nip = nip;
        this.fecha = fecha;
        this.cvv = cvv;
        this.tarjetaVirtual = tarjetaVirtual;
    }

    @Ignore
    public Card(String banco, String numeroDeCuenta, int nip, String fecha, int cvv,
                String usuarioApp, String passwordApp, int tarjetaVirtual){
        this.banco = banco;
        this.numeroDeCuenta = numeroDeCuenta;
        this.nip = nip;
        this.fecha = fecha;
        this.cvv = cvv;
        this.usuarioApp = usuarioApp;
        this.passwordApp = passwordApp;
        this.tarjetaVirtual = tarjetaVirtual;
    }

    @Ignore
    public Card(String banco, String numeroDeCuenta, int nip, String fecha, int cvv, int tarjetaVirtual){
        this.banco = banco;
        this.numeroDeCuenta = numeroDeCuenta;
        this.nip = nip;
        this.fecha = fecha;
        this.cvv = cvv;
        this.tarjetaVirtual = tarjetaVirtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getUsuarioApp() {
        return usuarioApp;
    }

    public void setUsuarioApp(String usuarioApp) {
        this.usuarioApp = usuarioApp;
    }

    public String getPasswordApp() {
        return passwordApp;
    }

    public void setPasswordApp(String passwordApp) {
        this.passwordApp = passwordApp;
    }

    public int getTarjetaVirtual() {
        return tarjetaVirtual;
    }

    public void setTarjetaVirtual(int tarjetaVirtual) {
        this.tarjetaVirtual = tarjetaVirtual;
    }
}
