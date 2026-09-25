package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "Confirmada";
    }

    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }

    @Override
    public String toString() {
        return estudiante.getNombre() + " - Fecha: " + fecha + " - Estado: " + estado;
    }
}