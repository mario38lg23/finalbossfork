package com.rodasfiti.model;

public class Enemigo extends Personaje{
    protected int percepcion;
    protected TipoEnemigo tipo;
    protected int x;
    protected int y;

    public Enemigo(TipoEnemigo tipo, int nivel, int ataque, int defensa, int vida, int velocidad, int percepcion, int x, int y) {
        super(nivel, ataque, defensa, vida, velocidad);
        this.percepcion = percepcion;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }
    
    public int getPercepcion() {
        return percepcion;
    }
    public void setPercepcion(int percepcion) {
        this.percepcion = percepcion;
    }
    

    public void atacar(Personaje objetivo) {
        super.atacar(objetivo);
    }

    public TipoEnemigo getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoEnemigo tipo) {
        this.tipo = tipo;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void movimiento() {

    }

}
