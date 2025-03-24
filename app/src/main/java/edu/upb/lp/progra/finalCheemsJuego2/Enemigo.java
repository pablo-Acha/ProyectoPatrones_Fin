package edu.upb.lp.progra.finalCheemsJuego2;

/**
 * cada enemigo tiene una forma diferente de atacar
 */
public interface Enemigo extends Movible{
    void atacar();
    void aparecer();
    boolean estaMuerto();
}
