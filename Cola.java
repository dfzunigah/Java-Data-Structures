// ----
// COLA - QUEUE
// ----
public class Cola<T> {

    DoubleLinkedList<T> lista;

    public Cola() {
        lista = new DoubleLinkedList<T>();
    }

    public void insertar(T valor) {
        lista.insertarCola(valor);
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
