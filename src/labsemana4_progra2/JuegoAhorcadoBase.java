/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

/**
 *
 * @author ljmc2
 */
import java.util.ArrayList;
import java.util.Scanner;
import labsemana4_progra2.Exceptions.*;

public abstract class JuegoAhorcadoBase implements Ahorcable {
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;

    public JuegoAhorcadoBase() {
        this.letrasUsadas = new ArrayList<>();
        this.figuraAhorcado = imprimirFigura();
    }

    protected ArrayList<String> imprimirFigura() {
        ArrayList<String> figura = new ArrayList<>();
        figura.add("");
        figura.add(" O ");
        figura.add(" O\n | ");
        figura.add(" O\n/| ");
        figura.add(" O\n/|\\");
        figura.add(" O\n/|\\\n/ ");
        figura.add(" O\n/|\\\n/ \\");//6 actualizaciones, para la séptima iteración se pierde
        return figura;
    }

    protected abstract void actualizarPalabraActual(char letra);
    protected abstract boolean verificarLetra(char letra);
    protected abstract boolean hasGanado();

    protected void mostrarEstado() {
        System.out.println("\nPalabra: " + palabraActual);
        System.out.println("Letras usadas: " + letrasUsadas);
        System.out.println("Intentos restantes: " + (limiteIntentos - intentos));
        System.out.println("Ahorcado:\n" + figuraAhorcado.get(intentos));
    }

    protected void validarLetra(char letra) throws LetraRepetidaException, LetraInvalidaException {
        if (!Character.isLetter(letra))
            throw new LetraInvalidaException("Solo se permiten letras del alfabeto.");
        if (letrasUsadas.contains(letra))
            throw new LetraRepetidaException("La letra '" + letra + "' ya fue usada.");
    }

    @Override
    public void jugar() {
        Scanner sc = new Scanner(System.in);
        while (intentos < limiteIntentos && !hasGanado()) {
            mostrarEstado();
            System.out.print("Ingrese una letra: ");
            String entrada = sc.nextLine().toLowerCase();

            if (entrada.isEmpty()) continue;
            char letra = entrada.charAt(0);

            try {
                validarLetra(letra);
                letrasUsadas.add(letra);

                if (verificarLetra(letra)) {
                    actualizarPalabraActual(letra);
                    System.out.println("¡Bien hecho! Letra correcta.");
                } else {
                    intentos++;
                    System.out.println("Letra incorrecta.");
                    if (intentos >= limiteIntentos)
                        throw new SinIntentosException("Has perdido. La palabra era: " + palabraSecreta);
                }

            } catch (LetraRepetidaException | LetraInvalidaException e) {
                System.out.println("⚠️ " + e.getMessage());
            } catch (SinIntentosException e) {
                mostrarEstado();
                System.out.println("💀 " + e.getMessage());
                return;
            }
        }

        if (hasGanado()) {
            mostrarEstado();
            System.out.println("🎉 ¡Felicidades! Has adivinado la palabra.");
        }
    }
}

