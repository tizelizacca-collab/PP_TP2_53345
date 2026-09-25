import actividades.Curso;
import excepciones.CupoExcedidoException;
import excepciones.CupoMinimoNoAlcanzadoException;
import hilos.EnvioTicketsThread;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;

public class App {
    public static void main(String[] args) {
        System.out.println("=== INTEGRACIÓN COMPLETA TP2 (EJERCICIOS 1 AL 4) ===");

        EventoUniversitario evento = new EventoUniversitario("EVT-FINAL", "Expo Tecnología UTN 2026", 5000.0, false);
        evento.asignarSala(new Sala(301, "Auditorio Central"));

        Curso curso = new Curso(101, "Programación Concurrente en Java", 10, 30);
        evento.agregarActividad(curso);

        // Creamos solo 2 estudiantes para probar el caso donde NO se alcanza el cupo mínimo (que es 5)
        Estudiante e1 = new Estudiante("3001", "Lucas Benítez");
        Estudiante e2 = new Estudiante("3002", "Sonia Giménez");

        // --- PRUEBA DE EXCEPCIONES (EJERCICIOS 1 Y 3) ---
        try {
            System.out.println("\n--- Inscribiendo alumnos ---");
            curso.inscribir(e1);
            curso.inscribir(e2);
            System.out.println("Alumnos inscritos correctamente.");

            System.out.println("\n--- Validando Cupo Mínimo ---");
            curso.validarCupoMinimo(); // Lanzará CupoMinimoNoAlcanzadoException

        } catch (CupoExcedidoException e) {
            System.out.println("[CATCH - CUPO EXCEDIDO]: " + e.getMessage());
        } catch (CupoMinimoNoAlcanzadoException e) {
            System.out.println("[CATCH - CUPO MÍNIMO]: " + e.getMessage());
        } finally {
            System.out.println("[FINALLY]: Control de inscripción y cupos finalizado.");
        }

        // --- PRUEBA DE PERSISTENCIA (EJERCICIO 1) ---
        System.out.println("\n--- Persistencia de Datos ---");
        if (evento.persistirEvento()) {
            EventoUniversitario recuperado = EventoUniversitario.recuperarEvento("EVT-FINAL");
            if (recuperado != null) {
                recuperado.mostrarDatos();
            }
        }

        // --- PRUEBA DE CONCURRENCIA / HILOS (EJERCICIO 4) ---
        System.out.println("\n--- Ejecución de Hilos (Envío de Tickets) ---");
        EnvioTicketsThread hiloTickets = new EnvioTicketsThread(curso.getTitulo(), curso.getInscripciones());
        hiloTickets.start();

        try {
            hiloTickets.join(); // Espera a que el hilo termine antes de finalizar el programa
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== TRABAJO PRÁCTICO 2 FINALIZADO CON ÉXITO ===");
    }
}