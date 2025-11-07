/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ljmc2
 */
public class AdminPalabrasSecretas {
    private ArrayList<String> palabras;

    public AdminPalabrasSecretas() {
        this.palabras = new ArrayList<>();
        palabras.add("soya");
        palabras.add("tofu");
        palabras.add("aguacate");
        palabras.add("enumeracion");
        palabras.add("recursividad");
    }

    public void agregarPalabra(String palabra) throws Exception {
        if (palabras.contains(palabra.toLowerCase()))
            throw new Exception("La palabra ya existe en la lista.");
        palabras.add(palabra.toLowerCase());
    }

    public String obtenerPalabraAleatoria() {
        Random rand = new Random();
        return palabras.get(rand.nextInt(palabras.size()));
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }
}

