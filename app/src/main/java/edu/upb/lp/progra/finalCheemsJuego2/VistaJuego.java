package edu.upb.lp.progra.finalCheemsJuego2;

public class VistaJuego {
    private FinalCheemsConector conector;

    public VistaJuego(FinalCheemsConector conector) {
        this.conector = conector;
    }

    /**
     * Carga las imágenes del nivel especificado.
     * @param nivel El nivel que se desea cargar.
     */
    public void cargarNivel(int nivel) {
        conector.configureScreen(10, 21);
        for (int y = 0; y < 10; y++) { // Suponiendo una matriz de 10 filas
            for (int x = 0; x < 21; x++) { // Suponiendo una matriz de 21 columnas
                String nombreImagen = "pantalladeljuego" + nivel + y + "_" + x;
                conector.setImageOnCell(y, x, nombreImagen);
            }
        }
    }

    /**
     * Actualiza una celda específica de la matriz con una imagen.
     * @param posicionY La posición vertical de la celda.
     * @param posicionX La posición horizontal de la celda.
     * @param imagen El nombre de la imagen a mostrar.
     */
    public void actualizarCelda(int posicionY, int posicionX, String imagen) {
        conector.setImageOnCell(posicionY, posicionX, imagen);
    }

    public void mostrarPantallaInicial() {
        conector.setImageOnCell(0,0,"pantalladeinicio");
    }

    public void mostrarMenuPersonajes() {
        conector.configureScreen(1, 2);
        for (int i = 0; i < 2; i++) {
            actualizarCelda(0, i, "batman0_" + i);
        }
    }
    public void cambiarPersonaje(String nombre){
        actualizarCelda(0, 0, nombre+"0_0" );
    }
}
