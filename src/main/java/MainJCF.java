package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Versión del sistema de emergencias usando java.util.PriorityQueue (JCF).
 * Paciente implementa Comparable, por lo que PriorityQueue ordena automáticamente
 * según el código de emergencia (A = mayor prioridad).
 */
public class MainJCF {
    public static void main(String[] args) {
        PriorityQueue<Paciente> colaEmergencia = new PriorityQueue<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    colaEmergencia.offer(new Paciente(datos[0], datos[1], datos[2]));
                }
            }
            System.out.println("Archivo cargado exitosamente. Pacientes en cola: " + colaEmergencia.size());
        } catch (IOException e) {
            System.out.println("Error al leer pacientes.txt");
            return;
        }

        System.out.println("\n--- ATENCIÓN DE PACIENTES (java.util.PriorityQueue - JCF) ---");
        while (!colaEmergencia.isEmpty()) {
            System.out.println(colaEmergencia.poll());
        }
    }
}