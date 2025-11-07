/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author ljmc2
 */
public class AdminGUI extends JFrame {
    private AdminPalabrasSecretas admin;
    private DefaultListModel<String> modeloLista;
    private JList<String> lista;
    private JTextField txtPalabra;
    private JButton btnAgregar, btnVolver;
    private JFrame menuPrincipal;

    public AdminGUI(JFrame menu, AdminPalabrasSecretas adminCompartido) {
        this.menuPrincipal = menu;
        this.admin = adminCompartido;

        setTitle("Administrador de Palabras");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(lista);

        JPanel panelInferior = new JPanel(new FlowLayout());
        txtPalabra = new JTextField(10);
        btnAgregar = new JButton("Agregar");
        btnVolver = new JButton("Volver");

        btnAgregar.addActionListener(e -> {
            String palabra = txtPalabra.getText().trim().toLowerCase();
            if (palabra.matches("[a-zA-Z]+")) {
                try {
                    admin.agregarPalabra(palabra);
                    actualizarLista();
                    txtPalabra.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Solo letras válidas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            menuPrincipal.setVisible(true);
            dispose();
        });

        panelInferior.add(new JLabel("Nueva palabra:"));
        panelInferior.add(txtPalabra);
        panelInferior.add(btnAgregar);
        panelInferior.add(btnVolver);

        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        actualizarLista();
    }

    private void actualizarLista() {
        modeloLista.clear();
        List<String> palabras = admin.getPalabras();
        for (String p : palabras) {
            modeloLista.addElement(p);
        }
    }
}

