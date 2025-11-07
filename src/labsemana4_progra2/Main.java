/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labsemana4_progra2;

import javax.swing.SwingUtilities;

/**
 *
 * @author ljmc2
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdminPalabrasSecretas admin = new AdminPalabrasSecretas();

        JuegoAhorcadoFijo juegoFijo = new JuegoAhorcadoFijo("ejemplo");
        juegoFijo.inicializarPalabraSecreta();

        JuegoAhorcadoAzar juegoAzar = new JuegoAhorcadoAzar(admin);
        juegoAzar.inicializarPalabraSecreta();

        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal(admin).setVisible(true);
        });
    }
    
}
