package edu.upb.lp.progra.finalCheemsJuego;

import java.util.Random;

public class FragmentoDeChihuhua extends Personaje implements Runnable{
    private boolean serLaCabeza ;
    private boolean serElUltimo;
    private int tamanioEnV = 4;
    private int tamanioEnH = 4;
    private int danioAcumulado = 0;
    private FragmentoDeChihuhua siguiente;
    public FragmentoDeChihuhua(int posicionV, int posicionH, FinalCheemsGame game, String nombre,
                               String tipoDeBala, boolean serLaCabeza,boolean serElUltimo ) {
        super(posicionV, posicionH, game, nombre, tipoDeBala);
        this.serLaCabeza = serLaCabeza;
        this.serElUltimo = serElUltimo;
        setVida(80);
    }

    public void setSiguiente(FragmentoDeChihuhua siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public void morir() {

    }

    public int getDanioAcumulado() {
        return danioAcumulado;
    }

    public void setDanioAcumulado(int danioAcumulado) {
        this.danioAcumulado = danioAcumulado;
    }

    public void moverAlSiguienteArriba(){
        moverseArriba();
        if(!serElUltimo){
//            siguiente.moverseArriba();
            siguiente.moverAlSiguienteArriba();
        }
    }
    public void moverAlSiguienteAbajo(){
        if(serElUltimo){
            moverseAbajo();
        }else{
            siguiente.moverAlSiguienteAbajo();
            moverseAbajo();
        }
    }
    public void moverAlSiguienteDerecha(){
        if(serElUltimo){
            moverseDerecha();
        } else{
            siguiente.moverAlSiguienteDerecha();
            moverseDerecha();
        }
    }
    public void moversAlSiguienteIzquierda(){
        moverseIzquierda();
        if (!serElUltimo){
            siguiente.moversAlSiguienteIzquierda();
        }
    }

    public void hacerDispararAlsiguienteDerecha(int tamanioH){
        if(tamanioH == 3){
            dispararLasBalas();
            tamanioH = 0;
            if(!serElUltimo){
                siguiente.hacerDispararAlsiguienteDerecha(tamanioH);
            }
        } else {
            if(!serElUltimo){
                siguiente.hacerDispararAlsiguienteDerecha(tamanioH+1);
            }
        }
    }
    public void hacerDispararAlsiguienteIzquierda(int tamanioH){
        if(tamanioH == 0){
            dispararLasBalas();
            tamanioH = 3;
            if(!serElUltimo){
                siguiente.hacerDispararAlsiguienteIzquierda(tamanioH);
            }
        } else {
            if (!serElUltimo){
                siguiente.hacerDispararAlsiguienteIzquierda(tamanioH-1);
            }
        }
    }
    public void hacerDispararAlsiguienteAbajo(int tamanioV,int tamanioH){
        if(tamanioH == 3){
            tamanioV++;
        }
        if(tamanioV == 3){
            dispararLasBalas();
//            tamanioV = tamanioEnV;
            if(!serElUltimo){
                siguiente.hacerDispararAlsiguienteAbajo(tamanioV,tamanioH);
            }
        } else {
            if(!serElUltimo){
                siguiente.hacerDispararAlsiguienteAbajo(tamanioV,tamanioH+1);
            }
        }
    }
    public void hacerDispararAlsiguienteArriba(int tamanioV,int tamanioH){
        if(tamanioH == 3){
            tamanioV++;
        }
        if(tamanioV == 0){
            dispararLasBalas();
//            tamanioV = tamanioEnV;
            if(!serElUltimo){
                siguiente.hacerDispararAlsiguienteArriba(tamanioV, tamanioH+1);
            }
        }
    }
    public void disparar(){
        if(serLaCabeza){
            if(getDireccion().equals("derecha")){
                hacerDispararAlsiguienteDerecha(0);
            } else if (getDireccion().equals("izquierda")) {
                hacerDispararAlsiguienteIzquierda(0);
            } else if (getDireccion().equals("arriba")) {
                hacerDispararAlsiguienteArriba(0,0);
            } else if (getDireccion().equals("abajo")) {
                hacerDispararAlsiguienteAbajo(0,0);
            }
        }
    }
    @Override
    public void recibirDanio(int danio) {
        if(!serLaCabeza){
            danioAcumulado += danio;
        }else{
            super.recibirDanio(danio+siguiente.getDanioAcumulado());
            siguiente.setDanioAcumulado(0);
        }
    }

    @Override
    public void run() {
        if(serLaCabeza){
            moverseAleatoreamente();
            if(!getGame().isGameOver()){
                getGame().executeLater(this,700);
            }
        }
    }
    public void moverseAleatoreamente(){
        Random rnd = new Random();
        int numAleatoreo = rnd.nextInt(5);
        boolean dispara = rnd.nextBoolean();
        if (numAleatoreo == 0 &&  getPosicionV() > 1){
            moverAlSiguienteArriba();
        } else if (numAleatoreo == 1 && getPosicionV() < 5) {
            moverAlSiguienteAbajo();
        }else if (numAleatoreo == 2 && getPosicionH() < 12) {
            moverAlSiguienteDerecha();
        }else if (numAleatoreo == 3 && getPosicionH() > 5) {
            moversAlSiguienteIzquierda();
        } else if (numAleatoreo == 4) {
            disparar();
        }
        if (dispara){
            disparar();
        }
    }
}
