package edu.upb.lp.progra.finalCheemsJuego;

public class AcabarElJuego implements Runnable{
    private FinalCheemsGame game;
    private boolean poderReinciar = false;
    public AcabarElJuego(FinalCheemsGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        poderReinciar = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 21; j++) {
                game.setImageOnCell(i,j,"gameoverpic"+i+"_"+j);
            }
        }
    }

    public boolean isPoderReinciar() {
        return poderReinciar;
    }
}
