package edu.upb.lp.progra.finalCheemsJuego;

public class EstadoHerido implements EstadoHeroe {
    private int duracionInvencibilidad = 2000; // 2 segundos

    @Override
    public void recibirDanio(int danio, Heroe heroe) {
        // No recibe daño durante la invencibilidad
        heroe.getGame().showTemporaryMessage("¡Invencible!");
    }

    @Override
    public void actualizarUI(Personaje personaje) {
        // Parpadear corazones
        personaje.getGame().setImageOnCell(0, 20, "corazon_parpadeo");
        // Volver al estado normal después del tiempo
        personaje.getGame().executeLater(() -> {
  //          personaje.setEstado(new EstadoNormal());
        }, duracionInvencibilidad);
    }
}
