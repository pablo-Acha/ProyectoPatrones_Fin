package edu.upb.lp.progra.finalCheemsJuego;

public class FinalCheemsGame4 extends FinalCheemsGame{
    public FinalCheemsGame4(FinalCheemsConector conector) {
        super(conector, 4);
    }

    @Override
    public void click(int vertical, int horizontal) {
        super.click(vertical, horizontal);
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
    public void crearEnemigos() {

    }

    @Override
    public void crearObstaculosDelMundo() {
        boolean [][] matrizDeObstaculos = getMatrizDeParedes();
        for (int i = 0; i < 14; i++) {
            matrizDeObstaculos[3][i] = true;
            matrizDeObstaculos[6][i] = true;
        }
        matrizDeObstaculos[4][13] = true;
        matrizDeObstaculos[5][13] = true;

    }

    @Override
    public void crearTrampasDelMundo() {

    }
}
