package edu.upb.lp.progra.finalCheemsJuego2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class PreferenciasJuego {
    private static final String ARCHIVO_PREFERENCIAS = "preferencias_juego.properties";
    private Properties propiedades;

    public PreferenciasJuego() {
        propiedades = new Properties();
        cargarPreferencias();
    }

    // Cargar preferencias desde el archivo
    private void cargarPreferencias() {
        try (FileInputStream input = new FileInputStream(ARCHIVO_PREFERENCIAS)) {
            propiedades.load(input);
        } catch (IOException e) {
            // Si el archivo no existe, se creará al guardar las preferencias
        }
    }

    // Guardar preferencias en el archivo
    public void guardarPreferencias() {
        try (FileOutputStream output = new FileOutputStream(ARCHIVO_PREFERENCIAS)) {
            propiedades.store(output, "Preferencias del Juego");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para obtener y establecer valores
    public String obtenerString(String clave, String valorPorDefecto) {
        return propiedades.getProperty(clave, valorPorDefecto);
    }

    public void establecerString(String clave, String valor) {
        propiedades.setProperty(clave, valor);
    }

    public int obtenerInt(String clave, int valorPorDefecto) {
        return Integer.parseInt(propiedades.getProperty(clave, String.valueOf(valorPorDefecto)));
    }

    public void establecerInt(String clave, int valor) {
        propiedades.setProperty(clave, String.valueOf(valor));
    }

    public boolean obtenerBoolean(String clave, boolean valorPorDefecto) {
        return Boolean.parseBoolean(propiedades.getProperty(clave, String.valueOf(valorPorDefecto)));
    }

    public void establecerBoolean(String clave, boolean valor) {
        propiedades.setProperty(clave, String.valueOf(valor));
    }
}
