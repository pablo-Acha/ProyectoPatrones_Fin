package edu.upb.lp.progra.finalCheemsJuego;

import java.util.LinkedList;
import java.util.Queue;

public abstract class FinalCheemsGame {
    private FinalCheemsConector conector;
    private ReproductorDeHistoria historiaDeInicio = new ReproductorDeHistoria(this);
    private Queue<String> personajesASeleccionar = new LinkedList<>();
    private LinkedList<Personaje> listaDePersonajes = new LinkedList<>();
    private LinkedList<Balas> listaDeBalas = new LinkedList<>();
    private LinkedList<Items> listaDeitems = new LinkedList<>();
    private boolean[][] matrizDeTrampas = new boolean[10][21];
//    private Temporizador tiempo = new Temporizador(this);
    private boolean[][] matrizDeParedes = new boolean[10][21];
    private boolean personajeSelecionado = false;
    private boolean configurarPantalla = false;
    private boolean gameOver = false;
    private boolean pasarAlSiguienteNivel = false;
    private int nivel;
    private boolean reiniciar = false;
    private AcabarElJuego acabarElJuego = new AcabarElJuego(this);
    public FinalCheemsGame(FinalCheemsConector conector, int nivel) {
        this.conector = conector;
        this.nivel = nivel;
    }

    public void setReiniciar(boolean reiniciar) {
        this.reiniciar = reiniciar;
    }

    public boolean isReiniciar() {
        return reiniciar;
    }

    public boolean isPasarAlSiguienteNivel() {
        return pasarAlSiguienteNivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setPasarAlSiguienteNivel(boolean pasarAlSiguienteNivel) {
        this.pasarAlSiguienteNivel = pasarAlSiguienteNivel;
    }

    public LinkedList<Personaje> getListaDePersonajes() {
        return listaDePersonajes;
    }

    public AcabarElJuego getAcabarElJuego() {
        return acabarElJuego;
    }

    public boolean[][] getMatrizDeParedes() {
        return matrizDeParedes;
    }
    public boolean[][] getMatrizDeTrampas(){
        return matrizDeTrampas;
    }

    public LinkedList<Items> getListaDeitems() {
        return listaDeitems;
    }

    public ReproductorDeHistoria getHistoriaDeInicio() {
        return historiaDeInicio;
    }

    public FinalCheemsConector getConector() {
        return conector;
    }

    public LinkedList<Balas> getListaDeBalas() {
        return listaDeBalas;
    }

//    public Temporizador getTiempo() {
//        return tiempo;
//    }

    public boolean isConfigurarPantalla() {
        return configurarPantalla;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean getPersonajeSeleccionado () {
        return  personajeSelecionado;
    }

    public Queue<String> getPersonajesASeleccionar() {
        return personajesASeleccionar;
    }

    public boolean isPersonajeSelecionado() {
        return personajeSelecionado;
    }

    public void setConector(FinalCheemsConector conector) {
        this.conector = conector;
    }

    public void setHistoriaDeInicio(ReproductorDeHistoria historiaDeInicio) {
        this.historiaDeInicio = historiaDeInicio;
    }

    public void setPersonajesASeleccionar(Queue<String> personajesASeleccionar) {
        this.personajesASeleccionar = personajesASeleccionar;
    }

    public void setListaDePersonajes(LinkedList<Personaje> listaDePersonajes) {
        this.listaDePersonajes = listaDePersonajes;
    }

    public void setListaDeBalas(LinkedList<Balas> listaDeBalas) {
        this.listaDeBalas = listaDeBalas;
    }

    public void setListaDeitems(LinkedList<Items> listaDeitems) {
        this.listaDeitems = listaDeitems;
    }

    public void setMatrizDeTrampas(boolean[][] matrizDeTrampas) {
        this.matrizDeTrampas = matrizDeTrampas;
    }
    

    public void setMatrizDeParedes(boolean[][] matrizDeParedes) {
        this.matrizDeParedes = matrizDeParedes;
    }

    public void setPersonajeSelecionado(boolean personajeSelecionado) {
        this.personajeSelecionado = personajeSelecionado;
    }

    public void setConfigurarPantalla(boolean configurarPantalla) {
        this.configurarPantalla = configurarPantalla;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void executeLater(Runnable run, int ms){
        conector.executeLater(run, ms);
    }
    public void setImageOnCell(int vertical, int horizontal, String image){
        conector.setImageOnCell(vertical,horizontal,image);
    }
    public  void click(int vertical, int horizontal){
        if (!gameOver) {
            ((Heroe)listaDePersonajes.get(0)).yoistic(vertical, horizontal);
        } else {
            if(acabarElJuego.isPoderReinciar()){
                reiniciar = true;
            }
        }
    }

     public void showTemporaryMessage(String mensaje){
        conector.showTemporaryMessage(mensaje);
    }

    public void creaUnItem(int posicionV, int posicionH, String item) {
        listaDeitems.add(new Items(item,posicionV,posicionH));
        setImageOnCell(listaDeitems.getLast().getPosicionV(), listaDeitems.getLast().getPosicionH(), listaDeitems.getLast().getTipoDeItem());
    }
    public abstract void crearElmapa();

    public abstract void crearEnemigos();

    /**
     * metodo para llenar la matrizParedes con las paredes del juego
     */
    public void crearParedesYBotones(){
        for (int i = 0; i < 20; i++) {
            matrizDeParedes[0][i] = true;
            matrizDeParedes[9][i] = true;
        }
        for (int i = 0; i < 7; i++) {
            matrizDeParedes[i][0] = true;
            matrizDeParedes[i][20] = true;
        }

        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                matrizDeParedes[i][j] = true;
            }
            for (int j = 17; j < 21; j++) {
                matrizDeParedes[i][j] = true;
            }
        }
    }

    public abstract void crearObstaculosDelMundo();
    public abstract void crearTrampasDelMundo();

    public void aparecerTodosLosObjetos(){
        for (int i = 0; i < listaDePersonajes.size(); i++) {
            Personaje obj = listaDePersonajes.get(i);
            setImageOnCell(obj.getPosicionV(),obj.getPosicionH(),obj.getNombre());
        }
    }
    public boolean verSiHayUnaBalaAdelante(int verticalAdelante, int horizontalAdelante){
        boolean hayUnaBala = false;
        int i = 0;
        while (!hayUnaBala && i < listaDeBalas.size()){
            Balas bala = listaDeBalas.get(i);
            if (bala.getPosicionV() == verticalAdelante && bala.getPosicionH() == horizontalAdelante){
                hayUnaBala = true;
            } else {
                i++;
            }
        }
        return hayUnaBala;
    }
    public boolean verSihayUnPersonajeAdelante(int verticalAdelante, int horizontalAdelante){
        boolean hayUnPersonaje = false;
        int i = 0;
        while (!hayUnPersonaje && i < listaDePersonajes.size()){
            Personaje personaje = listaDePersonajes.get(i);
            if (personaje.getPosicionV() == verticalAdelante && personaje.getPosicionH() == horizontalAdelante){
                hayUnPersonaje = true;
            } else {
                i++;
            }
        }
        return hayUnPersonaje;
    }
    public void impacto(int vDelPersonaje, int hDelPersonaje, int vDeLaBala, int hDeLaBala){
        int indiceDeLaBala = 0;
        int indiceDelPersonaje = 0;
        boolean siHayBala = false;
        boolean siHayPersonaje = false;
        while (!siHayBala && indiceDeLaBala < listaDeBalas.size()){
            Balas bala = listaDeBalas.get(indiceDeLaBala);
            if (bala.getPosicionV() == vDeLaBala && bala.getPosicionH() == hDeLaBala){
                siHayBala = true;
            } else {
                indiceDeLaBala++;
            }
        }
        while (!siHayPersonaje && indiceDelPersonaje < listaDePersonajes.size()){
            Personaje personaje = listaDePersonajes.get(indiceDelPersonaje);
            if (personaje.getPosicionV() == vDelPersonaje && personaje.getPosicionH() == hDelPersonaje){
                siHayPersonaje = true;
            } else {
                indiceDelPersonaje++;
            }
        }
        if (siHayBala && siHayPersonaje){
            listaDePersonajes.get(indiceDelPersonaje).recibirDanio(listaDeBalas.get(indiceDeLaBala).getDanio());
            listaDeBalas.get(indiceDeLaBala).setStop(true);
//            listaDeBalas.remove(indiceDeLaBala);
        }
    }
    public void recogerItem(int posicionV, int posicionH){
        int indiceDelPersonaje = 0;
        int indiceDelItem = 0;
        boolean personajeRecogeItem = false;
        for (int i = 0; i < listaDeitems.size(); i++) {
            if (listaDeitems.get(i).getPosicionV() == posicionV && listaDeitems.get(i).getPosicionH() == posicionH){
                indiceDelItem = i;
                personajeRecogeItem = true;
            }
        }
        for (int i = 0; i < listaDePersonajes.size(); i++) {
            if (listaDePersonajes.get(i).getPosicionV() == posicionV && listaDePersonajes.get(i).getPosicionH() == posicionH){
                indiceDelPersonaje = i;
            }
        }
        if (personajeRecogeItem){
            Items item = listaDeitems.get(indiceDelItem);
            if (item.getTipoDeItem().equals("vidasitem"+nivel)){
                listaDePersonajes.get(indiceDelPersonaje).aumentarVidas(item.aumentarVidasOMunicion());
            } else if (item.getTipoDeItem().equals("balasitem"+ nivel)){
                listaDePersonajes.get(indiceDelPersonaje).aumentarBalas(item.aumentarVidasOMunicion());
            }
//            setImageOneCell(item.getPosicionV(), item.getPosicionH(),"pantalladeljuego"+item.getPosicionV()+"_"+item.getPosicionH());
            listaDeitems.remove(indiceDelItem);
        }
    }
    public void llenarQueue(){
        personajesASeleccionar.offer("batman");
        personajesASeleccionar.offer("deadpool");
        personajesASeleccionar.offer("donatelo");
//        personajesASeleccionar.offer("sombra");
    }

    public void iniciarElNivel(){
//        executeLater(getTiempo(), 0);
        crearElmapa();
        crearParedesYBotones();
        crearObstaculosDelMundo();
        crearTrampasDelMundo();
        crearEnemigos();
        aparecerTodosLosObjetos();
    }
    public void borrarBalas(){
        for (int i = 0; i < listaDeBalas.size(); i++) {
            if (listaDeBalas.get(i).isStop()){
                listaDeBalas.remove(listaDeBalas.get(i));
            }
        }
    }
    public void dispararBala(int posicionV, int posicionH, String direccion, String tipoDeBala){
        int arriba = posicionV-1;
        int abajo = posicionV+1;
        int derecha = posicionH+1;
        int izquierda = posicionH-1;
        Balas bala = null;
        if (!matrizDeParedes[arriba][posicionH] && direccion.equals("arriba")){
             bala = new Balas(arriba,posicionH,this,tipoDeBala,20);
        } else if (!matrizDeParedes[abajo][posicionH] && direccion.equals("abajo")){
             bala = new Balas(abajo,posicionH,this,tipoDeBala,20);
        } else if (!matrizDeParedes[posicionV][derecha]&& direccion.equals("derecha")) {
             bala = new Balas(posicionV,derecha,this,tipoDeBala,20);
        } else if (!matrizDeParedes[posicionV][izquierda]&& direccion.equals("izquierda")) {
             bala = new Balas(posicionV,izquierda,this,tipoDeBala,20);
        }
        if (bala != null){
            boolean aniadirAlaListaDeBalas = true;
            for (int i = 0; i < listaDePersonajes.size(); i++) {
                if (listaDePersonajes.get(i ).getPosicionV() == bala.getPosicionV() && listaDePersonajes.get(i).getPosicionH() == bala.getPosicionH()){
                    listaDePersonajes.get(i).recibirDanio(bala.getDanio());
                    aniadirAlaListaDeBalas = false;
                }
            }
            if (aniadirAlaListaDeBalas){
                listaDeBalas.add(bala);
                Balas balas = listaDeBalas.getLast();
                listaDeBalas.getLast().setDireccion(direccion);
                setImageOnCell(balas.getPosicionV(),balas.getPosicionH(),balas.getNombre()+getNivel()+balas.getDireccion());
                executeLater(listaDeBalas.getLast(),100);
            }
        }
    }
    public void borrarBalasYEnemigos(){
        for (int i = 0; i < listaDeBalas.size(); i++) {
            if (listaDeBalas.get(i).isStop()){
                listaDeBalas.remove(listaDeBalas.get(i));
            }
        }
        for (int i = 0; i < listaDePersonajes.size(); i++) {
            Personaje personaje = listaDePersonajes.get(i);
            if (personaje.estaMuerto()){
                setImageOnCell(personaje.getPosicionV(), personaje.getPosicionH(),"pantalladeljuego"+nivel+personaje.getPosicionV()+"_"+personaje.getPosicionH());
                personaje.morir();
                listaDePersonajes.remove(i);
            }
        }
    }
    public void acabarElJuego() {
        for (int i = 0; i < listaDeBalas.size(); i++) {
            listaDeBalas.get(i).setStop(true);
        }
        borrarBalas();
        listaDePersonajes.remove(0);
        if (listaDePersonajes.size() > 0){
            for (int i = 0; i < listaDePersonajes.size(); i++) {
                listaDePersonajes.get(i).recibirDanio(1000);
            }
        }
        borrarEnemigo();
        if (listaDeitems.size() > 0){
            int tamanioDeLaLista = listaDeitems.size();
            for (int i = 0; i < tamanioDeLaLista; i++) {
                setImageOnCell(listaDeitems.get(0).getPosicionV(), listaDeitems.get(0).getPosicionH(),"pantalladeljuego"+nivel
                        +listaDeitems.get(0).getPosicionV()+"_"+listaDeitems.get(0).getPosicionH());
                listaDeitems.remove(0);
            }
        }
        executeLater(acabarElJuego,2000);
        gameOver = true;

    }
    public void nuevoNivel(){
        if (listaDePersonajes.size() == 1 ){
            matrizDeParedes[4][20] = false;
            matrizDeParedes[5][20] = false;
        }
    }
    public void borrarEnemigo(){
        for (int i = 0; i < listaDePersonajes.size(); i++) {
            Personaje personaje = listaDePersonajes.get(i);
            if (personaje.estaMuerto()){
                setImageOnCell(personaje.getPosicionV(), personaje.getPosicionH(),"pantalladeljuego"+nivel+personaje.getPosicionV()+"_"+personaje.getPosicionH());
                personaje.morir();
                listaDePersonajes.remove(i);
            }
        }
    }
    public void pasarmeUnHeroe(Personaje heroe){
        Heroe heroe1 = new Heroe(heroe.getPosicionV(), heroe.getPosicionH(),this, heroe.getNombre(),heroe.getTipoDeBala());
        heroe1.setVida(heroe.getVida());
        heroe1.setCantidadDeBalas(heroe.getCantidadDeBalas());
        listaDePersonajes.addFirst(heroe1);
    }
}
