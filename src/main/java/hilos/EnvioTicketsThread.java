package hilos;

import modelo.Inscripcion;
import java.util.List;

public class EnvioTicketsThread extends Thread {
    private List<Inscripcion> inscripciones;
    private String nombreActividad;

    public EnvioTicketsThread(String nombreActividad, List<Inscripcion> inscripciones) {
        this.nombreActividad = nombreActividad;
        this.inscripciones = inscripciones;
    }

    @Override
    public void run() {
        System.out.println("[HILO " + getName() + "] Iniciando envío de tickets para: " + nombreActividad);
        for (Inscripcion ins : inscripciones) {
            try {
                Thread.sleep(500); // Simulación de tiempo de envío de 0.5 segundos
                System.out.println("[HILO " + getName() + "] Ticket enviado a: " + ins.getEstudiante().getNombre());
            } catch (InterruptedException e) {
                System.err.println("[ERROR EN HILO]: " + e.getMessage());
            }
        }
        System.out.println("[HILO " + getName() + "] Finalizó el envío de tickets para: " + nombreActividad);
    }
}