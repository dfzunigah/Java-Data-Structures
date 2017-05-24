import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        //Para lectura de datos
        Scanner reader = new Scanner(System.in); 
        int numeroAristas, origen, destino, distancia, inicial, numeroNodos, cases;
        //Número de casos
        System.out.print("Ingresa el número de casos: ");
        cases = reader.nextInt();
        for (int j = 0; j < cases; j++) {
            System.out.print("# de nodos: ");
            numeroNodos = reader.nextInt();
            System.out.print("# de aristas: ");
            numeroAristas = reader.nextInt();
            System.out.print("Nodo de inicio: ");
            inicial = reader.nextInt();
            System.out.print("Nodo final: ");
            int end = reader.nextInt();
            Dijkstra dijkstraAlgorithm = new Dijkstra(numeroNodos);
            for (int i = 0; i < numeroAristas; ++i) {
                System.out.println("Por favor, ingresa las aristas (origen, destino, distancia)");
                origen = reader.nextInt();
                destino = reader.nextInt();
                distancia = reader.nextInt();
                /*El boolean en el argumento indica si es un grafo dirigido o no
                  true -> Dirigido y false -> No dirigido
                */
                dijkstraAlgorithm.addEdge(origen, destino, distancia, true);
            }
            
            //Distancia más corta en el nodo "inicial" y el nodo "end"
            int minimumDistance = dijkstraAlgorithm.dijkstra(inicial, end);
            //Si es posible alcanzar el nodo
            if(minimumDistance!=1073741824){
                System.out.println("Caso #" + (j+1) + ":");
                System.out.println("Distancia mínima entre " + inicial + " y " + end + ": " + minimumDistance);
                //Descomentar para que se imprima el camino
                //System.out.print("Camino: ");
                //dijkstraAlgorithm.printPath(end);
            }else{
                System.out.println("Caso #" + (j+1) + ":");
                System.out.println("Inalcanzable");
            }
        }
    }
}

class Dijkstra {
    //Maximo numero de vértices
    private final int MAX = 10005;
    //Se define un valor grande que represente la distancia infinita inicial,
    //basta conque sea superior al maximo valor del peso en alguna de las aristas
    private final int INF = 1 << 30;
    
    private List< List< Node>> ady = new ArrayList< List< Node>>();
    private int distancia[] = new int[MAX];
    //Arreglo de booleanos para identificar los vértices visitados
    private boolean visitado[] = new boolean[MAX];
    private PriorityQueue< Node> Q = new PriorityQueue<Node>(); 
    private int numberVextex; 
    private int previo[] = new int[MAX]; 
    private boolean dijkstraEjecutado;

    Dijkstra(int V) {
        this.numberVextex = V;
        for (int i = 0; i <= V; ++i) {
            ady.add(new ArrayList<Node>());
        }
        dijkstraEjecutado = false;
    }
    
    //Clase que representa cada nodo
    class Node implements Comparable<Node> {

        int first, second;

        Node(int d, int p) {
            this.first = d;
            this.second = p;
        }

        //Este método permite comparar y devuelve el menor valor
        //Se usa para que el la priorityQueue el nodo menor este en el tope
        public int compareTo(Node other) {           
            if (second > other.second) {
                return 1;
            }
            if (second == other.second) {
                return 0;
            }
            return -1;
        }
    };

    private void init() {
        for (int i = 0; i <= numberVextex; ++i) {
            /*Se inicia con todas las ditancias en infinito,
              con todos los nodos como no visitados y el vertice
              previo con -1
            */
            distancia[i] = INF; 
            visitado[i] = false; 
            previo[i] = -1;
        }
    }

    private void relajacion(int actual, int adyacente, int peso) {
        //Si la distancia del origen al vertice actual + peso de su arista es
        //menor a la distancia del origen al vertice adyacente
        if (distancia[actual] + peso < distancia[adyacente]) {
            distancia[adyacente] = distancia[actual] + peso;
            //Actualizamos el vertice previo
            previo[adyacente] = actual;
            //Agregamos el nodo adyavente a la cola de prioridad
            Q.add(new Node(adyacente, distancia[adyacente]));
        }
    }

    int dijkstra(int inicial, int end) {
        init(); 
        Q.add(new Node(inicial, 0)); 
        distancia[inicial] = 0;   
        int actual, adyacente, peso;
        while (!Q.isEmpty()) { 
            actual = Q.element().first; 
            Q.remove();
            //Si el vértice actual ya fue visitado entonces
            //sigo sacando elementos de la cola
            if (visitado[actual]) {
                continue;
            }
            visitado[actual] = true;

            //Reviso los vertices adyacentes
            for (int i = 0; i < ady.get(actual).size(); ++i) { 
                adyacente = ady.get(actual).get(i).first; 
                peso = ady.get(actual).get(i).second; 
                if (!visitado[adyacente]) { 
                    relajacion(actual, adyacente, peso);
                }
            }
        }
        dijkstraEjecutado = true;
        return distancia[end];
    }

    void addEdge(int origen, int destino, int peso, boolean dirigido) {
        //Para grafos dirigidos
        ady.get(origen).add(new Node(destino, peso));
        //Para grafos no dirigidos
        if (!dirigido) {
            ady.get(destino).add(new Node(origen, peso));
        }
    }
    

    
    //Impresion del camino mas corto desde el vertice inicial y final ingresados
    void printPath( int destino ){
        //Si aun poseo un vertice previo sigo explorando recursivamente
        if( previo[ destino ] != -1 )    
            printPath( previo[ destino ] );
        //Terminada la recursión se imprimen los vertices recorridos
        System.out.printf("%d " , destino );        
    }
    
}