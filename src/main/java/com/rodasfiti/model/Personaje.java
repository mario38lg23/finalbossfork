package com.rodasfiti.model;

import java.util.Random;

public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int atributos;
    protected static Random r = new Random();

    public Personaje(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        if (this.vida > 0 && this.vida < 15) {
            this.vida = vida;
        }
    }

    public int getAtaque() {
        return this.ataque;
    }

    public void setAtaque(int ataque) {
        if (this.ataque > 0 && this.ataque < 15) {
            this.ataque = ataque;
        }
    }

    public int getDefensa() {
        return this.defensa;
    }

    public void setDefensa(int defensa) {
        if (this.defensa > 0 && this.defensa < 15) {
            this.defensa = defensa;
        }
    }

    public int getAtributos() {
        return this.atributos;
    }

    public void setAtributos(int atributos) {
        if (getAtaque() + getDefensa() + getVida() == 15) {
            this.atributos = atributos;
        }
    }

    public void atacar() {
        if (r.nextInt(10) > getDefensa()) {
            vida = getVida() - getAtaque();
        }
    }

    public void movimiento() {

    }

    public void morir() {
        if (getVida() <= 0) {
            System.out.println("El personaje ha muerto");
        }
    }
}