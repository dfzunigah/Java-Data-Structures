    // ----
    // PILA - STACK
    // ----
public class Pila<T> {
    DoubleLinkedList<T> lista;

    public Pila() {
        lista = new DoubleLinkedList<T>();
    }

    public void insertar(T valor) {
        lista.insertarFrente(valor);
    }

    public T remover() {
        return lista.removerFrente();
    }

    public T consultar() {
        return lista.obtenerFrente();
    }

    public int longitud() {
        return lista.longitud();
    }
}
