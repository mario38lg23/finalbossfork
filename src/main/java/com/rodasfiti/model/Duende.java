package com.rodasfiti.model;

public class Duende extends Enemigo {

    public Duende(TipoEnemigo tipo, int nivel, int ataque, int defensa, int vida, int velocidad, int percepcion, int x, int y) {
        super(tipo, nivel, ataque, defensa, vida, velocidad, percepcion, x, y);
    }

    @Override
    public void atacar(Personaje objetivo) {
        super.atacar(objetivo);
    }

    @Override
    public void movimiento() {
        super.movimiento();
    }

}
