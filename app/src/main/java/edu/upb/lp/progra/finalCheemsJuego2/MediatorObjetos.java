package edu.upb.lp.progra.finalCheemsJuego2;

/**
 * Todos los los metodos de accion son los que estan abajo de modificar
 *
 */
public class MediatorObjetos implements Mediator{
    private ControladorJuego controlador;

    public MediatorObjetos(ControladorJuego controler) {
        this.controlador = controler;
    }

    @Override
    public void notificar(String evento, Object remitente) {
        if(evento.equals("heroeMovido")){
            movimientoHeroe(remitente);
        }else if(evento.equals("heroeAparecer")){
            aparecerHeroe(remitente);
        }else if(evento.equals("balaMovida")){
            movimientoBala(remitente);
        }else if(evento.equals("heroeDispara")){
           heroeDispara(remitente);
        }else if(evento.equals("enemigoAparecer")){
            aparecerEnemigo(remitente);
        }
    }


    private void movimientoHeroe(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int posicionAnteriorY = (int) datosMovimiento[0];
        int posicionAnteriorX = (int) datosMovimiento[1];
        int nuevaPosicionY = (int) datosMovimiento[2];
        int nuevaPosicionX = (int) datosMovimiento[3];
        String nombre = (String) datosMovimiento[4];
        String direccion = (String) datosMovimiento[5];
        String fondo = "pantalladeljuego" + nombre.charAt(nombre.length()-1)+posicionAnteriorY+"_"+posicionAnteriorY;
        controlador.getVista().actualizarCelda(posicionAnteriorY, posicionAnteriorX,fondo);
        controlador.getVista().actualizarCelda(nuevaPosicionY, nuevaPosicionX,nombre+direccion);
    }

    private void movimientoBala(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int posicionAnteriorY = (int) datosMovimiento[0];
        int posicionAnteriorX = (int) datosMovimiento[1];
        int nuevaPosicionY = (int) datosMovimiento[2];
        int nuevaPosicionX = (int) datosMovimiento[3];
        String direccion = (String) datosMovimiento[5];
        Balas bala = (Balas) datosMovimiento[6];
        String nombre = ((String) datosMovimiento[4])+controlador.getModelo().getNivelActual()+direccion;
        String fondo = "pantalladeljuego"+controlador.getModelo().getNivelActual()+posicionAnteriorY+"_"+posicionAnteriorX;
        if(controlador.getModelo().haycolsionPared(direccion,nuevaPosicionY,nuevaPosicionX)){
            controlador.getVista().actualizarCelda(posicionAnteriorY,posicionAnteriorX,fondo);
            bala.setStop(false);
        }else{

            //hacerdani0
            controlador.getVista().actualizarCelda(posicionAnteriorY,posicionAnteriorX,fondo);
            controlador.getVista().actualizarCelda(nuevaPosicionY,nuevaPosicionX,nombre);
        }
    }

    public void aparecerHeroe(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int posicionY = (int) datosMovimiento[0];
        int posicionX = (int) datosMovimiento[1];
        String nombre = (String) datosMovimiento[2];
        String direccion = (String) datosMovimiento[3];
        controlador.getVista().actualizarCelda(posicionY,posicionX,nombre+direccion);
    }
    public void aparecerEnemigo(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int posicionY = (int) datosMovimiento[0];
        int posicionX = (int) datosMovimiento[1];
        String nombre = (String) datosMovimiento[2];
        String direccion = (String) datosMovimiento[3];
        controlador.getVista().actualizarCelda(posicionY,posicionX,nombre+direccion);
    }

    public void heroeDispara(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int y = (int) datosMovimiento[0];
        int x = (int) datosMovimiento[1];
        String direccion = (String) datosMovimiento[2];
        Balas balaBase  = new BalaBase(y,x,direccion,"bala",controlador.getRunnableMediator(),this);
        controlador.getModelo().addBala(balaBase);
    }
}


