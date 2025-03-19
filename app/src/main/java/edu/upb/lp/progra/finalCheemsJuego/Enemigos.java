package edu.upb.lp.progra.finalCheemsJuego;

import java.util.Random;

public class Enemigos extends Personaje implements Runnable{

    public boolean stop = false;
    public Enemigos(int posicionV, int posicionH, FinalCheemsGame game, String nombre, String tipoDeBala) {
        super(posicionV, posicionH, game, nombre, tipoDeBala);
    }
    public void setStop(boolean b) {
        stop = b;
    }
    public void dejarUnItem(){
        Items item;
        Random rnd = new Random();
        boolean bool = rnd.nextBoolean();
        if (bool){
            getGame().creaUnItem(getPosicionV(), getPosicionH(),"balasitem"+ getGame().getNivel());
//            item = new Items("Balas",getPosicionV(),getPosicionH());
        } else {
            getGame().creaUnItem(getPosicionV(), getPosicionH(),"vidasitem"+ getGame().getNivel());
//            item = new Items("Vidas",getPosicionV(),getPosicionH());
        }
//        return item;
    }
    public void movimientoAleatoreo(){
        Random rnd = new Random();
        boolean disparar = rnd.nextBoolean();
        int numeroAleatoreo = rnd.nextInt(5);
        if (numeroAleatoreo == 0){
            moverseArriba();
        } else if (numeroAleatoreo == 1) {
            moverseAbajo();
        } else if (numeroAleatoreo == 2){
            moverseDerecha();
        } else if (numeroAleatoreo == 4){
            moverseIzquierda();
        } else {
            dispararLasBalas();
        }
        if (disparar){
            dispararLasBalas();
        }

    }

    @Override
    public void morir() {
        int v = getPosicionV();
        int h = getPosicionH();
        if(!getGame().isGameOver()){
            getGame().setImageOnCell(v,h,"pantalladeljuego"+getGame().getNivel()+v+"_"+h);
            dejarUnItem();
        }
    }
    @Override
    public void run() {
        if (!stop) {
            if(estaMuerto()){
                stop = true;
                getGame().borrarEnemigo();
            } else {
                movimientoAleatoreo();
                getGame().executeLater(this,1000);
            }
        } else{
            morir();
        }
    }
}
