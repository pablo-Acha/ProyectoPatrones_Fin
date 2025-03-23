package edu.upb.lp.progra.finalCheemsJuego2;

public class RunnableMediator {
    private static RunnableMediator instance;

    private FinalCheemsConector conector;
    public RunnableMediator(FinalCheemsConector conector) {
        this.conector = conector;
    }

    public void notificar(int tiempo, Runnable remitente) {
        conector.executeLater(remitente,tiempo);
    }
}
