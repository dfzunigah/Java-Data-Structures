    // -------------------------
    // LISTA DOBLEMENTE ENLAZADA
    // -------------------------
public class DoubleLinkedList<T> {

    // CLASE AUXILIAR NODO-LISTA
    static class NodoLista<T> {

        NodoLista anterior;
        NodoLista siguiente;
        T valor;

        public NodoLista(T valor) {
            this.valor = valor;
            siguiente = anterior = null;
        }
    }

    NodoLista<T> frente;
    NodoLista<T> cola;
    int longitud;

    public DoubleLinkedList() {
        frente = cola = null;
        longitud = 0;
    }

    public void insertarFrente(T valor) {

        NodoLista<T> nodo = new NodoLista<>(valor);

        if (longitud == 0) {
            cola = nodo;
        } else {
            frente.anterior = nodo;
            nodo.siguiente = frente;
        }
        frente = nodo;
        longitud++;
    }

    public void insertarCola(T valor) {

        if (longitud == 0) {
            insertarFrente(valor);
        } else {
            NodoLista<T> nodo = new NodoLista<>(valor);
            cola.siguiente = nodo;
            nodo.anterior = cola;
            cola = nodo;
            longitud++;
        }
    }

    public void insertar(T valor, int posicion) {

        if (posicion < 0 || posicion > longitud) {
            throw new IllegalArgumentException("posicion " + posicion + " fuera de rango [0, " + longitud + "]");
        }
        if (posicion == 0) {
            insertarFrente(valor);
        } else if (posicion == longitud) {
            insertarCola(valor);
        } else {
            NodoLista<T> ref = frente;

            for (int i = 0; i < posicion; i++) {
                ref = ref.siguiente;
            }
            NodoLista<T> nodo = new NodoLista<>(valor);
            nodo.siguiente = ref;
            nodo.anterior = ref.anterior;
            ref.anterior.siguiente = nodo;
            ref.anterior = nodo;
            longitud++;
        }
    }

    public T removerFrente() {

        if (longitud == 0) {
            return null;
        }
        T valor = frente.valor;
        frente = frente.siguiente;
        longitud--;

        if (longitud > 0) {
            frente.anterior = null;
        }
        return valor;
    }

    public T removerCola() {

        if (longitud == 0) {
            return null;
        }
        T valor = cola.valor;
        cola = cola.anterior;
        longitud--;

        if (longitud > 0) {
            cola.siguiente = null;
        }
        return valor;
    }

    public T remover(int posicion) {

        if (posicion < 0 || posicion >= longitud) {
            throw new IllegalArgumentException("posicion " + posicion + " fuera de rango [0, " + longitud + ")");
        }

        if (posicion == 0) {
            return removerFrente();
        }
        if (posicion == longitud - 1) {
            return removerCola();
        }
        NodoLista<T> ref = frente;

        for (int i = 0; i < posicion; i++) {
            ref = ref.siguiente;
        }
        T valor = ref.valor;
        ref.anterior.siguiente = ref.siguiente;
        ref.siguiente.anterior = ref.anterior;
        longitud--;
        return valor;
    }

    public T valorEn(int posicion) {

        if (posicion < 0 || posicion >= longitud) {
            throw new IllegalArgumentException("posicion " + posicion + " fuera de rango [0, " + longitud + ")");
        }
        NodoLista<T> ref = frente;

        for (int i = 0; i < posicion; i++, ref = ref.siguiente);
        return ref.valor;
    }

    public T obtenerFrente() {

        if (longitud == 0) {
            return null;
        }
        return frente.valor;
    }

    public T obtenerCola() {

        if (longitud == 0) {
            return null;
        }
        return cola.valor;
    }

    public int indiceDe(T valor) {

        NodoLista<T> ref = frente;

        for (int i = 0; i < longitud; i++, ref = ref.siguiente) {
            if (ref.valor == valor) {
                return i;
            }
        }
        return -1;
    }

    public boolean contiene(T valor) {
        return indiceDe(valor) != -1;
    }

    public int longitud() {
        return longitud;
    }

    @Override
    public String toString() {
        if (longitud == 0) {
            return "";
        }
        NodoLista<T> ref = frente;
        StringBuffer sb = new StringBuffer("[");
        sb.append(ref.valor.toString());
        while (ref.siguiente != null) {
            ref = ref.siguiente;
            sb.append(", ");
            sb.append(ref.valor.toString());
        }
        sb.append("]");
        return sb.toString();
    }

}
