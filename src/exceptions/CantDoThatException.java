package exceptions;


import madballs.Carta;

public class CantDoThatException extends Exception {

    private Carta cartaQueProdujoLaExcepcion;

    public CantDoThatException(String message, Carta carta) {
        super(message);
        cartaQueProdujoLaExcepcion = carta;
    }

    public String getNombreCarta(){
        return cartaQueProdujoLaExcepcion.getNombre();
    }
}
