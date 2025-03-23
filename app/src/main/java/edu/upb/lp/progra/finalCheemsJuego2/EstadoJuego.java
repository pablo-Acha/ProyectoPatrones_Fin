package edu.upb.lp.progra.finalCheemsJuego2;

public interface EstadoJuego {
    void iniciar();
    void actualizar();
    void manejarInput(int y, int x); // Para manejar clics en la pantalla
    void renderizar(); // Para actualizar la Vista
}
