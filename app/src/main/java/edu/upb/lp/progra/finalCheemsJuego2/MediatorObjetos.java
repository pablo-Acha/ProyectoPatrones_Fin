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
        }else if(evento.equals("enemigoMovido")){
            moverEnemigo(remitente);
        }else if(evento.equals("enemigoMuerto")){
            borrarEnemigo(remitente);
        }else if(evento.equals("enemigoDispara")){
            enemigoDispara(remitente);
        }else if(evento.equals("actualizarVida")){
            actualizarVida(remitente);
        }
    }

    private void moverEnemigo(Object remitente) {
        Object[] datosMovimiento = (Object[]) remitente;
        int posicionAnteriorY = (int) datosMovimiento[0];
        int posicionAnteriorX = (int) datosMovimiento[1];
        int nuevaPosicionY = (int) datosMovimiento[2];
        int nuevaPosicionX = (int) datosMovimiento[3];
        String nombre = (String) datosMovimiento[4];
        String direccion = (String) datosMovimiento[5];
        String fondo = "pantalladeljuego" + nombre.charAt(nombre.length()-1)+posicionAnteriorY+"_"+posicionAnteriorX;
        Enemigo enemigo = (Enemigo) datosMovimiento[6];
        if(controlador.getModelo().haycolsionPared(direccion,posicionAnteriorY,posicionAnteriorX)){
            retrocederEnemigo(direccion,controlador.getModelo().getListaEnemigos().indexOf(enemigo));
        } else{
            controlador.getVista().actualizarCelda(posicionAnteriorY, posicionAnteriorX,fondo);
            controlador.getVista().actualizarCelda(nuevaPosicionY, nuevaPosicionX,nombre+direccion);
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
        String fondo = "pantalladeljuego" + nombre.charAt(nombre.length()-1)+posicionAnteriorY+"_"+posicionAnteriorX;
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
        if(controlador.getModelo().haycolsionPared(direccion,posicionAnteriorY,posicionAnteriorX)
            || controlador.getModelo().verificarColisionesBala(bala)){
            controlador.getVista().actualizarCelda(posicionAnteriorY,posicionAnteriorX,fondo);
            bala.setStop(false);
        }else{
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

        if(!controlador.getModelo().haycolsionPared(direccion,y,x)){
            switch (direccion) {
                case "arriba":
                    y -= 1;
                    break;
                case "derecha":
                    x += 1;
                    break;
                case "abajo":
                    y += 1;
                    break;
                default:
                    x -= 1;
                    break;
            }
            Balas balaBase  = new BalaBase(y,x,direccion,"bala",controlador.getRunnableMediator(),this);
            controlador.getModelo().addBala(balaBase);
        }
    }

    public void retrocederEnemigo(String direccion, int index){
        if (direccion.equals("arriba")){
            controlador.getModelo().getListaEnemigos().get(index).abajo();
        }else if(direccion.equals("derecha")){
            controlador.getModelo().getListaEnemigos().get(index).izquierda();
        }else if(direccion.equals("izquierda")){
            controlador.getModelo().getListaEnemigos().get(index).derecha();
        }else{
            controlador.getModelo().getListaEnemigos().get(index).arriba();
        }
    }
    public void borrarEnemigo(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int posicionY = (int) datosMovimiento[0];
        int posicionX = (int) datosMovimiento[1];
        String nombre = (String) datosMovimiento[2];
        String direccion = (String) datosMovimiento[3];
        String fondo = "pantalladeljuego" + nombre.charAt(nombre.length()-1)+posicionY+"_"+posicionX;
        controlador.getVista().actualizarCelda(posicionY,posicionX,fondo);
    }
    public void enemigoDispara(Object remitente){
        Object[] datosMovimiento = (Object[]) remitente;
        int y = (int) datosMovimiento[0];
        int x = (int) datosMovimiento[1];
        String direccion = (String) datosMovimiento[2];

        if(!controlador.getModelo().haycolsionPared(direccion,y,x)){
            switch (direccion) {
                case "arriba":
                    y -= 1;
                    break;
                case "derecha":
                    x += 1;
                    break;
                case "abajo":
                    y += 1;
                    break;
                default:
                    x -= 1;
                    break;
            }
            Balas balaBase  = new BalaBase(y,x,direccion,"bala",controlador.getRunnableMediator(),this);
            controlador.getModelo().addBala(balaBase);
        }
    }
    private void actualizarVida(Object remitente){
        int vida = (int) remitente;
        controlador.getVista().cambiarCorzones(vida,controlador.getModelo().getNivelActual());
    }
}


