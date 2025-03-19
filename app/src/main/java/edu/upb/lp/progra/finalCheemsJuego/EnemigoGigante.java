package edu.upb.lp.progra.finalCheemsJuego;

import java.util.LinkedList;
import java.util.Random;

public class EnemigoGigante implements Runnable {
    private FinalCheemsGame3 game;
    private FragmentoDeChihuhua[][] partes = new FragmentoDeChihuhua[4][4];
    private String direccion = "derecha";
    private boolean stop = false;
    private boolean vivo = true;
    private int vida = 500;
    public EnemigoGigante(FinalCheemsGame3 game) {
        this.game = game;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
//                partes[i][j] = new FragmentoDeChihuhua(4+i, 9+j, game, "fragmentos"+i+"_"+j,"bolas" , this);
            }
        }
    }
    public void morir(){
        if(vida <= 0){
            stop = true;
            game.pararTemporizador();
            //game.dejarLlave();
            int v = partes[0][0].getPosicionV();
            int h = partes[0][0].getPosicionH();
            for (int i = 0; i < 4; i++) {
                for (int j =0; j <4 ; j++) {
                    game.setImageOnCell(v+i,h+j,"pantalladeljuego3"+(v+i)+"_"+(h+j));
                }

            }
//            game.pararTrampas();
        }
    }
    public void moverseAleatoreamente(){
        Random rnd = new Random();
        boolean disparar = rnd.nextBoolean();
        int num = rnd.nextInt(6);
        if(partes[0][0].getPosicionV() < 5 && num == 0){
            for (int i = 3; i > -1 ; i--) {
                for(int j = 0; j < 4 ; j++){
                    partes[i][j].moverseAbajo();
                }
            }
            direccion = "abajo";
        } else if(partes[3][0].getPosicionV() > 4 && num == 2){
            for (int i = 0; i < 4 ; i++) {
                for(int j = 0; j < 4 ; j++){
                    partes[i][j].moverseArriba();
                }
            }
            direccion = "arriba";
        }else if(partes[0][0].getPosicionH() > 5 && num == 3){
            for (int i = 0; i < 4 ; i++) {
                for(int j = 0; j < 4 ; j++){
                    partes[i][j].moverseIzquierda();
                }
            }
            direccion = "izquierda";
        }else if(partes[0][3].getPosicionH() < 16 && num == 4){
            for(int i = 0; i < 4 ; i++){
                for (int j = 3; j > -1 ; j--){
                    partes[i][j].moverseDerecha();
                }
            }
            direccion = "derecha";
        }else if(num == 5){
            dispararLasBalas();
        }
        if(disparar){
            dispararLasBalas();
        }
    }
    public void diparaEnLasCuatroDirecciones(){

    }
    public void dispararLasBalas(){
        if(direccion.equals("derecha")){
            for(int i = 0; i< 4; i++){
                 partes[i][3].dispararLasBalas();
            }
        } else if(direccion.equals("abajo")){
            for(int i = 0; i< 4; i++){
                partes[3][i].dispararLasBalas();
            }
         }else if(direccion.equals("arriba")){
            for(int i = 0; i< 4; i++){
                partes[0][i].dispararLasBalas();
            }
        }else if(direccion.equals("izquierda")){
            for(int i = 0; i< 4; i++){
                partes[i][0].dispararLasBalas();
            }
        }
    }

    @Override
    public void run() {
        if(!stop){
            if(vivo){
                moverseAleatoreamente();
                game.executeLater(this,1000);
            } else {
                stop = true;
                game.borrarEnemigo();
            }
        } else{
            morir();
        }
    }
    public void recibirDanio(int danio){
        vida -=danio;
        morir();
    }

    public boolean getVivo() {
        return vivo;
    }

//    public void pasarleListaAlGame () {
//        LinkedList<Personaje> personaje = new
//    }
}
