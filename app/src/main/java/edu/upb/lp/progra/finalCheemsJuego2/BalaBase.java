package edu.upb.lp.progra.finalCheemsJuego2;

public class BalaBase implements Balas{
    private int posicionY;
    private int posicionX;
    String direccion;
    String nombreimage;
    RunnableMediator runnableMediator;
    MediatorObjetos mediatorObjetos;
    private boolean avanzar = true;
    public BalaBase(int posicionY, int posicionX, String direccion, String nombreimage, RunnableMediator runnableMediator,MediatorObjetos mediatorObjetos) {
        this.posicionY = posicionY;
        this.posicionX = posicionX;
        this.direccion = direccion;
        this.nombreimage = nombreimage;
        this.runnableMediator = runnableMediator;
        this.mediatorObjetos = mediatorObjetos;
    }

    @Override
    public void disparBala() {
        runnableMediator.notificar(100,this);
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
    public void setStop(boolean stop) {
        avanzar = stop;
    }

    @Override
    public void logicaDeMovimiento() {
        int posicionAnteriorY = this.posicionY;
        int posicionAnteriorX = this.posicionX;
        switch (direccion) {
            case "arriba":
                arriba();
                break;
            case "abajo":
                abajo();
                break;
            case "derecha":
                derecha();
                break;
            default:
                izquierda();
                break;
        }
        mediatorObjetos.notificar("balaMovida", new Object[]{posicionAnteriorY, posicionAnteriorX, posicionY, posicionX,nombreimage ,direccion,this});

    }

    @Override
    public void run() {
        logicaDeMovimiento();
        if(avanzar){
            runnableMediator.notificar(100,this);
        }

    }

}
