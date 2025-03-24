package edu.upb.lp.progra.finalCheemsJuego2;

public class ControladorJuego {
    private EstadoJuego estadoActual;
    private VistaJuego vista;
    private ModeloJuego modelo;
    private MediatorObjetos mediator;
    private FinalCheemsConector conector;
    private RunnableMediator runnableMediator;
    public ControladorJuego(VistaJuego vista, ModeloJuego modelo,FinalCheemsConector conector) {
        this.vista = vista;
        this.modelo = modelo;
        // Iniciar con la pantalla inicial
        this.estadoActual = new EstadoPantallaInicial(this);
        this.mediator = new MediatorObjetos(this);
        this.conector = conector;
        this.runnableMediator = new RunnableMediator(conector);
    }

    public void cambiarEstado(EstadoJuego nuevoEstado) {
        this.estadoActual = nuevoEstado;
        this.estadoActual.iniciar();
    }

    public void onCellPressed(int y, int x) {
        // Delegar el input al estado actual
        estadoActual.manejarInput(y, x);
    }

    public void actualizar() {
        // Delegar la actualización al estado actual
        estadoActual.actualizar();
    }

    public void renderizar() {
        // Delegar la renderización al estado actual
        estadoActual.renderizar();
    }

    public VistaJuego getVista() {
        return vista;
    }

    public ModeloJuego getModelo() {
        return modelo;
    }
    public EstadoJuego getEstadoActual(){
        return estadoActual;
    }
    public MediatorObjetos getMediator() {
        return mediator;
    }
    public FinalCheemsConector getConector() {
        return conector;
    }
    public RunnableMediator getRunnableMediator() {
        return runnableMediator;
    }
}