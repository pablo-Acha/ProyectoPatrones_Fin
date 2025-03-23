package edu.upb.lp.progra.finalCheemsJuego2;

public class EnemigoBase implements Enemigo {
    private int posicionY;
    private int posicionX;
    String direccion;
    String nombreimage;

    @Override
    public void run() {

    }

    @Override
    public void logicaDeMovimiento() {

    }

    @Override
    public void izquierda() {
        posicionX-=1;
    }

    @Override
    public void derecha() {
        posicionX+=1;
    }

    @Override
    public void abajo() {
        posicionY+=1;
    }

    @Override
    public void arriba() {
        posicionY-=1;

    }

    @Override
    public void atacar() {

    }
}
