package edu.upb.lp.progra.finalCheemsJuego2;

import java.util.LinkedList;
import java.util.Random;

public class EnemigoBase implements Enemigo, Movible, Runnable {
    private LinkedList<EnemigoObserver> observadores = new LinkedList<>();
    private int posicionY;
    private int posicionX;
    private int vida = 100;
    String direccion;
    String nombreimage;
    RunnableMediator runnableMediator;
    MediatorObjetos mediatorObjetos;
    private boolean avanzar = true;
    private EstrategiaMovimiento estrategiaMovimiento;
    int coldDown = 0;
    public int getPosicionY() {
        return posicionY;
    }

    public int getPosicionX() {
        return posicionX;
    }
    public boolean stop = false;
    Random random = new Random();
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
        posicionX-=1; direccion = "izquierda";
    }

    @Override
    public void derecha() {
        posicionX+=1;direccion = "derecha";
    }

    @Override
    public void abajo() {
        posicionY+=1; direccion = "abajo";
    }

    @Override
    public void arriba() { posicionY-=1;direccion = "arriba";}
    @Override
    public void atacar() {

    }
    public  boolean estaMuerto(){
        return vida <= 0;
    }
    @Override
    public void aparecer(){mediatorObjetos.notificar("enemigoAparecer",new Object[]{posicionY, posicionX,nombreimage,direccion});}

    @Override
    public void run() {
        if (!stop) {
            if(estaMuerto()){
                stop = true;
                morir();
            } else {
                int posicionY = getPosicionY();
                int posicionX = getPosicionX();
                mover();
                mediatorObjetos.notificar("enemigoMovido",new Object[]{posicionY, posicionX,getPosicionY(),getPosicionX(),nombreimage,direccion,this});
                runnableMediator.notificar(random.nextInt(1000)+1000,this);
                if(coldDown%3 == 2){
                    disparar();
                }
                coldDown+=1;
            }
        }
    }
    @Override
    public void agregarObservador(EnemigoObserver observador) {
        observadores.add(observador);
    }

    private void notificarObservadores() {
        for (EnemigoObserver observador : observadores) {
            observador.enemigoMuerto(this);
        }
    }

    public void disparar(){
        mediatorObjetos.notificar("enemigoDispara", new Object[]{posicionY, posicionX,direccion});
    }

    @Override
    public void recibirDanio(int danio) {
        vida-= danio;
    }

    @Override
    public void morir() {
        mediatorObjetos.notificar("enemigoMuerto", new Object[]{posicionY, posicionX,nombreimage,direccion});
        notificarObservadores();
    }
}
