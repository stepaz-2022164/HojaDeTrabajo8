# Hoja De Trabajo 8 

## Descripción

Sistema de atención de pacientes para la sección de Emergencias de un hospital. Los pacientes ingresan con un código de emergencia de la `A` (más urgente) a la `E` (menos urgente). El sistema siempre atiende primero al paciente de mayor prioridad, usando una **Priority Queue** implementada sobre un **min-heap**.

El proyecto incluye dos versiones funcionales:
- `MainVectorHeap` — usa la implementación propia `VectorHeap`.
- `MainJCF` — usa `java.util.PriorityQueue` del Java Collections Framework.

---

## Estructura del proyecto

```
HojaDeTrabajo8/
├── src/
│   ├── main/java/
│   │   ├── PriorityQueue.java     # Interfaz genérica del ADT
│   │   ├── Paciente.java          # Ficha del paciente (implementa Comparable)
│   │   ├── VectorHeap.java        # Min-heap propio con Javadoc completo
│   │   ├── MainVectorHeap.java    # Programa principal con VectorHeap
│   │   └── MainJCF.java           # Programa principal con java.util.PriorityQueue
│   └── test/java/
│       └── VectorHeapTest.java    # Suite de pruebas (27 casos)
├── pacientes.txt                  # Archivo de datos de entrada
├── HojaDeTrabajo8.iml
└── README.md
```

---

## Requisitos

- JDK 11 o superior
- JUnit 5 (para ejecutar pruebas desde IntelliJ IDEA o Maven)

---

## Formato del archivo de entrada

`pacientes.txt` — campos separados por coma:

```
Nombre, descripcion del sintoma, CodigoEmergencia
```

Ejemplo:
```
Juan Perez, fractura de pierna, C
Maria Ramirez, apendicitis, A
Lorenzo Toledo, chikunguya, E
Carmen Sarmientos, dolores de parto, B
```

Los códigos válidos son `A` (mayor prioridad) a `E` (menor prioridad).

---

## Compilación y ejecución

```bash
# Compilar
javac -d out src/main/java/*.java

# Versión con VectorHeap propio
java -cp out main.java.MainVectorHeap

# Versión con java.util.PriorityQueue (JCF)
java -cp out main.java.MainJCF
```

Salida esperada para ambas versiones:
```
Maria Ramirez, apendicitis, A
Carmen Sarmientos, dolores de parto, B
Juan Perez, fractura de pierna, C
Lorenzo Toledo, chikunguya, E
```

---

## Arquitectura

### `PriorityQueue<E>` (interfaz)
Define el contrato del ADT: `add`, `remove`, `getFirst`, `isEmpty`, `size`, `clear`.

### `Paciente` (implementa `Comparable<Paciente>`)
`compareTo` compara los códigos de emergencia como caracteres (`'A' < 'B' < ... < 'E'`), por lo que el heap coloca siempre al más urgente en la raíz.

### `VectorHeap<E extends Comparable<E>>` (implementa `PriorityQueue<E>`)
Min-heap almacenado en un `Vector<E>`. Relaciones de índices para un nodo en posición `i`:

| Relación | Fórmula |
|---|---|
| Padre | `(i - 1) / 2` |
| Hijo izquierdo | `2i + 1` |
| Hijo derecho | `2i + 2` |

Operaciones clave:
- `add(value)` — inserta al final y llama `percolateUp` → O(log n).
- `remove()` — mueve el último elemento a la raíz y llama `pushDownRoot` → O(log n).
- `getFirst()` — lectura directa del índice 0 → O(1).

---

## Pruebas unitarias

`VectorHeapTest.java` cubre **27 casos** agrupados por método:

| Grupo | Casos |
|---|---|
| `add` | Inserción simple, raíz es el mínimo, percolación correcta, prioridades iguales |
| `remove` | Orden A→B→C→E, decremento de size, vaciado completo, null en heap vacío, propiedad tras remover |
| `getFirst` | No modifica el heap, null en heap vacío |
| `isEmpty` / `size` / `clear` | Heap nuevo, vaciado con clear |
| `Paciente.compareTo` | Mayor, menor e igual prioridad |
| Caso del enunciado | Secuencia completa exacta del documento |

Para ejecutar desde IntelliJ: clic derecho sobre `VectorHeapTest.java` → *Run*.

---

## Decisiones de diseño

- **`remove()` retorna `null` en heap vacío** en lugar de lanzar excepción, consistente con la interfaz `java.util.Queue`.
- **`pushDownRoot` elige el hijo más pequeño** (no solo el izquierdo) para garantizar la propiedad del min-heap con ambos hijos presentes.
- **`Paciente.compareTo` delega en `String.compareTo`** sobre el código de emergencia — comparación lexicográfica que funciona correctamente para el rango A–E.
- **Javadoc completo en `VectorHeap`** con complejidades O(log n) / O(1) documentadas por método.

---

## Autor

Curso CC2003 – Algoritmos y Estructura de Datos · UVG · 2020
