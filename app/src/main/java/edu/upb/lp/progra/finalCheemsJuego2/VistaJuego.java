package edu.upb.lp.progra.finalCheemsJuego2;

import edu.upb.lp.progra.finalCheemsJuego.NoTienesBalasException;

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

    public void cambiarCorzones(int vida, int nivel){
        if (vida <= 0) {
            conector.setImageOnCell(0,18,"imagenunsolocora");
        } else if (vida< 33) {
            conector.setImageOnCell(0,19,"imagenunsolocora");
        } else if (vida< 66){
            conector.setImageOnCell(0,20,"imagenunsolocora");
        } else if (vida <= 100){
            conector.setImageOnCell(0,20,"pantalladeljuego"+ nivel +0+"_"+20);
            conector.setImageOnCell(0,19,"pantalladeljuego"+ nivel +0+"_"+19);
            conector.setImageOnCell(0,18,"pantalladeljuego"+ nivel +0+"_"+18);
        }
    }
    public void actualizarMunicion(int balas){
        int decimales = balas % 100;
        decimales /= 10;
        int unidades = balas % 10;
        if (balas > 0 && balas < 100) {
            conector.setImageOnCell(0, 0, "n0");
            conector.setImageOnCell(0, 1, "n" + decimales);
            conector.setImageOnCell(0, 2, "n" + unidades);
        } else if (balas >= 100) {
            conector.setImageOnCell(0, 0, "n1");
            conector.setImageOnCell(0, 1, "n0");
            conector.setImageOnCell(0, 2, "n0");
        }
    }
}
