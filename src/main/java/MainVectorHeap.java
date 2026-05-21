package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainVectorHeap {
    public static void main(String[] args) {
        VectorHeap<Paciente> colaEmergencia = new VectorHeap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    Paciente paciente = new Paciente(datos[0], datos[1], datos[2]);
                    colaEmergencia.add(paciente);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo pacientes.txt");
            return;
        }

        System.out.println("--- ATENCIÓN DE PACIENTES (VECTOR HEAP PROPIO) ---");
        while (!colaEmergencia.isEmpty()) {
            Paciente p = colaEmergencia.remove();
            System.out.println(p.toString());
        }
    }
}
