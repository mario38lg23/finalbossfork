package com.rodasfiti.model;

public class Proveedor {
    private static Proveedor instance;
    private Protagonista protagonista;

    private Proveedor() {

    }

    public static Proveedor getInstance() {
        if (instance == null) {
            instance = new Proveedor();
        }
        return instance;
    }

    public Protagonista getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(Protagonista protagonista) {
        this.protagonista = protagonista;
    }
}