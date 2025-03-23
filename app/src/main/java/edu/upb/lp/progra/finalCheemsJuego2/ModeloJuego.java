package edu.upb.lp.progra.finalCheemsJuego2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModeloJuego {
    // Atributos
    private Heroe heroe;
    private List<Enemigo> enemigos = new ArrayList<>();
    private LinkedList<Balas> listabalas = new LinkedList<>();
//    private List<Item> items;
    private boolean[][] paredesYobstaculos;
    private int nivelActual;
    private int vidas;
    private int puntuacion;
    private FabricaEnemigos fabricaEnemigos;
    // Constructor
    public ModeloJuego() {
//        this.personaje = new Personaje();
//        this.enemigos = new ArrayList<>();
//        this.balas = new ArrayList<>();
//        this.items = new ArrayList<>();
        this.nivelActual = 1;
        this.vidas = 3;
        this.puntuacion = 0;
    }

    // Métodos

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
        //inicializarEnemigos();
        // Inicializar enemigos, ítems, trampas, etc., según el nivel
//        inicializarEnemigos();
//        inicializarItems();
        // Notificar al Mediator que el nivel ha sido cargado
        //mediator.notificar("nivelCargado", nivel);
    }
    public void inicializarEnemigos(Enemigo enemigo) {
        enemigos.add(enemigo); // Se usa la fábrica
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
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
        if (!haycolsionPared(posicion,heroe.getPosicionY(),heroe.getPosicionX())) {
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
    /*private void verificarColisiones() {
        for (Bala bala : balas) {
            for (Enemigo enemigo : enemigos) {
                if (bala.getPosicionY() == enemigo.getPosicionY() &&
                        bala.getPosicionX() == enemigo.getPosicionX()) {
                    // Colisión detectada: la bala impacta al enemigo
                    enemigo.recibirDanio(bala.getDanio());
                    balas.remove(bala);
                    // Notificar al Mediator sobre la colisión
                    mediator.notificar("colisionBalaEnemigo", new Object[]{bala, enemigo});
                    break;
                }
            }
        }
    }*/

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
    /*private void inicializarEnemigos() {
        // Lógica para crear enemigos según el nivel
        enemigos.clear();
        if (nivelActual == 1) {
            enemigos.add(new Enemigo(5, 10)); // Ejemplo: enemigo en la posición (5, 10)
        } else if (nivelActual == 2) {
            enemigos.add(new Enemigo(3, 15));
            enemigos.add(new Enemigo(7, 5));
        }
    }*/

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