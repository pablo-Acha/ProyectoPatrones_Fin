package edu.upb.lp.progra.finalCheemsJuego2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModeloJuego {
    // Atributos
    private Heroe heroe;
    private LinkedList<Enemigo> listaEnemigos = new LinkedList<>();
    private LinkedList<Balas> listabalas = new LinkedList<>();
//    private List<Item> items;
    private boolean[][] paredesYobstaculos;
    private int nivelActual;
    private int vidas;
    private int puntuacion;
    private PreferenciasJuego preferencias;

    public void setConector(FinalCheemsConector conector) {
        this.conector = conector;
    }

    private FinalCheemsConector conector;
    // Constructor
    public ModeloJuego() {
//        this.personaje = new Personaje();
//        this.enemigos = new ArrayList<>();
//        this.balas = new ArrayList<>();
//        this.items = new ArrayList<>();
        this.nivelActual = 1;
        this.vidas = 3;
        this.puntuacion = 0;
        this.preferencias = new PreferenciasJuego();
    }

    // Métodos

    public Heroe getHeroe() {
        return heroe;
    }

    public void escogerHeroe(String nombre, MediatorObjetos mediator){
        heroe = new Heroe(1,5,"derecha",nombre+nivelActual,mediator);
    }


    /**
     * Carga los datos del nivel especificado.
     * @param nivel El nivel que se desea cargar.
     */
    public void cargarNivel(int nivel) {
        this.nivelActual = nivel;
        inicializarParedesyObstaculos();
        for (int i = 0; i < listaEnemigos.toArray().length-1; i++) {
            listaEnemigos.get(i).aparecer();
        }        //inicializarEnemigos();
        // Inicializar enemigos, ítems, trampas, etc., según el nivel
//        inicializarEnemigos();
//        inicializarItems();
        // Notificar al Mediator que el nivel ha sido cargado
        //mediator.notificar("nivelCargado", nivel);
    }
    public void inicializarEnemigos(Enemigo enemigo) {
        listaEnemigos.add(enemigo); // Se usa la fábrica
        conector.executeLater((EnemigoBase)enemigo, 0);
    }

    public LinkedList<Enemigo> getListaEnemigos() {
        return listaEnemigos;
    }
    public void accionPersonaje(String accion){
        if(accion.equals("disparar")){
            if(heroe.hasMunicion()){
                heroe.disparar();
            }
        }else if(!accion.equals("*")){
            moverPersonaje(accion);
        }
    }
    /**
     * Mueve al personaje a la posición especificada.
     * @param posicion La posición donde ira.
     */
    public void moverPersonaje(String posicion) {
        if (!haycolsionPared(posicion,heroe.getPosicionY(),heroe.getPosicionX())
            && !verificarColsionesHeroeEnemigo(heroe.getPosicionY(),heroe.getPosicionX(),posicion)) {
            heroe.mover(posicion);
        }
    }

    /**
     * Crea una nueva bala en la posición especificada.
     * @param posicionY La posición vertical.
     * @param posicionX La posición horizontal.
     */
    public void dispararBala(int posicionY, int posicionX,String direccion) {
        heroe.disparar();
    }

    public void addBala(Balas bala){
        listabalas.add(bala);
        listabalas.get(listabalas.toArray().length-1).disparBala();
    }
    /**
     * Verifica colisiones entre objetos (por ejemplo, balas con enemigos).
     */
    public boolean verificarColisionesBala(Balas bala) {
        for (Enemigo enemigo : listaEnemigos) {
            if(bala.getPosicionY() == enemigo.getPosicionY() &&
                    bala.getPosicionX() == enemigo.getPosicionX()){
                enemigo.recibirDanio(20);
                return true;
            }
        }
        if(bala.getPosicionY() == heroe.getPosicionY() &&
                bala.getPosicionX() == heroe.getPosicionX()){
            heroe.recibirDanio(20);
            return true;
        }
        return false;
    }

    public boolean verificarColsionesEnemigoHeroe(int y, int x, String direccion){
        if(direccion.equals("arriba")){
            y-=1;
        }else if(direccion.equals("abajo")){
            y+=1;
        }else if(direccion.equals("derecha")){
            x+=1;
        }else{
            x-=1;
        }
        return y == heroe.getPosicionY() && x == heroe.getPosicionX();
    }
    public boolean verificarColsionesHeroeEnemigo(int y, int x, String direccion) {
        // Calcular nueva posición
        if(direccion.equals("arriba")) {
            y -= 1;
        } else if(direccion.equals("abajo")) {
            y += 1;
        } else if(direccion.equals("derecha")) {
            x += 1;
        } else {
            x -= 1;
        }

        // Verificar colisión con cada enemigo
        for (Enemigo enemigo : listaEnemigos) {
            if(y == enemigo.getPosicionY() && x == enemigo.getPosicionX()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Recoge un ítem en la posición especificada.
     * @param posicionY La posición vertical.
     * @param posicionX La posición horizontal.
     */
    /*public void recogerItem(int posicionY, int posicionX) {
        for (Item item : items) {
            if (item.getPosicionY() == posicionY && item.getPosicionX() == posicionX) {
                // Aplicar el efecto del ítem (por ejemplo, aumentar vidas o munición)
                item.aplicarEfecto(personaje);
                items.remove(item);
                // Notificar al Mediator que se ha recogido un ítem
                mediator.notificar("itemRecogido", item);
                break;
            }
        }
    }*/

    /**
     * Inicializa los enemigos para el nivel actual.
     */

    /**
     * Inicializa los ítems para el nivel actual.
     */
    /*private void inicializarItems() {
        // Lógica para crear ítems según el nivel
        items.clear();
        if (nivelActual == 1) {
            items.add(new Item(2, 8, "vida")); // Ejemplo: ítem de vida en la posición (2, 8)
        } else if (nivelActual == 2) {
            items.add(new Item(4, 12, "municion"));
            items.add(new Item(6, 18, "vida"));
        }
    }*/

    private void inicializarParedesyObstaculos(){
        boolean[][] matrizDeParedes = new boolean[10][21];
        for (int i = 0; i < 20; i++) { matrizDeParedes[0][i] = true; matrizDeParedes[9][i] = true;}
        for (int i = 0; i < 7; i++) { matrizDeParedes[i][0] = true; matrizDeParedes[i][20] = true; }
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 4; j++) {matrizDeParedes[i][j] = true; }
            for (int j = 17; j < 21; j++) {matrizDeParedes[i][j] = true; }
        }

        if(nivelActual == 1){
            heroe.aparecer();
            matrizDeParedes[2][7] = true;
            matrizDeParedes[5][13] = true;
            matrizDeParedes[5][14] = true;
            matrizDeParedes[5][15] = true;
            matrizDeParedes[4][8] = true;
            matrizDeParedes[4][9] = true;
            matrizDeParedes[5][8] = true;
            matrizDeParedes[5][9] = true;
        }else if(nivelActual == 2){

        }
        paredesYobstaculos = matrizDeParedes;
    }

    public boolean haycolsionPared(String posicion, int y,int x){
        return posicion.equals("arriba") && paredesYobstaculos[y-1][x]
                || posicion.equals("abajo") && paredesYobstaculos[y+1][x]
                ||posicion.equals("derecha") && paredesYobstaculos[y][x+1]
                ||posicion.equals("izquierda") && paredesYobstaculos[y][x-1];

    }
    //public void borrarEnemigo(){
      //  for (int i = 0; i < listaEnemigos.size(); i++) {
        //    EnemigoBase enemigo = (EnemigoBase) listaEnemigos.get(i);
          //  if (enemigo.estaMuerto()){
            //    conector.getLibrary().setImageOnCell(enemigo.getPosicionY(), enemigo.getPosicionX(),"pantalladeljuego"+nivel+enemigo.getPosicionY()+"_"+enemigo.getPosicionX());
              //  enemigo.morir();
                //listaEnemigos.remove(i);
            //}
        //}
    //}
    // Getters y Setters
//    public Personaje getPersonaje() {
//        return personaje;
//    }
//
//    public List<Enemigo> getEnemigos() {
//        return enemigos;
//    }
//
//    public List<Bala> getBalas() {
//        return balas;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }

    public int getNivelActual() {
        return nivelActual;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}