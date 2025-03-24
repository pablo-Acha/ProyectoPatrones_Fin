package edu.upb.lp.progra.finalCheemsJuego2;


import edu.upb.lp.progra.adapterFiles.AndroidLibrary;
import edu.upb.lp.progra.adapterFiles.AppConnector;
import edu.upb.lp.progra.finalCheemsJuego.FinalCheemsGame;

public class FinalCheemsConector implements AppConnector {
    private AndroidLibrary library;
    private VistaJuego vista;
    private ControladorJuego controlador;
    private ModeloJuego modelo;


    public AndroidLibrary getLibrary() {
        return library;
    }

    public FinalCheemsConector(AndroidLibrary library) {
        this.library = library;
        this.vista = new VistaJuego(this);
        // 2. Crear el Modelo
        this.modelo = new ModeloJuego();
        this.modelo.setConector(this);
        // 3. Crear el Controlador
        this.controlador = new ControladorJuego(vista, modelo,this);
    }

    @Override
    public void onButtonPressed(String name) {

    }

    @Override
    public void onCellPressed(int vertical, int horizontal) {
        controlador.onCellPressed(vertical, horizontal);
    }

    @Override
    public void initialiseInterface() {
        library.configureScreen(1,1,0,0,false,0);
        controlador.getEstadoActual().iniciar();
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
