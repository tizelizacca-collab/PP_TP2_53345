package actividades;

import certificacion.Certificable;
import modelo.Estudiante;

public class Curso extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;

    private int horas;

    public Curso(int id, String titulo, int cupoMaximo, int horas) {
        super(id, titulo, cupoMaximo);
        this.horas = horas;
    }

    @Override
    public double calcularCostoMateriales() {
        return horas * 100.0;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "CERTIFICADO DE APROBACIÓN [" + ENTIDAD_EMISORA + "]\n" +
                "Otorgado a: " + estudiante.getNombre() + " (Legajo: " + estudiante.getLegajo() + ")\n" +
                "Por completar la actividad: " + getTitulo() + " (" + horas + " hs)";
    }

    public int getHoras() { return horas; }
}