package edu.upb.lp.progra.finalCheemsJuego;

import java.util.LinkedList;
import java.util.Random;

public class FinalCheemsGame3 extends FinalCheemsGame{
    private Temporizador aparecedorDeTrampas = new Temporizador(this);
    public FinalCheemsGame3(FinalCheemsConector conector) {
        super(conector, 3);
    }

    @Override
    public void click(int vertical, int horizontal) {
        super.click(vertical, horizontal);
        
    }

    @Override
    public void crearElmapa() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 21; j++) {
                setImageOnCell(i,j,"pantalladeljuego"+getNivel()+i+"_"+j);
            }
        }
        executeLater(aparecedorDeTrampas,3000);
    }

    @Override
    public void crearEnemigos() {
        Personaje heroe = getListaDePersonajes().get(0);
        LinkedList<Personaje> personajes = new LinkedList<>();
//        int v = 5;
//        int h = 5;
//        int j = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                personajes.add(new FragmentoDeChihuhua(5+i, 5+j,this,"fragmentos"+i+"_"+j, "bala",false,false));
            }
        }
        personajes.remove(0);
        personajes.addFirst(new FragmentoDeChihuhua(5, 5,this,"fragmentos"+0+"_"+0, "bala",true,false));
        personajes.remove(15);
        personajes.addLast(new FragmentoDeChihuhua(5+3, 5+3,this,"fragmentos"+3+"_"+3, "bala",false,true));
        personajes.addFirst(heroe);
        setListaDePersonajes(personajes);
        for (int i = 1; i < 16; i++) {
            ((FragmentoDeChihuhua)personajes.get(i)).setSiguiente((FragmentoDeChihuhua)personajes.get(i+1));
        }
        executeLater((FragmentoDeChihuhua)getListaDePersonajes().get(1),0);
//        for (int i = 1; i < getListaDePersonajes().size(); i++) {
//            executeLater((Enemigos) getListaDePersonajes().get(i),0);
//        }
    }

    @Override
    public void crearObstaculosDelMundo() {

    }

    @Override
    public void crearTrampasDelMundo() {
        Random rnd  = new Random();
        boolean[][] trampas = new boolean[10][21];
        for (int i = 0; i < 15; i++) {
            int vertical = rnd.nextInt(8)+1;
            int horizontal = rnd.nextInt(19)+1;
            if (!getMatrizDeParedes()[vertical][horizontal]) {
                trampas[vertical][horizontal] = true;
            }
        }
        setMatrizDeTrampas(trampas);
//        executeLater(aparecedorDeTrampas,3000);
    }
    
    public void mostrarTrampas(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 21; j++) {
                if (getMatrizDeTrampas()[i][j]){
                    setImageOnCell(i,j,"trampas");
                }
            }
        }
    }
    public void borrarTrampas(){
        boolean [][] trampas = new boolean[10][21];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 21; j++) {
                if(getMatrizDeTrampas()[i][j]){
                    setImageOnCell(i,j,"pantalladeljuego3"+i+"_"+j);
                }
            }
        }
        setMatrizDeTrampas(trampas);
    }


    public void pararTemporizador() {
        aparecedorDeTrampas.setStop(true);
    }
    public void añadirALaLista(LinkedList<Personaje> chihuahua){
        for(int i = 0; i < chihuahua.size();i++){
            getListaDePersonajes().add(chihuahua.get(i));
        }
    }
}
