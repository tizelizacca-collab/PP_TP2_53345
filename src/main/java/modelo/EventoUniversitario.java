package modelo;

import actividades.Actividad;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public double calcularCostoEstimado() {
        double costoTotal = gratuito ? 0 : costoBase;
        for (Actividad act : actividades) {
            costoTotal += act.calcularCostoMateriales();
        }
        return costoTotal;
    }

    // Método para guardar el evento en disco mediante serialización
    public boolean persistirEvento() {
        String filename = "evento_" + id + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("[PERSISTENCIA] Evento " + id + " guardado exitosamente en: " + filename);
            return true;
        } catch (IOException e) {
            System.err.println("[ERROR PERSISTENCIA] Error al serializar el evento: " + e.getMessage());
            return false;
        }
    }

    // Método estático para recuperar un evento guardado desde disco
    public static EventoUniversitario recuperarEvento(String id) {
        String filename = "evento_" + id + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            EventoUniversitario ev = (EventoUniversitario) ois.readObject();
            System.out.println("[PERSISTENCIA] Evento recuperado correctamente desde: " + filename);
            return ev;
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR PERSISTENCIA] Archivo no encontrado: " + filename);
        } catch (IOException e) {
            System.err.println("[ERROR PERSISTENCIA] Error de E/S al leer archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR PERSISTENCIA] Clase no encontrada durante la deserialización.");
        }
        return null;
    }

    public void mostrarDatos() {
        System.out.println("========================================");
        System.out.println("Evento ID: " + id + " - " + titulo);
        System.out.println("Gratuito: " + (gratuito ? "Sí" : "No") + " | Costo Base: $" + costoBase);
        if (sala != null) {
            System.out.println("Sala: " + sala.getNombre());
        }
        System.out.println("Cantidad de Actividades: " + actividades.size());
        System.out.println("========================================");
    }

    public static int getCantidadEventos() { return cantidadEventos; }
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Actividad> getActividades() { return actividades; }
}