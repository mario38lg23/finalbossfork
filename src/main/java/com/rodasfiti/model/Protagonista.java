package com.rodasfiti.model;

public class Protagonista extends Personaje {

    public Protagonista(String nombre, int vida, int ataque, int defensa, int atributos, int nivel) {
        super(nombre, vida, ataque, defensa, atributos, nivel);

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
