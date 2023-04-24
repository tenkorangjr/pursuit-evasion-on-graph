import java.util.LinkedList;

public class Vertex{

    LinkedList<Edge> edges;

    public Vertex(){
        edges = new LinkedList<>();
    }

    /**
     * Returns the edge which connects this vertex to parameter vertex
     * @param vertex
     * @return
     */
    public Edge getEdgeTo(Vertex vertex){
        for (Edge edge: edges){
            if (edge.other(this).equals(vertex)){
                return edge;
            }
        }
        return null;
    }

    /**
     * Adds an edge to the edges of a particular vertex
     * @param edge
     */
    public void addEdge(Edge edge){
        edges.addLast(edge);
    }

    /**
     * Removes an edge object from a vertex
     * @param edge
     * @return
     */
    public boolean removeEdge(Edge edge){
        if (edges.contains(edge)){
            edges.remove(edge);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of the vertices adjacent to the vertex
     * @return
     */
    public Iterable<Vertex> adjacentVertices(){
        LinkedList<Vertex> out = new LinkedList<>();
        for (Edge curEdge: edges){
            out.addLast(curEdge.other(this));
        }

        return out;
    }

    /**
     * Returns all the edges that are incident to this vertex
     * @return
     */
    public Iterable<Edge> incidentEdges(){
        return edges;
    }
}