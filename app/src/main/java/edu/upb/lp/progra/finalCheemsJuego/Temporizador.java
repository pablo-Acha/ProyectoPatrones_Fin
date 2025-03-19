package edu.upb.lp.progra.finalCheemsJuego;

public class Temporizador implements Runnable{
    private int centecimaDeSegundo = 0;
    private FinalCheemsGame3 game;
    private boolean stop = false;
    private boolean crearTrampas = true;
    private EnemigoGigante enemigoGigante = new EnemigoGigante(game);

    public Temporizador(FinalCheemsGame3 game) {
        this.game = game;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isStop() {
        return stop;
    }

    @Override
    public void run() {
        if (!stop){
            if (enemigoGigante.getVivo()){
            if (crearTrampas){
                game.crearTrampasDelMundo();
                game.mostrarTrampas();
                crearTrampas = false;
            } else {
                game.borrarTrampas();
                crearTrampas=true;
            }

            } else {
                game.borrarTrampas();
            }
            if(!game.isGameOver()){
                game.executeLater(this,4000);
            }
        }
    }
}
