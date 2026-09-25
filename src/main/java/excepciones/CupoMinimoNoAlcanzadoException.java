package excepciones;

public class CupoMinimoNoAlcanzadoException extends Exception {
    public CupoMinimoNoAlcanzadoException(String mensaje) {
        super(mensaje);
    }
}