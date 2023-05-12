
/**Author: Michael Tenkorang
*Course: CS231
*Purpose: Implementing a pursuit simulation with graphs
*/

import java.util.Random;
import java.util.LinkedList;
import java.util.HashMap;

public class MoveAwayPlayerAlgorithm extends AbstractPlayerAlgorithm {

    Random picker;

    public MoveAwayPlayerAlgorithm(Graph graph) {
        super(graph);
        picker = new Random();
        curVertex = ((LinkedList<Vertex>) graph.getVertices()).get(picker.nextInt(graph.size()));
    }

    @Override
    public Vertex chooseStart() {
        Vertex out = graph.getVertices().get(picker.nextInt(0, graph.size()));
        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        Vertex maxVertex = null;

        for (Vertex vertex : graph.getVertices()) {
            if (maxVertex == null) {
                maxVertex = vertex;
            } else if (distances.get(vertex) > distances.get(maxVertex) && vertex != other) {
                maxVertex = vertex;
            }
        }

        curVertex = maxVertex;
        return maxVertex;
    }

    /**
     * Chooses the start on a multiplayer board and returns it.
     * 
     * @param other
     * @param other2
     * @return
     */
    public Vertex chooseStart(Vertex other, Vertex other2) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        HashMap<Vertex, Double> distances2 = graph.distanceFrom(other2);
        Vertex maxVertex = null;

        for (Vertex vertex : graph.getVertices()) {
            if (maxVertex == null) {
                maxVertex = vertex;
            } else if (!vertex.equals(other) && !vertex.equals(other2)
                    && (distances.get(maxVertex) < distances.get(vertex)
                            || distances2.get(maxVertex) < distances2.get(vertex))) {
                maxVertex = vertex;
            }
        }

        curVertex = maxVertex;
        return maxVertex;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        Vertex maxNeighbor = null;

        for (Vertex vertex : curVertex.adjacentVertices()) {
            if (maxNeighbor == null) {
                maxNeighbor = vertex;
            } else if (!vertex.equals(otherPlayer) && distances.get(maxNeighbor) < distances.get(vertex)) {
                maxNeighbor = vertex;
            }
        }

        curVertex = maxNeighbor;
        return curVertex;
    }

    /**
     * Chooses the next vertex to go in a multiplayer mode and returns it.
     * 
     * @param otherPlayer
     * @param otherPlayer2
     * @return
     */
    public Vertex chooseNext(Vertex otherPlayer, Vertex otherPlayer2) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        HashMap<Vertex, Double> distances2 = graph.distanceFrom(otherPlayer2);
        Vertex maxNeighbor = null;

        for (Vertex vertex : curVertex.adjacentVertices()) {
            if (maxNeighbor == null) {
                maxNeighbor = vertex;
            } else if (!vertex.equals(otherPlayer) && !vertex.equals(otherPlayer2)
                    && (distances.get(maxNeighbor) < distances.get(vertex)
                            || distances2.get(maxNeighbor) < distances2.get(vertex))) {
                maxNeighbor = vertex;
            }
        }

        curVertex = maxNeighbor;
        return curVertex;
    }

}
