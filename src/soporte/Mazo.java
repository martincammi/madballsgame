package soporte;

import madballs.Carta;

import java.util.Collections;
import java.util.Stack;

public class Mazo extends Stack<Carta> {

    public Carta robar(){
        Carta carta = super.pop();
        System.out.println("Roba " + carta.getNombre());
        return carta;
    }

    public void mezclar(){
        Collections.shuffle(this);
    }
}
