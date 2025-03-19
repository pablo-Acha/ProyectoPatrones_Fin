package edu.upb.lp.progra.finalCheemsJuego;

import java.util.LinkedList;
import java.util.Random;

public class FinalCheemsGame1 extends FinalCheemsGame{

    public FinalCheemsGame1(FinalCheemsConector conector) {
        super(conector,1);
        llenarQueue();
    }

    @Override
    public void click (int vertical, int horizontal) {
            if(getHistoriaDeInicio().getNumeroDeLaEscena() == 0){
                reprdoucirHistoria();
            } else if (getHistoriaDeInicio().getNumeroDeLaEscena() == 4){
                if (!getPersonajeSeleccionado()){
                    seleccionarPersonaje(vertical,horizontal);
                } else {
                    super.click(vertical,horizontal);
                }
            }
        }
    public void reprdoucirHistoria(){
        getConector().configureScreen(2, 2);
        getConector().executeLater(getHistoriaDeInicio(), 0);
        setConfigurarPantalla(true);
//        getConector().executeLater(getTiempo(),0);
    }
    public void seleccionarPersonaje(int vertical, int horizontal) {
        getConector().configureScreen(1, 2);
        for (int i = 0; i < 2; i++) {
            setImageOnCell(0, i, "batman0_" + i);
        }

        if(horizontal == 1){
            getPersonajesASeleccionar().offer(getPersonajesASeleccionar().poll());
            getConector().setImageOnCell(0,0,getPersonajesASeleccionar().peek()+"0_0");
        } else if(horizontal == 0){
            setPersonajeSelecionado(true);
            getListaDePersonajes().addFirst(new Heroe(5,1,this,getPersonajesASeleccionar().peek(),"bala"));
//            getTiempo().setStop(false);

            getConector().configureScreen(10, 21);
            iniciarElNivel();
        }
    }
    @Override
    public void crearElmapa(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 21; j++) {
                getConector().setImageOnCell(i, j, "pantalladeljuego" +getNivel() + i + "_" + j);
            }
        }
    }
    @Override
    public void crearObstaculosDelMundo(){
        boolean[][] matrizDeParedes = getMatrizDeParedes();
        matrizDeParedes[2][7] = true;
        matrizDeParedes[5][13] = true;
        matrizDeParedes[5][14] = true;
        matrizDeParedes[5][15] = true;
        matrizDeParedes[4][8] = true;
        matrizDeParedes[4][9] = true;
        matrizDeParedes[5][8] = true;
        matrizDeParedes[5][9] = true;
        setMatrizDeParedes(matrizDeParedes);
    }
    @Override
    public void crearTrampasDelMundo() {
        boolean[][]matrizDeTrampas = getMatrizDeTrampas();
        matrizDeTrampas[1][1] = true;
        matrizDeTrampas[3][5] = true;
        matrizDeTrampas[3][6] = true;
        matrizDeTrampas[3][7] = true;
        matrizDeTrampas[1][10]= true;
        matrizDeTrampas[8][11] = true;
        matrizDeTrampas[4][13] = true;
        matrizDeTrampas[4][14] = true;
        matrizDeTrampas[2][20] = true;
        setMatrizDeTrampas(matrizDeTrampas);
    }
    @Override
    public void crearEnemigos(){
        Random rnd  = new Random();
        int num = rnd.nextInt(10)+10;
        LinkedList<Personaje> personajes = getListaDePersonajes();
        for (int i = 0; i < 10; i++) {
            int cordenadasEnV = rnd.nextInt(7) + 1;
            int cordenadasEnH = rnd.nextInt(12)+ 4;
            if(!getMatrizDeParedes()[cordenadasEnV][cordenadasEnH]){
                personajes.add(new Enemigos(cordenadasEnV, cordenadasEnH,this,"chihuhuafacil", "cuchillo"));
            }
        }
        setListaDePersonajes(personajes);
        for (int i = 1; i < getListaDePersonajes().size(); i++) {
            executeLater((Enemigos) getListaDePersonajes().get(i),0);
        }
    }
    public void historiaContada(){
        getHistoriaDeInicio().setNumeroDeLaEscena(4);
    }
}

