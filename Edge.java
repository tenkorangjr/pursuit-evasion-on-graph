/**
 * Author: Michael Tenkorang
 * Course: CS231
 * Purpose: Implementing a pursuit simulation with graphs
 */

public class Edge {

    private double distance;
    private Vertex v1, v2;

    public Edge(Vertex u, Vertex v, double distance) {
        this.distance = distance;
        this.v1 = u;
        this.v2 = v;
    }

    /**
     * Return the distance of the edge
     * 
     * @return
     */
    public double distance() {
        return this.distance;
    }

    /**
     * Returns the other vertex of an edge
     * 
     * @param vertex
     * @return
     */
    public Vertex other(Vertex vertex) {
        if (vertex.equals(v1)) {
            return v2;
        } else if (vertex.equals(v2)) {
            return v1;
        }
        return null;
    }

    /**
     * Return the vertices of the edge
     * 
     * @return
     */
    public Vertex[] vertices() {
        Vertex[] out = new Vertex[2];

        out[0] = v1;
        out[1] = v2;

        return out;
    }
}
