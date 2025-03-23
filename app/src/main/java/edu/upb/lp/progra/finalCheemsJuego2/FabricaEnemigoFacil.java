package edu.upb.lp.progra.finalCheemsJuego2;

public class FabricaEnemigoFacil implements FabricaEnemigos{
    @Override
    public Enemigo crearEnemigo() {
        return new EnemigoBase();
    }
}
