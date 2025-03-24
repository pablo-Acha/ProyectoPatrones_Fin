package edu.upb.lp.progra.finalCheemsJuego2;

public class EstadoNiveles implements EstadoJuego {
    private ControladorJuego controlador;
    private FabricaEnemigos fabricaEnemigos;
    public EstadoNiveles(ControladorJuego controlador) {
        this.controlador = controlador;
        iniciar();
    }

    @Override
    public void iniciar() {
        // Cargar el primer nivel
        if(controlador.getModelo().getNivelActual() ==1){
            crearNivelUno();
        }
    }

    @Override
    public void actualizar() {
    }

    @Override
    public void manejarInput(int y, int x) {
        // Manejar clics en el nivel (por ejemplo, mover el personaje)
        String accion = "*";
        if (y == 7 && x == 1) {
            accion = "arriba";
        } else if (y == 9 && x == 1) {
            accion = "abajo";
        } else if (y == 8 && x == 0) {
            accion = "izquierda";
        } else if (y == 8 && x == 2) {
            accion = "derecha";
        } else if (y > 6 && y < 10 && x > 17 && x < 21) {
            accion = "disparar";
        }
        controlador.getModelo().accionPersonaje(accion);
    }

    @Override
    public void renderizar() {
    }

    private void crearNivelUno(){
        //crear fabrica de enemigos facil
        controlador.getVista().cargarNivel(1);
        controlador.getModelo().cargarNivel(1);
        fabricaEnemigos= new FabricaEnemigoFacil(controlador.getMediator(),controlador.getRunnableMediator());
        for(int i=0; i<5; i++){
            controlador.getModelo().inicializarEnemigos(fabricaEnemigos.crearEnemigo(i%2==0?new MovimientoAleatorio():new MovimientoPersecucion(controlador.getModelo().getHeroe())));
        }
        EliminadorDeEnemigos eliminador = new EliminadorDeEnemigos(controlador);
        for(int i=0; i<5; i++){
            controlador.getModelo().getListaEnemigos().get(i).agregarObservador(eliminador);
        }
    }
}
