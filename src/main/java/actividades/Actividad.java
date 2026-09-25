package actividades;

import excepciones.CupoExcedidoException;
import excepciones.CupoMinimoNoAlcanzadoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int CUPO_MINIMO = 5;

    private int id;
    private String titulo;
    private int cupoMaximo;
    protected List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public void inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException(
                    "Cupo agotado para la actividad: " + titulo + " (Cupo máximo: " + cupoMaximo + ")"
            );
        }
        Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
        inscripciones.add(nuevaInscripcion);
        System.out.println("Inscripción exitosa para: " + estudiante.getNombre());
    }

    public void validarCupoMinimo() throws CupoMinimoNoAlcanzadoException {
        if (inscripciones.size() < CUPO_MINIMO) {
            throw new CupoMinimoNoAlcanzadoException(
                    "La actividad " + titulo + " no alcanza el cupo mínimo de " + CUPO_MINIMO + " inscritos. (" + inscripciones.size() + " registrados)"
            );
        }
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}