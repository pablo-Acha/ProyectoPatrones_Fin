package edu.upb.lp.progra.finalCheemsJuego2;

public class EnemigoBase implements Enemigo {
    private int posicionY;
    private int posicionX;
    String direccion;
    String nombreimage;
    RunnableMediator runnableMediator;
    MediatorObjetos mediatorObjetos;
    private boolean avanzar = true;
//dispare

    //soltar items
    // reciber danio

    public EnemigoBase(int posicionY, int posicionX, String direccion, String nombreimage, RunnableMediator runnableMediator, MediatorObjetos mediatorObjetos) {
        this.posicionY = posicionY;
        this.posicionX = posicionX;
        this.direccion = direccion;
        this.nombreimage = nombreimage;
        this.runnableMediator = runnableMediator;
        this.mediatorObjetos = mediatorObjetos;
        aparecer();
    }

    @Override
    public void run() {
        if(avanzar){

        }
    }

    @Override
    public void logicaDeMovimiento() {

    }

    @Override
    public void izquierda() {
        posicionX-=1;
    }

    @Override
    public void derecha() {
        posicionX+=1;
    }

    @Override
    public void abajo() {
        posicionY+=1;
    }

    @Override
    public void arriba() {
        posicionY-=1;

    }

    @Override
    public void atacar() {

    }
    @Override
    public void aparecer(){
        mediatorObjetos.notificar("enemigoAparecer",new Object[]{posicionY, posicionX,nombreimage,direccion});
    }
}
