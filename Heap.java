import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap {
  /**
   * Crea un monticulo minimo basado en el orden natural del tipo T.
   * El objeto T de menor orden posee la menor prioridad, por tanto se encuentra 
   * en el tope del monticulo.
   * El tipo T debe implementar la interfaz Comparable.  
   * El orden del tipo T es dado por la implementacion del metodo compareTo() 
   */
  static class MonticuloMinimo<T extends Comparable<T>> {
    List<T> datos;

    public MonticuloMinimo() {
      datos = new ArrayList<T>();
    }

    public MonticuloMinimo(T[] datos) {

      this.datos = new ArrayList<T>(datos.length);
      
      for (int i = 0; i < datos.length; i++)
        this.datos.add(datos[i]);
      for (int i = padre(datos.length-1); i >= 0; i--)
        desplazarAbajo(i);
    }

    private int padre(int u) {
      return (u-1)/2;
    }

    private int izquierdo(int p) {
      return 2*p+1;
    }

    private int derecho(int p) {
      return 2*p+2;
    }
    
    private void desplazarAbajo(int p) {

      int izq = izquierdo(p);
      int der = derecho(p);
      int mini = p;

      if (izq < datos.size() && datos.get(izq).compareTo(datos.get(mini)) < 0)
        mini = izq;
      if (der < datos.size() && datos.get(der).compareTo(datos.get(mini)) < 0)
        mini = der;
      if (p != mini) {
        Collections.swap(datos, mini, p);
        desplazarAbajo(mini);
      }
    }

    private void desplazarArriba(int u) {
      
      int p = padre(u);
      
      if (p >= 0 && datos.get(u).compareTo(datos.get(p)) < 0) {
        Collections.swap(datos, u, p);
        desplazarArriba(p);
      }
    }
    
    /**
     * Retorna el elemento con menor prioridad sin retirarlo del monticulo.
     */
    public T consultar() {
      
      if (datos.isEmpty())
        return null;
      return datos.get(0);
    }
  
    /**
     * Inserta el elemento v en el montículo
     */
    public void insertar(T v) {
      
      datos.add(v);
      desplazarArriba(datos.size()-1);
    }
    
    /**
     * Retorna el elemento con menor prioridad y lo retira del monticulo.
     */
    public T extraer() {
      
      if (datos.isEmpty())
        return null;
      T v = datos.get(0);
      datos.set(0, datos.get(datos.size()-1));
      datos.remove(datos.size()-1);
      desplazarAbajo(0);
      return v;
    }
  }

  /** 
   * Ejemplo: Definicion de la clase Entero que implementa la interfaz Comparable.
   */
  static class Entero implements Comparable<Entero> {
    int valor;

    public Entero(int valor) {
      this.valor = valor;
    }
    /**
     * Esta funcion se utiliza para determinar el orden natural del elemento actual (this) 
     * con respecto al objeto pasado como parametro.
     * Si this es menor a obj retorna -1.
     * Si this es mayor a obj retorna +1.
     * Si this es igual a obj retorna 0.
     */
    public int compareTo(Entero obj) {
      return this.valor-obj.valor;
    }
  }

  public static void main(String args[]) {
    
    int[] valores = {43, 51, 34, 83, 25, 10, 43, 78, 32, 41, 30, 45, 56};
    Entero[] enteros = new Entero[valores.length];

    for (int i = 0; i < valores.length; i++)
      enteros[i] = new Entero(valores[i]);
      
    MonticuloMinimo<Entero> mm = new MonticuloMinimo<Entero>(enteros);
    System.out.println("Min: "+mm.consultar().valor);

    mm.extraer();
    System.out.println("Min: "+mm.consultar().valor);

    mm.insertar(new Entero(11));
    mm.insertar(new Entero(9));
    System.out.println("Min: "+mm.consultar().valor);
  }
}
