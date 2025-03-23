package edu.upb.lp.progra.finalCheemsJuego;

public class Heroe extends Personaje {

    public Heroe(int posicionV, int posicionH, FinalCheemsGame game, String nombre, String tipoDeBala){
        super(posicionV, posicionH, game, nombre, tipoDeBala);
    }

    public void yoistic(int vertical, int horizontal) {
        if (vertical == 7 && horizontal == 1) {
            setDireccion("arriba");
            moverseArriba();
        } else if (vertical == 9 && horizontal == 1) {
            setDireccion("abajo");
            moverseAbajo();
        } else if (vertical == 8 && horizontal == 0) {
            setDireccion("izquierda");
            moverseIzquierda();
        } else if (vertical == 8 && horizontal == 2) {
            setDireccion("derecha");
            moverseDerecha();
        } else if (vertical > 6 && vertical < 10 && horizontal > 17 && horizontal < 21) {
            dispararLasBalas();
            contadorBala();
        }
        getGame().nuevoNivel();
        pasarAlSiguienteNivel();
    }

    public void contadorBala() { //100 balas
        int decimales = getCantidadDeBalas() % 100;
        decimales /= 10;
        int unidades = getCantidadDeBalas() % 10;
        try {
            if (getCantidadDeBalas() > 0 && getCantidadDeBalas() < 100) {
                getGame().setImageOnCell(0, 0, "n0");
                getGame().setImageOnCell(0, 1, "n" + decimales);
                getGame().setImageOnCell(0, 2, "n" + unidades);
            } else if (getCantidadDeBalas() >= 100) {
                getGame().setImageOnCell(0, 0, "n1");
                getGame().setImageOnCell(0, 1, "n0");
                getGame().setImageOnCell(0, 2, "n0");
            } else {
                throw new NoTienesBalasException("no tienes suficientes balas");
            }
        } catch (NoTienesBalasException e) {
            getGame().showTemporaryMessage("No tienes balas");
        }
    }

    @Override
    public void morir() {
        if (getVida() <= 0) {
            getGame().acabarElJuego();
        }
    }

    @Override
    public void recibirDanio(int danio) {
        super.recibirDanio(danio);
        borrarVida();
        if (getVida() < 0) {
            morir();
        }
    }
    public void borrarVida () {
        if (getVida() <= 0) {
            getGame().setImageOnCell(0,20,"imagenunsolocora");
            getGame().setImageOnCell(0,19,"imagenunsolocora");
            getGame().setImageOnCell(0,18,"imagenunsolocora");
        } else if (getVida()< 33) {
            getGame().setImageOnCell(0,20,"imagenunsolocora");
            getGame().setImageOnCell(0,19,"imagenunsolocora");
            getGame().setImageOnCell(0,18,"pantalladeljuego"+ getGame().getNivel()+0+"_"+18);
        } else if (getVida()< 66){
            getGame().setImageOnCell(0,20,"imagenunsolocora");
            getGame().setImageOnCell(0,19,"pantalladeljuego"+ getGame().getNivel() + 0+"_"+19);
            getGame().setImageOnCell(0,18,"pantalladeljuego"+  getGame().getNivel() +0+"_"+18);
        } else if (getVida() <= 100){
            getGame().setImageOnCell(0,20,"pantalladeljuego"+ getGame().getNivel() +0+"_"+20);
            getGame().setImageOnCell(0,19,"pantalladeljuego"+ getGame().getNivel() +0+"_"+19);
            getGame().setImageOnCell(0,18,"pantalladeljuego"+ getGame().getNivel() +0+"_"+18);
        }
    }

    @Override
    public void aumentarVidas(int masVidas) {
        super.aumentarVidas(masVidas);
        borrarVida();
    }

    @Override
    public void aumentarBalas(int masBalas) {
        super.aumentarBalas(masBalas);
        contadorBala();
    }
    public void pasarAlSiguienteNivel(){
        if (getPosicionV() > 3 && getPosicionV() < 6 && getPosicionH() == 20){
            getGame().setPasarAlSiguienteNivel(true);
            setPosicionV(5);
            setPosicionH(1);
        }
    }
}

