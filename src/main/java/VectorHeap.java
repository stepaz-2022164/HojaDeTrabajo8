package main.java;

import java.util.Vector;

/**
 * Implementación de una cola con prioridad utilizando un Vector como estructura base (Min-Heap).
 * * @param <E> Tipo de elemento que contiene el heap, el cual debe ser Comparable.
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    private Vector<E> data;

    /**
     * Constructor que inicializa un nuevo VectorHeap vacío.
     */
    public VectorHeap() {
        data = new Vector<>();
    }

    /**
     * Retorna el índice del padre del nodo en la posición i.
     * @param i Índice del nodo actual.
     * @return Índice del nodo padre.
     */
    protected static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Retorna el índice del hijo izquierdo del nodo en la posición i.
     * @param i Índice del nodo actual.
     * @return Índice del hijo izquierdo.
     */
    protected static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Retorna el índice del hijo derecho del nodo en la posición i.
     * @param i Índice del nodo actual.
     * @return Índice del hijo derecho.
     */
    protected static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Mueve el nodo en el índice hoja hacia arriba hasta su posición correcta en el heap.
     * @param leaf Índice del nodo a mover.
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 && (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * Mueve el nodo en el índice raíz hacia abajo hasta su posición correcta en el heap.
     * @param root Índice del nodo a mover.
     */
    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) && ((data.get(childpos + 1)).compareTo(data.get(childpos)) < 0)) {
                    childpos++;
                }
                if ((data.get(childpos)).compareTo(value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos; // Sigue moviéndose hacia abajo
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

    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    @Override
    public E remove() {
        if (isEmpty()) return null;
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) return null;
        return data.get(0);
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        data.clear();
    }
}
