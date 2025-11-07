/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ljmc2
 */
public class MenuPrincipal extends JFrame{
    private JButton btnAdmin, btnFijo, btnAzar;
    private AdminPalabrasSecretas admin;

    public MenuPrincipal(AdminPalabrasSecretas admin) {
        this.admin=admin;
        
        setTitle("Menú Principal - Ahorcado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        btnAdmin = new JButton("Administrar Palabras");
        btnFijo = new JButton("Jugar - Palabra Fija");
        btnAzar = new JButton("Jugar - Palabra al Azar");

        btnAdmin.addActionListener(e -> {
            new AdminGUI(this).setVisible(true);
            setVisible(false);
        });

        btnFijo.addActionListener(e -> {
            
            JuegoAhorcadoFijo juegoFijo = new JuegoAhorcadoFijo("ejemplo");
            juegoFijo.inicializarPalabraSecreta();
            
        });

        btnAzar.addActionListener(e -> {
            
            JuegoAhorcadoAzar juegoAzar = new JuegoAhorcadoAzar(admin);
            juegoAzar.inicializarPalabraSecreta();
            new JuegoAhorcadoGUI(this, new JuegoAhorcadoAzar(admin)).setVisible(true);
            setVisible(false);
            
        });

        add(btnAdmin);
        add(btnFijo);
        add(btnAzar);
    }
}
