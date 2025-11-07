/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import javax.swing.*;
import java.awt.*;
import labsemana4_progra2.Exceptions.*;

/**
 *
 * @author ljmc2
 */
public class JuegoAhorcadoGUI extends JFrame {
    private JuegoAhorcadoBase juego;
    private JTextField txtLetra;
    private JLabel lblPalabra, lblIntentos, lblMensaje, lblFigura;
    private JButton btnProbar, btnVolver;
    private JFrame menuPrincipal;

    public JuegoAhorcadoGUI(JFrame menu, JuegoAhorcadoBase juego) {
        this.juego = juego;
        this.menuPrincipal = menu;

        setTitle("Juego del Ahorcado");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel(new GridLayout(4, 1, 10, 10));
        lblPalabra = new JLabel(juego.getPalabraActual(), SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Monospaced", Font.BOLD, 24));

        lblIntentos = new JLabel("Intentos restantes: " + (juego.limiteIntentos - juego.intentos), SwingConstants.CENTER);
        lblMensaje = new JLabel("Ingrese una letra:", SwingConstants.CENTER);
        lblFigura = new JLabel("", SwingConstants.CENTER);

        txtLetra = new JTextField(1);
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        btnProbar = new JButton("Probar");
        btnVolver = new JButton("Volver al Menú");

        JPanel panelInferior = new JPanel();
        panelInferior.add(txtLetra);
        panelInferior.add(btnProbar);
        panelInferior.add(btnVolver);

        btnProbar.addActionListener(e -> jugarTurno());
        btnVolver.addActionListener(e -> {
            menuPrincipal.setVisible(true);
            dispose();
        });

        panelCentral.add(lblPalabra);
        panelCentral.add(lblIntentos);
        panelCentral.add(lblMensaje);
        panelCentral.add(lblFigura);

        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void jugarTurno() {
        if (txtLetra.getText().isEmpty()) return;
        char letra = txtLetra.getText().toLowerCase().charAt(0);
        txtLetra.setText("");

        try {
            boolean acierto = juego.jugar(letra);
            lblMensaje.setText(acierto ? "¡Letra correcta!" : "Letra incorrecta.");
            lblPalabra.setText(juego.getPalabraActual());
            lblFigura.setText(juego.imprimirFigura(juego.intentos));

            if (juego.hasGanado()) {
                lblMensaje.setText("¡Ganaste! La palabra era: " + juego.getPalabraSecreta());
                desactivarJuego();
            }

        } catch (LetraRepetidaException | LetraInvalidaException ex) {
            lblMensaje.setText(ex.getMessage());
        } catch (SinIntentosException ex) {
            lblMensaje.setText(ex.getMessage());
            desactivarJuego();
        }
    }

    private void desactivarJuego() {
        txtLetra.setEnabled(false);
        btnProbar.setEnabled(false);
    }
}
