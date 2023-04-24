import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Graph {

    private LinkedList<Vertex> vertices;
    private LinkedList<Edge> edges;
    private int size;

    public Graph(int n, double probability){
        size = n;
        Random picker = new Random();
        vertices = new LinkedList<>();

        for (int i = 0; i < n; i++){
            vertices.addLast(new Vertex());
        }
        for (Vertex vertex1: vertices){
            for (Vertex vertex2: vertices){
                if (!vertex2.equals(vertex1) && vertex2.getEdgeTo(vertex1) == null && picker.nextDouble() <= probability){
                    addEdge(vertex2, vertex1, 1.0);
                }
            }
        }
    }

    public Graph(int n){
        this(n, 0.0);
    }

    public Graph(){
        this(0);
    }

    /**
     * Return the size of the graph
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Return a list of the vertices in the graph
     * @return
     */
    public Iterable<Vertex> getVertices(){
        return vertices;
    }

    /**
     * Return the list of the edges in the graph
     * @return
     */
    public Iterable<Edge> getEdges(){
        return edges;
    }

    /**
     * Add a vertex to the graph
     * @return
     */
    public Vertex addVertex(){
        Vertex newVertex = new Vertex();
        vertices.addLast(newVertex);
        return newVertex;
    }

    /**
     * Adds an egde between u and v with distance `distance` to the graph and returns it
     * @param u
     * @param v
     * @param distance
     * @return
     */
    public Edge addEdge(Vertex u, Vertex v, double distance){
        Edge newEdge = new Edge(u, v, distance);
        u.addEdge(newEdge);
        v.addEdge(newEdge);
        edges.addLast(newEdge);
        return newEdge;
    }

    /**
     * Returns the edge that connects the two vertices params.
     * If there is no edge, return null
     * @param u
     * @param v
     * @return
     */
    public Edge getEdge(Vertex u, Vertex v){
        for (Edge edge: edges){
            if (edge.other(v).equals(u)){
                return edge;
            }
        }
        return null;
    }

    /**
     * Removes a vertex from the graph and from the edge
     * @param vertex
     * @return
     */
    public boolean remove(Vertex vertex){
        if (vertices.contains(vertex)){
            vertices.remove(vertex);
            // Remove all of its edges and the vertex
            for (Edge edge: vertex.incidentEdges()){
                edge.other(vertex).removeEdge(edge);
            }
            return true;
        }
        return false;
    }

    /**
     * Remove an edge from the graph.
     * Removes edge from vertices also.
     * @param edge
     * @return
     */
    public boolean remove(Edge edge){
        if (edge == null){
            return false;
        }
        Vertex[] toRemoveVertices = edge.vertices();
        for (Vertex ver: toRemoveVertices){
            ver.removeEdge(edge);
        }

        return true;
    }

    /**
     * Compute the minimal distance in this Graph from the given Vertex source to all other Vertices in the graph. 
     * The HashMap returned maps each Vertex to its distance from the source.
     * @param source
     * @return
     */
    public HashMap<Vertex, Double> distanceFrom(Vertex source){
        
    }
}
