package edu.upb.lp.progra.finalCheemsJuego2;

public class MovimientoPersecucion implements EstrategiaMovimiento{
    private Heroe heroe;
    public MovimientoPersecucion(Heroe heroe) {
        this.heroe = heroe;
    }

    @Override
    public void mover(Movible enemigo) {
        int deltaX = heroe.getPosicionX() - ((EnemigoBase) enemigo).getPosicionX();
        int deltaY = heroe.getPosicionY() - ((EnemigoBase) enemigo).getPosicionY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) enemigo.derecha();
            else enemigo.izquierda();
        } else {
            if (deltaY > 0) enemigo.abajo();
            else enemigo.arriba();
        }

    }
}
