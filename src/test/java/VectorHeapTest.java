package test.java;

import main.java.Paciente;
import main.java.VectorHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorHeapTest {

    private VectorHeap<Paciente> heap;

    @BeforeEach
    public void setUp() {
        heap = new VectorHeap<>();
    }

    @Test
    public void testAdd() {
        heap.add(new Paciente("Juan Perez", "fractura de pierna", "C"));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", "A"));

        assertEquals(2, heap.size());
        // Maria tiene prioridad 'A', así que debe ser la primera en la cola (raíz del min-heap)
        assertEquals("Maria Ramirez", heap.getFirst().getNombre());
    }

    @Test
    public void testRemove() {
        heap.add(new Paciente("Lorenzo Toledo", "chikunguya", "E"));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", "A"));
        heap.add(new Paciente("Carmen Sarmientos", "dolores de parto", "B"));

        // Al remover, debe salir en orden de prioridad A, B, E
        Paciente primero = heap.remove();
        assertEquals("Maria Ramirez", primero.getNombre());
        assertEquals(2, heap.size());

        Paciente segundo = heap.remove();
        assertEquals("Carmen Sarmientos", segundo.getNombre());
        assertEquals(1, heap.size());

        Paciente tercero = heap.remove();
        assertEquals("Lorenzo Toledo", tercero.getNombre());
        assertTrue(heap.isEmpty());
    }
}
