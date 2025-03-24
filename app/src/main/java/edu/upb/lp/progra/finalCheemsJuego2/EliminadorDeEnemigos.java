package edu.upb.lp.progra.finalCheemsJuego2;

import java.util.LinkedList;

public class EliminadorDeEnemigos implements EnemigoObserver{
    private ControladorJuego controlador;
    public EliminadorDeEnemigos(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    @Override
    public void enemigoMuerto(Enemigo enemigo) {
        controlador.getModelo().getListaEnemigos().remove(enemigo);

        System.out.println("Enemigo eliminado: " + enemigo);
    }
}
