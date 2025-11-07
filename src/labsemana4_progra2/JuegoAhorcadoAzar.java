/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

/**
 *
 * @author ljmc2
 */
public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    private AdminPalabrasSecretas admin;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas admin) {
        this.admin = admin;
        inicializarPalabraSecreta();
    }

    @Override
    public void inicializarPalabraSecreta() {
        this.palabraSecreta = admin.obtenerPalabraAleatoria().toLowerCase();
        this.palabraActual = "_".repeat(palabraSecreta.length());
        this.intentos = 0;
    }

    @Override
    protected void actualizarPalabraActual(char letra) {
        String palabra = "";

        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {//cambiar letra si es acierto
                palabra += letra;
            } else {
                palabra += palabraActual.charAt(i);
            }
        }

        palabraActual = palabra;
    }

    @Override
    protected boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    @Override
    protected boolean hasGanado() {
        return palabraActual.equals(palabraSecreta);
    }
}

