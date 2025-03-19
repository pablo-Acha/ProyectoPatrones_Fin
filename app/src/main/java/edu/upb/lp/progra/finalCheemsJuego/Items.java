package edu.upb.lp.progra.finalCheemsJuego;

import java.util.Random;

public class Items {
    private String tipoDeItem;
    private int posicionV;
    private int posicionH;

    public int getPosicionV() {
        return posicionV;
    }

    public int getPosicionH() {
        return posicionH;
    }

    public String getTipoDeItem() {
        return tipoDeItem;
    }

    public Items(String tipoDeItem, int posicionV, int posicionH) {
        this.tipoDeItem = tipoDeItem;
        this.posicionV = posicionV;
        this.posicionH = posicionH;
    }

  
    public int aumentarVidasOMunicion(){
        Random rnd = new Random();
        return rnd.nextInt(10);
    }
}
