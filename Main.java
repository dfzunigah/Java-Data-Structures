import java.math.*;
import java.util.*;
import java.io.*;

public class Main {
  static class ArbolDeSegmentos {
    static class Nodo {
      Nodo izq;
      Nodo der;
      int v;
      int min, max;

      public Nodo(int min, int max) {

        this.min = min;
        this.max = max;
        izq = der = null;
      }
    }
    int[] datos;
    Nodo raiz;

    ArbolDeSegmentos(int[] datos) {

      this.datos = new int[datos.length];
      System.arraycopy(datos, 0, this.datos, 0, datos.length);
      raiz = new Nodo(0, datos.length-1);
      construir(0, datos.length-1, raiz);
    }

    void construir(int min, int max, Nodo raiz) {
    
      if (min == max) {
        raiz.v = datos[min];
        return;
      }
      int med = (min+max)/2;
      raiz.izq = new Nodo(min, med);
      construir(min, med, raiz.izq);
      raiz.der = new Nodo(med+1, max);
      construir(med+1, max, raiz.der);
      raiz.v = Math.min(raiz.izq.v, raiz.der.v);
    }
      
    void actualizar(int p, int v) {
      actualizar(p, v, raiz);
    }

    void actualizar(int p, int v, Nodo raiz) {
      
      if (raiz.min == raiz.max) {
        raiz.v = datos[p] = v;
        return;
      }
      int med = (raiz.min+raiz.max)/2;
      
      if (p <= med)
        actualizar(p, v, raiz.izq);
      else
        actualizar(p, v, raiz.der);
      raiz.v = Math.min(raiz.izq.v, raiz.der.v);
    }

    int consultar(int c_min, int c_max) {
      return consultar(c_min, c_max, raiz);
    }
    
    int consultar(int c_min, int c_max, Nodo raiz) {
      
      if (raiz.max < c_min || raiz.min > c_max)
        return Integer.MAX_VALUE;
      if (raiz.min >= c_min && raiz.max <= c_max)
        return raiz.v;
      int izq = consultar(c_min, c_max, raiz.izq);
      int der = consultar(c_min, c_max, raiz.der);
      return Math.min(izq, der);
    }
    
  }

  public static void main(String args[]) {
    
    int[] datos = {43, 51, 34, 83, 25, 10, 43, 78, 32, 41, 30, 45, 56};
    ArbolDeSegmentos as = new ArbolDeSegmentos(datos);
    System.out.println(as.consultar(2, 6));
    as.actualizar(5, 30);
    System.out.println(as.consultar(2, 6));
  }
}


