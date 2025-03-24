package edu.upb.lp.progra.finalCheemsJuego2;

import java.util.Random;

public class MovimientoAleatorio implements EstrategiaMovimiento{
    private Random random = new Random();
    @Override
    public void mover(Movible enemigo) {
        int direccion = random.nextInt(4); // 0: arriba, 1: abajo, 2: izquierda, 3: derecha
        switch (direccion) {
            case 0: enemigo.arriba(); break;
            case 1: enemigo.abajo(); break;
            case 2: enemigo.izquierda(); break;
            case 3: enemigo.derecha(); break;
        }
    }
}
