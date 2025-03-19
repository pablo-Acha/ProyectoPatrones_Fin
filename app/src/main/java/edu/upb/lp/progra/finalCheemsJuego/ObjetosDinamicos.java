package edu.upb.lp.progra.finalCheemsJuego;

public abstract class ObjetosDinamicos {
    private int posicionV;
    private int posicionH;
    private FinalCheemsGame game;
    private String nombre;
    private String direccion = "derecha";

    public ObjetosDinamicos(int posicionV, int posicionH, FinalCheemsGame game, String nombre) {
        this.posicionV = posicionV;
        this.posicionH = posicionH;
        this.game = game;
        this.nombre = nombre;
    }

    //getters
    public String getDireccion() {
        return direccion;
    }

    public int getPosicionV() {
        return posicionV;
    }

    public int getPosicionH() {
        return posicionH;
    }

    public String getNombre() {
        return nombre;
    }

    public FinalCheemsGame getGame() {
        return game;
    }

    //setters
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPosicionH(int posicionH) {
        this.posicionH = posicionH;
    }

    public void setPosicionV(int posicionV) {
        this.posicionV = posicionV;
    }

    //metodos que son de la calse ObjetosDinamicos

    public void moverseArriba() {
        int v = posicionV;
        int h = posicionH;
        if (!getGame().getMatrizDeParedes()[v - 1][h] && posicionV > 0) {
            direccion = "arriba";
            ponerImagenDeItemOFondo();
            posicionV--;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }

    public void setGame(FinalCheemsGame game) {
        this.game = game;
    }

    /**
     * metodo para moverse abajo
     * el game tiene que pasarle todos los objetos
     */
    public void moverseAbajo() {
        int v = posicionV;
        int h = posicionH;
        if (!getGame().getMatrizDeParedes()[v + 1][h] && posicionV < 9) {
            direccion = "abajo";
            ponerImagenDeItemOFondo();
            posicionV++;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }

    public void moverseIzquierda() {
        int v = posicionV;
        int h = posicionH;
        if (!game.getMatrizDeParedes()[v][h - 1] && posicionH > 0) {
            direccion = "izquierda";
            ponerImagenDeItemOFondo();
            posicionH--;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }

    public void moverseDerecha(){
        int v = posicionV;
        int h = posicionH;
        if(!game.getMatrizDeParedes()[v][h+1] && posicionH < 20) {
            direccion = "derecha";
            ponerImagenDeItemOFondo();
            posicionH++;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }


    public void ponerImagenDeItemOFondo(){
        boolean ponerImagenDeFondo = true;
        int indiceDelItem = 0;
        for (int i = 0; i < getGame().getListaDeitems().size(); i++) {
            Items item = getGame().getListaDeitems().get(i);
            if (item.getPosicionV() == posicionV && item.getPosicionH() == posicionH){
                ponerImagenDeFondo = false;
                indiceDelItem = i;
            }
        }
        if (ponerImagenDeFondo){
            game.setImageOnCell(posicionV, posicionH,"pantalladeljuego"+game.getNivel()+posicionV+"_"+posicionH);
        } else if (!getGame().getListaDeitems().isEmpty()){
            game.setImageOnCell(posicionV, posicionH,getGame().getListaDeitems().get(indiceDelItem).getTipoDeItem());
        }
    }



    //comentar, a mi no me daj comentar todo xd
    public void moverseIndiagonalArribaDerecha(){
        int v = posicionV;
        int h = posicionH;
        if(!game.getMatrizDeParedes()[v-1][h+1] && posicionH < 20) {
            direccion = "arribaderecha";
            ponerImagenDeItemOFondo();
            posicionH++;
            posicionV--;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }
    public void moverseIndiagonalArribaIzquierda(){
        int v = posicionV;
        int h = posicionH;
        if(!game.getMatrizDeParedes()[v-1][h-1] && posicionH < 20) {
            direccion = "arribaizquierda";
            ponerImagenDeItemOFondo();
            posicionH--;
            posicionV--;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }
    public void moverseIndiagonalAbajoDerecha(){
        int v = posicionV;
        int h = posicionH;
        if(!game.getMatrizDeParedes()[v+1][h+1] && posicionH < 20) {
            direccion = "abajoderecha";
            ponerImagenDeItemOFondo();
            posicionH++;
            posicionV++;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }
    public void moverseIndiagonalAbajoIzquierda(){
        int v = posicionV;
        int h = posicionH;
        if(!game.getMatrizDeParedes()[v+1][h-1] && posicionH < 20) {
            direccion = "abajoizquierda";
            ponerImagenDeItemOFondo();
            posicionH--;
            posicionV++;
            game.setImageOnCell(posicionV, posicionH, nombre+game.getNivel()+direccion);
        }
    }
}


