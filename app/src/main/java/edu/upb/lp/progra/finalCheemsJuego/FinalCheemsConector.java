package edu.upb.lp.progra.finalCheemsJuego;


import edu.upb.lp.progra.adapterFiles.AndroidLibrary;
import edu.upb.lp.progra.adapterFiles.AppConnector;

public class FinalCheemsConector implements AppConnector {
    private AndroidLibrary library;
    private FinalCheemsGame game;
//    private FinalCheemsGame1 game1;

    public FinalCheemsConector(AndroidLibrary library) {
        this.library = library;
        this.game = new FinalCheemsGame1(this);
    }

    @Override
    public void onButtonPressed(String name) {

    }

    @Override
    public void onCellPressed(int vertical, int horizontal) {
        if(!game.isPasarAlSiguienteNivel()){
            game.click(vertical, horizontal);
        } else {
            Personaje heroe = game.getListaDePersonajes().getFirst();
            if(game.getNivel() == 1){
                game = new FinalCheemsGame2(this);
            } else if (game.getNivel() == 2){
                game = new FinalCheemsGame3(this);
            } else if (game.getNivel() == 3) {
                game = new FinalCheemsGame4(this);
            }
            game.pasarmeUnHeroe(heroe);
            game.iniciarElNivel();
        }
        if (game.isReiniciar()){
            game = new FinalCheemsGame1(this);
//            configureScreen(1,2);
            ((FinalCheemsGame1)game).historiaContada();
        }
    }

    @Override
    public void initialiseInterface() {
        game.llenarQueue();
        library.configureScreen(1,1,0,0,false,0);
        library.setImageOnCell(0,0,"pantalladeinicio");
    }
    public void setImageOnCell(int vertical, int horizontal,String image){
        library.setImageOnCell(vertical,horizontal,image);
    }
    public void executeLater(Runnable run, int ms){
        library.executeLater(run, ms);
    }
    public void configureScreen(int filas, int columnas){
        library.configureScreen(filas,columnas,0,0,false,0);
    }
    public void showTemporaryMessage(String mensaje){
        library.showTemporaryMessage(mensaje);
    }
}
