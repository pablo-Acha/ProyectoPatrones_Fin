package edu.upb.lp.progra.finalCheemsJuego;

public class EstadoNormal implements EstadoHeroe {
    @Override
    public void recibirDanio(int danio, Heroe heroe) {
        heroe.setVida(heroe.getVida() - danio);
        heroe.borrarVida();
        // Actualizar UI con la vida actual
        if (heroe.getVida() <= 0) {
            heroe.morir();
        } else {
//            personaje.setEstado(new EstadoHerido()); // Cambiar a estado herido
        }
    }

    @Override
    public void actualizarUI(Personaje personaje) {
        // Actualizar corazones normalmente
        personaje.getGame().setImageOnCell(0, 20, "corazon_lleno");
    }

}
