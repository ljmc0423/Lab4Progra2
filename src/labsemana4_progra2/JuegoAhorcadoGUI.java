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
            juego.validarLetra(letra);
            juego.letrasUsadas.add(letra);

            if (juego.verificarLetra(letra)) {
                juego.actualizarPalabraActual(letra);
                lblMensaje.setText("¡Letra correcta!");
            } else {
                juego.intentos++;
                lblMensaje.setText("Letra incorrecta.");
            }

            actualizarVista();

            if (juego.hasGanado()) {
                lblMensaje.setText("🎉 ¡Ganaste! La palabra era: " + juego.palabraSecreta);
                desactivarJuego();
            } else if (juego.intentos >= juego.limiteIntentos) {
                throw new SinIntentosException("💀 Has perdido. La palabra era: " + juego.palabraSecreta);
            }

        } catch (LetraInvalidaException | LetraRepetidaException ex) {
            lblMensaje.setText("⚠️ " + ex.getMessage());
        } catch (SinIntentosException ex) {
            lblMensaje.setText(ex.getMessage());
            desactivarJuego();
        }
    }

    private void actualizarVista() {
        lblPalabra.setText(juego.getPalabraActual());
        lblIntentos.setText("Intentos restantes: " + (juego.limiteIntentos - juego.intentos));
        lblFigura.setText(juego.imprimirFigura());
    }

    private void desactivarJuego() {
        txtLetra.setEnabled(false);
        btnProbar.setEnabled(false);
    }
}
