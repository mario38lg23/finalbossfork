package com.rodasfiti.model;

public class Esqueleto extends Enemigo {

    public Esqueleto(TipoEnemigo tipo, int nivel, int ataque, int defensa, int vida, int velocidad, int percepcion, int x, int y) {
        super(tipo, nivel, ataque, defensa, vida, velocidad, percepcion, x, y);
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
