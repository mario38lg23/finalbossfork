package com.rodasfiti.model;

public abstract class Enemigo{
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected int atributos;
    public Enemigo(String nombre, int vida, int ataque, int defensa) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    
    public void atacar() {
        
    }

    
    public void movimiento() {
        
    }

}
