package edu.upb.lp.progra.finalCheemsJuego2;

public class EstadoNiveles implements EstadoJuego {
    private ControladorJuego controlador;

    public EstadoNiveles(ControladorJuego controlador) {
        this.controlador = controlador;
        iniciar();
    }

    @Override
    public void iniciar() {
        // Cargar el primer nivel
        controlador.getVista().cargarNivel(1);
        controlador.getModelo().cargarNivel(1);
    }

    @Override
    public void actualizar() {
        // Lógica de actualización del nivel
    }

    @Override
    public void manejarInput(int y, int x) {
        // Manejar clics en el nivel (por ejemplo, mover el personaje)
        String accion = "*";
        if (y == 7 && x == 1) {
            accion = "arriba";
        } else if (y == 9 && x == 1) {
            accion = "abajo";
        } else if (y == 8 && x == 0) {
            accion = "izquierda";
        } else if (y == 8 && x == 2) {
            accion = "derecha";
        } else if (y > 6 && y < 10 && x > 17 && x < 21) {
            accion = "disparar";
        }
        controlador.getModelo().accionPersonaje(accion);
    }

    @Override
    public void renderizar() {
        // Actualizar la Vista con el estado actual del nivel
//        controlador.getVista().actualizar();
    }

}
