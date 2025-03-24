package edu.upb.lp.progra.finalCheemsJuego2;

import java.util.Random;

public class FabricaEnemigoFacil implements FabricaEnemigos{
    MediatorObjetos mediatorObjetos;
    RunnableMediator runnableMediator;

    public FabricaEnemigoFacil(MediatorObjetos mediatorObjetos, RunnableMediator runnableMediator) {
        this.mediatorObjetos = mediatorObjetos;
        this.runnableMediator = runnableMediator;
    }
//    int posicionY, int posicionX, String direccion, String nombreimage, RunnableMediator runnableMediator, MediatorObjetos mediatorObjetos
    @Override
    public Enemigo crearEnemigo() {
        Random random = new Random();
        int x = random.nextInt(10)+5;
        int y = random.nextInt(5)+1;

        return new EnemigoBase(y,x,"derecha","chihuhuafacil1",runnableMediator,mediatorObjetos);
    }
}
