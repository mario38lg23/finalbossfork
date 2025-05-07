package com.rodasfiti.model;

public class Esqueleto extends Enemigo {

    public Esqueleto(String nombre, int vida, int ataque, int defensa, int posicion, int nivel, int velocidad, int percepcion) {
        super(nombre, vida, ataque, defensa,posicion,nivel, velocidad, percepcion);
    }

    @Override
    public void atacar() {
        super.atacar();
    }

    @Override
    public void movimiento() {
        super.movimiento();
    }

}
