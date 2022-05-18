package grafo;

public class AdjGraph {
    int vertex;
    int matrix[][];

    public AdjGraph (int vertex) {
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }

    public void addEdge(int source, int destination) {
        //add edge
        matrix[source][destination] = 1;

        //add bak edge for undirected graph
        matrix[destination][source] = 1;
    }

    public void printGraph() {
        System.out.println("Matriz de Adjacência");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j <vertex ; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
        for (int i = 0; i < vertex; i++) {
            if(i == 0){
                System.out.println("\n\nComponentes conexos:");
            } else {
                System.out.print("Vértice " + i + " é conectado a: ");
            }
            for (int j = 0; j <vertex ; j++) {
                if(matrix[i][j]==1){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}
