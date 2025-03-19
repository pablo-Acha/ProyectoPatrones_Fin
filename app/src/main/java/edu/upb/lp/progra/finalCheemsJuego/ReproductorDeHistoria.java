package edu.upb.lp.progra.finalCheemsJuego;

public class ReproductorDeHistoria implements Runnable {
    private FinalCheemsGame game;
    private int numeroDeLaEscena = 0;
    private String[] historia = {"historiaparte1","historiaparte2", "historiaparte3","historiaparte4"} ;

    public ReproductorDeHistoria(FinalCheemsGame game) {
        this.game = game;
    }

    public void setNumeroDeLaEscena(int numeroDeLaEscena) {
        this.numeroDeLaEscena = numeroDeLaEscena;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                game.setImageOnCell(i,j,historia[numeroDeLaEscena] + i + "_" + j);
            }
        }
        numeroDeLaEscena++;
        if(numeroDeLaEscena < 4){
            game.executeLater(this,100);
        }
    }

    public int getNumeroDeLaEscena() {
        return numeroDeLaEscena;
    }
}
