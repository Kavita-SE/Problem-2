package Test;

import java.util.LinkedList;
import java.util.Scanner;

class Edge {
    private String source;
    private String destination;
    private int weight;
    Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    String getSource(){
        return source;
    }

    String getDestination(){
        return destination;
    }

    int getWeight(){
        return weight;
    }
}
public class Graph{
    int numVertices;
    LinkedList<Edge> adjacencylist[];
    int paths; // variable to store paths from source to destination
    int totalDistance; // variable to store total distance covered during traversing all paths

    Graph(int vertices){
        this.numVertices = vertices;
        adjacencylist = new LinkedList[vertices];

        for (int i = 0; i <numVertices; i++){
            adjacencylist[i] = new LinkedList<Edge>();
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.adjacencyList();
        graph.printGraph();
        Scanner sc = new Scanner(System.in);

        System.out.println("\nFinding the Average Distance travelled in Graph between Source and Destination");
        System.out.print("\nEnter source: ");
        String source = sc.nextLine();
        System.out.print("\nEnter destination: ");
        String destination = sc.nextLine();
        System.out.println("Average Distance: "+ graph.calculateAverageDistanceBetweenTwoPoints(source,destination));
    }

    void addEdge(String source, String destination, int weight) {
        Edge forEdge = new Edge(source, destination, weight);
        adjacencylist[source.charAt(0) - 65].add(forEdge);

        Edge backEdge = new Edge(destination, source, weight);
        adjacencylist[destination.charAt(0) - 65].add(backEdge);
    }
    void adjacencyList(){
        addEdge("A", "B", 12);
        addEdge("A", "C", 13);
        addEdge("B", "C", 3);
        addEdge("A", "E", 8);
        addEdge("E", "C", 4);
        addEdge("A", "D", 11);
        addEdge("D", "E", 7);
    }

    void printGraph(){
        System.out.println("Graph edges with respected weights are shown below: ");
        for(LinkedList<Edge> adjList : adjacencylist)
            for(Edge e : adjList)
                System.out.println(e.getSource() + " --("+ e.getWeight() +")--> " + e.getDestination());
    }

    public double calculateAverageDistanceBetweenTwoPoints(String x, String y)
    {
        boolean visited[]=new boolean[numVertices];
        for(int i=0;i<numVertices;i++)
            visited[i]=false;
        paths=0;
        totalDistance=0;
        visited[(int)(x.charAt(0))-65]=true;

        // Depth First Search is used to calculate total number of paths exist between source and destination
        // and total distance covered in all the paths
        depthFirstSearch(x,y,visited,0);

        return ((double)totalDistance / (double)paths);
    }

    private void depthFirstSearch(String x, String y, boolean[] visited, int distance){

        if(x.equals(y)){
            paths++;
            totalDistance += distance;
            //System.out.println("paths: "+paths + "total Distance: "+totalDistance);
            return;
        }
        for(Edge e : adjacencylist[x.charAt(0)-65]){

            String reachedVertex = e.getDestination();
            int reachedIndex = (reachedVertex.charAt(0)) - 65;
            if(!visited[reachedIndex]){
                visited[reachedIndex] = true;
                depthFirstSearch(reachedVertex, y, visited, distance+e.getWeight());
                visited[reachedIndex] = false;
            }
        }
    }
}
