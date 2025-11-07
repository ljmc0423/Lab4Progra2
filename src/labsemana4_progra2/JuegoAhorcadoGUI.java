/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import labsemana4_progra2.Exceptions.*;

/**
 *
 * @author ljmc2
 */
public class JuegoAhorcadoGUI extends JFrame {
    private JuegoAhorcadoBase juego;
    private JTextField txtLetra;
    private JLabel lblPalabra, lblIntentos, lblMensaje;
    private JTextArea lblFigura;
    private JButton btnProbar, btnVolver;
    private JFrame menuPrincipal;

    public JuegoAhorcadoGUI(JFrame menu, JuegoAhorcadoBase juego) {
        this.juego = juego;
        this.menuPrincipal = menu;

        setTitle("Juego del Ahorcado");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;

        lblPalabra = new JLabel(juego.getPalabraActual(), SwingConstants.CENTER);
        lblPalabra.setFont(new Font("Monospaced", Font.BOLD, 24));
        gbc.gridy = 0;
        panelCentral.add(lblPalabra, gbc);

        lblIntentos = new JLabel("Intentos restantes: " + (juego.limiteIntentos - juego.intentos), SwingConstants.CENTER);
        gbc.gridy = 1;
        panelCentral.add(lblIntentos, gbc);

        lblMensaje = new JLabel("Ingrese una letra:", SwingConstants.CENTER);
        gbc.gridy = 2;
        panelCentral.add(lblMensaje, gbc);

        lblFigura = new JTextArea();
        lblFigura.setEditable(false);
        lblFigura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        lblFigura.setBackground(getBackground());
        lblFigura.setFocusable(false);
        lblFigura.setMargin(new Insets(10, 10, 10, 40));
        lblFigura.setPreferredSize(new Dimension(200, 150));

        gbc.gridy = 3;
        panelCentral.add(lblFigura, gbc);

        txtLetra = new JTextField(1);
        txtLetra.setHorizontalAlignment(JTextField.CENTER);

        ((AbstractDocument) txtLetra.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text == null) return;
                if (fb.getDocument().getLength() + text.length() - length > 1) return;
                if (!text.matches("[a-zA-Z]")) return;
                super.replace(fb, offset, length, text, attrs);
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                if (text == null) return;
                if (fb.getDocument().getLength() + text.length() > 1) return;
                if (!text.matches("[a-zA-Z]")) return;
                super.insertString(fb, offset, text, attr);
            }
        });

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
            lblIntentos.setText("Intentos restantes: " + (juego.limiteIntentos - juego.intentos));

            if (juego.hasGanado()) {
                lblMensaje.setText("¡Ganaste! La palabra era: " + juego.getPalabraSecreta());
                desactivarJuego();
            }

        } catch (LetraRepetidaException | LetraInvalidaException ex) {
            lblMensaje.setText(ex.getMessage());
        } catch (SinIntentosException ex) {
            lblMensaje.setText(ex.getMessage());
            lblFigura.setText(juego.imprimirFigura(juego.limiteIntentos));
            desactivarJuego();
        }
    }

    private void desactivarJuego() {
        txtLetra.setEnabled(false);
        btnProbar.setEnabled(false);
    }
}
