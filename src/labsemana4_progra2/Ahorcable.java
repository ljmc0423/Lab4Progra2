/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana4_progra2;

import labsemana4_progra2.Exceptions.LetraRepetidaException;
import labsemana4_progra2.Exceptions.LetraInvalidaException;
import labsemana4_progra2.Exceptions.SinIntentosException;

/**
 *
 * @author ljmc2
 */
public interface Ahorcable {
    void inicializarPalabraSecreta();
    public boolean jugar(char letra)throws LetraRepetidaException, LetraInvalidaException, SinIntentosException;
}

