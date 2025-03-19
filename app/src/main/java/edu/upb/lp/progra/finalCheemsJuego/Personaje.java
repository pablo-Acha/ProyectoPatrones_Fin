package edu.upb.lp.progra.finalCheemsJuego;

public abstract class Personaje extends ObjetosDinamicos {
    private int vida = 100;

    private Balas balas;
    private String tipoDeBala;
    private int cantidadDeBalas = 100;

    public Personaje(int posicionV, int posicionH, FinalCheemsGame game, String nombre, String tipoDeBala) {
        super(posicionV, posicionH, game,nombre);
        this.tipoDeBala = tipoDeBala;

    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setCantidadDeBalas(int cantidadDeBalas) {
        this.cantidadDeBalas = cantidadDeBalas;
    }

    public int getVida () {
        return vida;
    }

    public String getTipoDeBala() {
        return tipoDeBala;
    }



    public void bajarVida(){
        vida--;
    }

    public void bajarBalas(){
        cantidadDeBalas--;
    }

    public int getCantidadDeBalas() {
        return cantidadDeBalas;
    }

    public void dispararLasBalas () {
        if(cantidadDeBalas > 0) {
            getGame().dispararBala(getPosicionV(), getPosicionH(), getDireccion(), tipoDeBala);
            bajarBalas();
        }
    }


    public void aumentarVidas(int masVidas){
        if(vida + masVidas > 100){
            vida = 100;
        } else  {
            vida += masVidas;
        }
    }
    public void aumentarBalas(int masBalas){
        if (cantidadDeBalas + masBalas  > 100){
            cantidadDeBalas = 100;
        } else {
            cantidadDeBalas += masBalas;
        }
    }
    public void recibirDanio(int danio){
        vida -= danio;
    }
    public  boolean estaMuerto(){
        return vida <= 0;
    }
    public abstract void morir();

    public void caerEnTrampa(){
        if(getGame().getMatrizDeTrampas()[getPosicionV()][getPosicionH()]){
            recibirDanio(40);
        }
    }
   @Override
    public void moverseArriba(){
        int posV = getPosicionV();
        int posH = getPosicionH();
        if (!getGame().verSiHayUnaBalaAdelante(posV-1,posH)){
            if (!getGame().verSihayUnPersonajeAdelante(posV-1,posH)) {
                super.moverseArriba();
                caerEnTrampa();
                getGame().recogerItem(getPosicionV(), getPosicionH());
            }
        } else{
            getGame().impacto(posV,posH,posV-1,posH);
        }
    }

    @Override
    public void moverseAbajo() {
        int posV = getPosicionV();
        int posH = getPosicionH();
        if (!getGame().verSiHayUnaBalaAdelante(posV+1,posH)){
            if (!getGame().verSihayUnPersonajeAdelante(posV+1,posH)){
                super.moverseAbajo();
                caerEnTrampa();
                getGame().recogerItem(getPosicionV(),getPosicionH());
            }
        } else{
            getGame().impacto(posV,posH,posV+1,posH);
        }
    }


    @Override
    public void moverseIzquierda() {
        int posV = getPosicionV();
        int posH = getPosicionH();
        if (!getGame().verSiHayUnaBalaAdelante(posV,posH-1)){
            if (!getGame().verSihayUnPersonajeAdelante(posV,posH-1)) {
                super.moverseIzquierda();
                caerEnTrampa();
                getGame().recogerItem(getPosicionV(), getPosicionH());
            }
        } else{
            getGame().impacto(posV,posH,posV,posH-1);
        }
    }

    @Override
    public void moverseDerecha() {
        int posV = getPosicionV();
        int posH = getPosicionH();
        if (!getGame().verSiHayUnaBalaAdelante(posV,posH+1)){
            if (!getGame().verSihayUnPersonajeAdelante(posV,posH+1)) {
                super.moverseDerecha();
                caerEnTrampa();
                getGame().recogerItem(getPosicionV(), getPosicionH());
            }
        } else{
            getGame().impacto(posV,posH,posV,posH+1);
        }
    }

}