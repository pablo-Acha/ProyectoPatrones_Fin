package edu.upb.lp.progra.finalCheemsJuego2;

/**
 * esta clase sirve para objetos que se mueven solos
 */
public interface Movible extends Runnable {
    void arriba();
    void abajo();
    void derecha();
    void izquierda();
    void logicaDeMovimiento();
    int getPosicionY();
    int getPosicionX();
}
