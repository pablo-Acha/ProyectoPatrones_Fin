package edu.upb.lp.progra.finalCheemsJuego;

import java.util.LinkedList;

public class Balas extends ObjetosDinamicos implements Runnable{
    private boolean stop = false;
//    private Objetos[][] matrizDeObjetos;
    private boolean[][] matrizDeParedes;
    private int danio;
    private LinkedList<Personaje> personajes ;
    private boolean puedeRebotar = true;
    private int cantidadDeRebotes = 3;
//    private Objetos[][] objetosMatriz = new Objetos[10][21];
    private Personaje personaje;
    public Balas(int posicionV, int posicionH, FinalCheemsGame game, String nombre, int danio) {
        super(posicionV, posicionH, game, nombre);
        this.danio = danio;

    }
//    public void setMatrizDeObjetos(Objetos[][] matrizDeObjetos) {
//        this.matrizDeObjetos = matrizDeObjetos;
//    }

    public int getDanio() {
        return danio;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setPersonajes(LinkedList<Personaje> personajes) {
        this.personajes = personajes;
    }

    public void setMatrizDeParedes(boolean[][] matrizDeParedes) {
        this.matrizDeParedes = matrizDeParedes;
    }

    public boolean isStop() {
        return stop;
    }


    @Override
    public void run() {
        if (!stop && !getGame().isGameOver()){
            if (getDireccion().equals("arriba")){
                moverseArriba();
            } else if (getDireccion().equals("abajo")) {
                moverseAbajo();
            } else if (getDireccion().equals("izquierda")) {
                moverseIzquierda();
            } else if (getDireccion().equals("derecha")) {
                moverseDerecha();
            } else {
                moverseDerecha();
            }
            getGame().executeLater(this,200);
        } else {
            getGame().setImageOnCell(getPosicionV(),getPosicionH(),"pantalladeljuego"+getGame().getNivel()+getPosicionV()+"_"+getPosicionH());
            getGame().borrarBalas();
        }
    }
    @Override
    public void moverseArriba() {
        int pos = getPosicionV();
        if (!getGame().verSihayUnPersonajeAdelante(getPosicionV()-1,getPosicionH())){
            super.moverseArriba();
            balaQuieta(pos,getPosicionV());
        } else {
            getGame().impacto(getPosicionV()-1,getPosicionH(),getPosicionV(),getPosicionH());
        }
    }

    @Override
    public void moverseAbajo() {
        int pos = getPosicionV();
        if (!getGame().verSihayUnPersonajeAdelante(getPosicionV()+1,getPosicionH())){
            super.moverseAbajo();
            balaQuieta(pos,getPosicionV());
        } else {
            getGame().impacto(getPosicionV()+1,getPosicionH(),getPosicionV(),getPosicionH());
        }

    }

    @Override
    public void moverseIzquierda() {
        int pos = getPosicionH();
        if (!getGame().verSihayUnPersonajeAdelante(getPosicionV(),getPosicionH()-1)){
            super.moverseIzquierda();
            balaQuieta(pos,getPosicionH());
        } else {
            getGame().impacto(getPosicionV(),getPosicionH()-1,getPosicionV(),getPosicionH());
        }
    }

    @Override
    public void moverseDerecha() {
        int pos = getPosicionH();
        if (!getGame().verSihayUnPersonajeAdelante(getPosicionV(), getPosicionH() + 1)) {
            if (getPosicionH() < 19) {
                super.moverseDerecha();
            }
            balaQuieta(pos, getPosicionH());
        } else {
            getGame().impacto(getPosicionV(), getPosicionH() + 1, getPosicionV(), getPosicionH());
        }
    }
    public void balaQuieta(int posicionAnterior, int posicionActual){
        if (posicionAnterior == posicionActual ){
            stop = true;
        }
    }

}



