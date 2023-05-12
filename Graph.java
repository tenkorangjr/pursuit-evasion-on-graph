
/**Author: Michael Tenkorang
*Course: CS231
*Purpose: Implementing a pursuit simulation with graphs
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Graph {

    private LinkedList<Vertex> vertices;
    private LinkedList<Edge> edges;
    private int size;

    public Graph(int n, double probability) {
        Random picker = new Random();
        vertices = new LinkedList<>();
        edges = new LinkedList<>();
        this.size = n;

        for (int i = 0; i < n; i++) {
            vertices.addLast(new Vertex());
        }
        for (Vertex vertex1 : vertices) {
            for (Vertex vertex2 : vertices) {
                if (!vertex2.equals(vertex1) && vertex2.getEdgeTo(vertex1) == null
                        && picker.nextDouble() <= probability) {
                    addEdge(vertex2, vertex1, 1.0);
                }
            }
        }
    }

    public Graph(int n) {
        this(n, 0.0);
    }

    public Graph() {
        this(0);
    }

    /**
     * Return the size of the graph
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Return a list of the vertices in the graph
     * 
     * @return
     */
    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Return the list of the edges in the graph
     * 
     * @return
     */
    public LinkedList<Edge> getEdges() {
        return edges;
    }

    /**
     * Add a vertex to the graph
     * 
     * @return
     */
    public Vertex addVertex() {
        Vertex newVertex = new Vertex();
        vertices.addLast(newVertex);
        size += 1;
        return newVertex;
    }

    /**
     * Adds an egde between u and v with distance `distance` to the graph and
     * returns it
     * 
     * @param u
     * @param v
     * @param distance
     * @return
     */
    public Edge addEdge(Vertex u, Vertex v, double distance) {
        Edge newEdge = new Edge(u, v, distance);
        u.addEdge(newEdge);
        v.addEdge(newEdge);
        edges.addLast(newEdge);
        return newEdge;
    }

    /**
     * Returns the edge that connects the two vertices params.
     * If there is no edge, return null
     * 
     * @param u
     * @param v
     * @return
     */
    public Edge getEdge(Vertex u, Vertex v) {
        for (Edge edge : edges) {
            if (edge.other(v).equals(u)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Removes a vertex from the graph and from the edge
     * 
     * @param vertex
     * @return
     */
    public boolean remove(Vertex vertex) {
        if (vertices.contains(vertex)) {
            vertices.remove(vertex);
            // Remove all of its edges and the vertex
            for (Edge edge : vertex.incidentEdges()) {
                edge.other(vertex).removeEdge(edge);
            }
            size -= 1;
            return true;
        }
        return false;
    }

    /**
     * Remove an edge from the graph.
     * Removes edge from vertices also.
     * 
     * @param edge
     * @return
     */
    public boolean remove(Edge edge) {
        if (edge == null) {
            return false;
        }
        Vertex[] toRemoveVertices = edge.vertices();
        for (Vertex ver : toRemoveVertices) {
            ver.removeEdge(edge);
        }

        return true;
    }

    /**
     * Compute the minimal distance in this Graph from the given Vertex source to
     * all other Vertices in the graph.
     * The HashMap returned maps each Vertex to its distance from the source.
     * 
     * @param source
     * @return
     */
    public HashMap<Vertex, Double> distanceFrom(Vertex source) {
        HashMap<Vertex, Double> distances = new HashMap<>();

        distances.put(source, 0.0);
        for (Vertex vertex : vertices) {
            if (!vertex.equals(source)) {
                distances.put(vertex, Double.POSITIVE_INFINITY);
            }
        }

        Comparator<Vertex> comparator = new Comparator<Vertex>() {

            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (distances.get(o1) > distances.get(o2)) {
                    return 1;
                } else if (distances.get(o1) < distances.get(o2)) {
                    return -1;
                } else {
                    return 0;
                }
            }

        };
        PriorityQueue<Vertex> priorityQueue = new Heap<Vertex>(comparator, false);

        priorityQueue.offer(source);
        for (Vertex vertex : vertices) {
            if (!vertex.equals(source)) {
                priorityQueue.offer(vertex);
            }
        }

        while (priorityQueue.size() > 0) {
            Vertex curVertex = priorityQueue.poll();
            for (Edge edge : curVertex.incidentEdges()) {
                double newDistance = distances.get(curVertex) + edge.distance();

                Vertex otherVertex = edge.other(curVertex);
                if (newDistance < distances.get(otherVertex)) {
                    distances.put(otherVertex, newDistance);
                    priorityQueue.updatePriority(otherVertex);
                }
            }
        }

        return distances;
    }

    /**
     * Compute the shortest path in this Graph from the given Vertex source to the
     * target Vertex.
     * Returns the list of vertices representing the shortest path.
     *
     * @param source
     * @param target
     * @return
     */
    public ArrayList<Vertex> shortestPath(Vertex source, Vertex target) {
        HashMap<Vertex, Double> distances = distanceFrom(source);
        if (!distances.containsKey(target) || distances.get(target) == Double.POSITIVE_INFINITY) {
            return null; // No path exists
        }

        // Use a priority queue to select the vertex with the shortest distance
        PriorityQueue<Vertex> priorityQueue = new Heap<>();
        priorityQueue.offer(source);

        // Keep track of the previous vertex to reconstruct the path
        HashMap<Vertex, Vertex> previous = new HashMap<>();
        previous.put(source, null);

        while (priorityQueue.size() != 0) {
            Vertex current = priorityQueue.poll();

            if (current.equals(target)) {
                break; // Found the target vertex, exit the loop
            }

            for (Edge edge : current.incidentEdges()) {
                Vertex neighbor = edge.other(current);
                double newDistance = distances.get(current) + edge.distance();

                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                    priorityQueue.offer(neighbor);
                }
            }
        }

        // Reconstruct the shortest path
        ArrayList<Vertex> shortestPath = new ArrayList<>();
        Vertex vertex = target;
        while (vertex != null) {
            shortestPath.add(0, vertex);
            vertex = previous.get(vertex);
        }

        return shortestPath;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10, 0.5);

        assert graph.size() == 10 : "size() method not working";
        System.out.println("10 == " + graph.size());
    }
}
