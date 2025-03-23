package edu.upb.lp.progra.finalCheemsJuego2;

import java.util.LinkedList;
import java.util.Queue;

public class EstadoSeleccionPersonaje implements EstadoJuego{
    private ControladorJuego controlador;
    private Queue<String> colaHeroes = new LinkedList<>();

    public EstadoSeleccionPersonaje(ControladorJuego controlador) {
        this.controlador = controlador;
        //iniciar();
        crearListaPersonajes();
    }

    public void crearListaPersonajes(){
        colaHeroes.add("batman");
        colaHeroes.add("deadpool");
        colaHeroes.add("donatelo");
    }
    @Override
    public void iniciar() {
        // Mostrar el menú de selección de personaje
        controlador.getVista().mostrarMenuPersonajes();
    }

    @Override
    public void actualizar() {
        // Lógica de actualización (si es necesaria)
    }

    @Override
    public void manejarInput(int y, int x) {
        // Cuando el usuario selecciona un personaje, cambiar al estado de niveles
        if(x == 1){
            colaHeroes.add(colaHeroes.peek());
            colaHeroes.poll();
            controlador.getVista().cambiarPersonaje(colaHeroes.peek());
        }else{
            controlador.getModelo().escogerHeroe(colaHeroes.peek(), controlador.getMediator());
            controlador.cambiarEstado(new EstadoNiveles(controlador));
        }
        // controlador.cambiarEstado(new EstadoNiveles(controlador));
    }

    @Override
    public void renderizar() {
        // Actualizar la Vista (si es necesario)
    }
}
