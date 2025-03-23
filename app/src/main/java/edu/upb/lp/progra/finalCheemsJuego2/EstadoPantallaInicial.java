package edu.upb.lp.progra.finalCheemsJuego2;

public class EstadoPantallaInicial implements EstadoJuego{
    private ControladorJuego controlador;

    public EstadoPantallaInicial(ControladorJuego controlador) {
        this.controlador = controlador;
    }

    @Override
    public void iniciar() {
        // Mostrar la pantalla inicial
        controlador.getVista().mostrarPantallaInicial();
    }

    @Override
    public void actualizar() {
        // Lógica de actualización (si es necesaria)
    }

    @Override
    public void manejarInput(int y, int x) {
        // Cuando el usuario hace clic, cambiar al estado de selección de personaje
       controlador.cambiarEstado(new EstadoSeleccionPersonaje(controlador));
    }

    @Override
    public void renderizar() {
        // Actualizar la Vista (si es necesario)
    }
}
