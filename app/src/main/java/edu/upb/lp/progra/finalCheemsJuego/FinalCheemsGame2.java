package edu.upb.lp.progra.finalCheemsJuego;

import java.util.LinkedList;
import java.util.Random;

public class FinalCheemsGame2 extends FinalCheemsGame{

    public FinalCheemsGame2(FinalCheemsConector conector) {
        super(conector,2);
    }

    @Override
    public void click(int vertical, int horizontal) {
        super.click(vertical,horizontal);
    }

    @Override
    public void crearEnemigos() {
        Random rnd  = new Random();
        int num = rnd.nextInt(10)+10;
        LinkedList<Personaje> personajes = getListaDePersonajes();
        for (int i = 0; i < 10; i++) {
            int cordenadasEnV = rnd.nextInt(7) + 1;
            int cordenadasEnH = rnd.nextInt(12)+ 4;
            if(!getMatrizDeParedes()[cordenadasEnV][cordenadasEnH]){
                personajes.add(new Enemigos(cordenadasEnV, cordenadasEnH,this,"chihuhuamedio", "bola"));
            }
        }
        setListaDePersonajes(personajes);
        for (int i = 1; i < getListaDePersonajes().size(); i++) {
            executeLater((Enemigos) getListaDePersonajes().get(i),0);

        }
    }



    @Override
    public void crearElmapa() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 21; j++) {
                setImageOnCell(i,j,"pantalladeljuego"+getNivel()+i+"_"+j);
            }
        }
    }

    @Override
    public void crearObstaculosDelMundo() {

    }

    @Override
    public void crearTrampasDelMundo() {
        boolean[][] matrizDeTrampas = new boolean[10][21];
        matrizDeTrampas[1][1] = true;
        matrizDeTrampas[5][1] = true;
        matrizDeTrampas[4][3] = true;
        matrizDeTrampas[3][6] = true;
        matrizDeTrampas[3][7] = true;
        matrizDeTrampas[3][8] = true;
        matrizDeTrampas[1][9] = true;
        matrizDeTrampas[1][10] = true;
        matrizDeTrampas[1][11] = true;
        matrizDeTrampas[4][11] = true;
        matrizDeTrampas[4][12] = true;
        matrizDeTrampas[6][10] = true;
        matrizDeTrampas[6][11] = true;
        matrizDeTrampas[8][11] = true;
        matrizDeTrampas[3][14] = true;
        matrizDeTrampas[4][14] = true;
        matrizDeTrampas[4][15] = true;
        matrizDeTrampas[6][16] = true;
        matrizDeTrampas[2][19] = true;
        setMatrizDeTrampas(matrizDeTrampas);
    }
}
