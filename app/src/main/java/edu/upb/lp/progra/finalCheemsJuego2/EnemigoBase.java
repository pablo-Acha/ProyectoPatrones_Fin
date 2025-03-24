package edu.upb.lp.progra.finalCheemsJuego2;

public class EnemigoBase implements Enemigo, Movible, Runnable {
    private int posicionY;
    private int posicionX;
    private int vida = 100;
    String direccion;
    String nombreimage;
    RunnableMediator runnableMediator;
    MediatorObjetos mediatorObjetos;
    private boolean avanzar = true;
    private EstrategiaMovimiento estrategiaMovimiento;
//dispare

    //soltar items
    // reciber danio

    public int getPosicionY() {
        return posicionY;
    }

    public int getPosicionX() {
        return posicionX;
    }
    public boolean stop = false;

    public EnemigoBase(int posicionY, int posicionX, String direccion, String nombreimage, RunnableMediator runnableMediator, MediatorObjetos mediatorObjetos, EstrategiaMovimiento estrategiaMovimiento) {
        this.posicionY = posicionY;
        this.posicionX = posicionX;
        this.direccion = direccion;
        this.nombreimage = nombreimage;
        this.runnableMediator = runnableMediator;
        this.mediatorObjetos = mediatorObjetos;
        this.estrategiaMovimiento = estrategiaMovimiento;
        aparecer();
    }
    public void mover(){
        estrategiaMovimiento.mover(this);
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
    public  boolean estaMuerto(){
        return vida <= 0;
    }
    @Override
    public void aparecer(){
        mediatorObjetos.notificar("enemigoAparecer",new Object[]{posicionY, posicionX,nombreimage,direccion});
    }
     public void morir() {
        int v = getPosicionY();
        int h = getPosicionX();
        //if(!getGame().isGameOver()){
        //    getGame().setImageOnCell(v,h,"pantalladeljuego"+getGame().getNivel()+v+"_"+h);
        //    dejarUnItem();
        //}
    }
    @Override
    public void run() {
        if (!stop) {
            if(estaMuerto()){
                stop = true;
                //getGame().borrarEnemigo();
            } else {
                int posicionY = getPosicionY();
                int posicionX = getPosicionX();
                mover();
                mediatorObjetos.notificar("enemigoMovido",new Object[]{posicionY, posicionX,getPosicionY(),getPosicionX(),nombreimage,direccion});
                runnableMediator.notificar(1000,this);
            }
        } else{
            morir();
        }
    }
}
