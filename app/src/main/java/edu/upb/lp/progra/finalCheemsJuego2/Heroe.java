package edu.upb.lp.progra.finalCheemsJuego2;

public class Heroe implements Atacados {
    private int posicionX;
    private int posicionY;
    private String direccion;
    private MediatorObjetos mediator;
    private String nombre;
    int vida = 100;

    private int municion = 100;
    /**
     *
     * @param posicionX
     * @param posicionY
     * @param direccion
     * @param nombre
     * @param mediator
     */
    public Heroe(int posicionX, int posicionY, String direccion,String nombre, MediatorObjetos mediator) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccion = direccion;
        this.mediator = mediator;
        this.nombre = nombre;
    }

    public void mover(String direccion){
        int posicionAnteriorY = this.posicionY;
        int posicionAnteriorX = this.posicionX;
        this.direccion = direccion;
        // Actualizar la posición
        if(direccion.equals("arriba")){
            this.posicionY-=1;
        }else if(direccion.equals("abajo")){
            this.posicionY+=1;
        }else if(direccion.equals("derecha")){
            this.posicionX+=1;
        }else{
            this.posicionX-=1;
        }
        // Notificar al Mediator sobre el movimiento
        mediator.notificar("heroeMovido", new Object[]{posicionAnteriorY, posicionAnteriorX, posicionY, posicionX,nombre,direccion});
    }

    public void aparecer(){
        mediator.notificar("heroeAparecer", new Object[]{posicionY, posicionX,nombre,direccion});
    }

    public void disparar(){
        if(municion>0){
            mediator.notificar("heroeDispara", new Object[]{posicionY, posicionX,direccion,this});
            municion-=1;
            mediator.notificar("modificarMunicion",municion);
        }
    }
    public int getPosicionX() {
        return posicionX;
    }
    public int getPosicionY() {
        return posicionY;
    }
    public String getDireccion() {
        return direccion;
    }
    public boolean hasMunicion(){
        return municion>0;
    }

    @Override
    public void recibirDanio(int danio) {
        vida-= danio;
        if(vida<=0){morir();}
        mediator.notificar("actualizarVida",vida);
    }

    @Override
    public void morir() {

    }
}
