package main.java;

import java.util.Vector;

/**
 * Implementación de una cola con prioridad (min-heap) usando un {@link Vector} como
 * estructura interna. El elemento con menor valor según {@link Comparable} tiene la
 * máxima prioridad y es siempre la raíz del heap.
 *
 * <p>Complejidades:
 * <ul>
 *   <li>{@link #add(Comparable)}: O(log n)</li>
 *   <li>{@link #remove()}: O(log n)</li>
 *   <li>{@link #getFirst()}: O(1)</li>
 * </ul>
 *
 * @param <E> tipo de elemento; debe implementar {@link Comparable}.
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    /** Vector que almacena los elementos del heap. */
    private Vector<E> data;

    /**
     * Construye un VectorHeap vacío.
     */
    public VectorHeap() {
        data = new Vector<>();
    }

    /**
     * Retorna el índice del nodo padre de la posición {@code i}.
     * @param i índice del nodo hijo (≥ 1).
     * @return índice del padre.
     */
    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Retorna el índice del hijo izquierdo del nodo en la posición {@code i}.
     * @param i índice del nodo padre.
     * @return índice del hijo izquierdo.
     */
    protected static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Retorna el índice del hijo derecho del nodo en la posición {@code i}.
     * @param i índice del nodo padre.
     * @return índice del hijo derecho.
     */
    protected static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Sube el elemento en la posición {@code leaf} hasta restaurar la propiedad del heap
     * (el padre siempre es ≤ que sus hijos).
     * @param leaf índice del nodo recién insertado.
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 && value.compareTo(data.get(parent)) < 0) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * Baja el elemento en la posición {@code root} hasta restaurar la propiedad del heap,
     * intercambiándolo con el hijo de menor valor en cada nivel.
     * @param root índice del nodo a bajar.
     */
    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                // Elegir el hijo más pequeño
                if (right(root) < heapSize &&
                        data.get(childpos + 1).compareTo(data.get(childpos)) < 0) {
                    childpos++;
                }
                if (data.get(childpos).compareTo(value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos;
                } else {
                    data.set(root, value);
                    return;
                }
            } else {
                data.set(root, value);
                return;
            }
        }
    }

    /**
     * Inserta un nuevo elemento en el heap y restaura la propiedad de orden.
     * @param value elemento a insertar; no debe ser {@code null}.
     */
    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * Retira y devuelve el elemento con mayor prioridad (el mínimo del heap).
     * @return el elemento de mayor prioridad, o {@code null} si el heap está vacío.
     */
    @Override
    public E remove() {
        if (isEmpty()) return null;
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    /**
     * Devuelve (sin retirar) el elemento con mayor prioridad.
     * @return la raíz del heap, o {@code null} si está vacío.
     */
    @Override
    public E getFirst() {
        if (isEmpty()) return null;
        return data.get(0);
    }

    /**
     * Indica si el heap no contiene elementos.
     * @return {@code true} si el heap está vacío.
     */
    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    /**
     * Retorna la cantidad de elementos actualmente en el heap.
     * @return número de elementos.
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Elimina todos los elementos del heap.
     */
    @Override
    public void clear() {
        data.clear();
    }
}