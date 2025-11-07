/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import java.util.ArrayList;
import labsemana4_progra2.Exceptions.LetraRepetidaException;
import labsemana4_progra2.Exceptions.LetraInvalidaException;
import labsemana4_progra2.Exceptions.SinIntentosException;
/**
 *
 * @author ljmc2
 */
public abstract class JuegoAhorcadoBase implements Ahorcable {
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected final int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas;

    public JuegoAhorcadoBase() {
        this.letrasUsadas = new ArrayList<>();
        this.intentos = 0;
    }

    public String imprimirFigura(int intentos) {
        switch (intentos) {
            case 0: return " +---+"
                    +    "\n |   |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n=========";
            
            case 1: return " +---+\n |   |"
                    +    "\n O   |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n=========";
            
            case 2: return " +---+"
                    +    "\n |   |"
                    +    "\n O   |"
                    +    "\n |   |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n=========";
            
            case 3: return " +---+"
                    +    "\n |   |"
                    +    "\n O   |"
                    +    "\n/|   |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n=========";
            
            case 4: return " +---+"
                    +    "\n |   |"
                    +    "\n O   |"
                    +    "\n/|\\  |"
                    +    "\n     |"
                    +    "\n     |"
                    +    "\n=========";
            
            case 5: return " +---+"
                    +    "\n |   |"
                    +    "\n O   |"
                    +    "\n/|\\  |"
                    +    "\n/    |"
                    +    "\n     |"
                    +    "\n=========";
            
            case 6: return " +---+"
                    +    "\n |   |"
                    +    "\n O   |"
                    +    "\n/|\\  |"
                    +    "\n/ \\  |"
                    +    "\n     |"
                    +    "\n=========";
            
            default:return " +---+"
                    +    "\n |   |"
                    +    "\n X   |"
                    +    "\n/|\\  |"
                    +    "\n/ \\  |"
                    +    "\n     |"
                    +    "\n=========";
        }
    }

    public String getPalabraActual() {
        return palabraActual;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public void validarLetra(char letra) throws LetraInvalidaException, LetraRepetidaException {
        if (!Character.isLetter(letra))
            throw new LetraInvalidaException("Solo se permiten letras del alfabeto.");
        if (letrasUsadas.contains(letra))
            throw new LetraRepetidaException("La letra '" + letra + "' ya fue usada.");
    }

    @Override
    public boolean jugar(char letra)throws LetraRepetidaException, LetraInvalidaException, SinIntentosException {
        validarLetra(letra);
        letrasUsadas.add(letra);

        if (verificarLetra(letra)) {
            actualizarPalabraActual(letra);
            return true;
        } else {
            intentos++;
            if (intentos >= limiteIntentos)
                throw new SinIntentosException("Has perdido. La palabra era: " + palabraSecreta);
            return false;
        }
    }

    /** Métodos abstractos obligatorios para subclases */
    protected abstract void actualizarPalabraActual(char letra);
    protected abstract boolean verificarLetra(char letra);
    protected abstract boolean hasGanado();
}


