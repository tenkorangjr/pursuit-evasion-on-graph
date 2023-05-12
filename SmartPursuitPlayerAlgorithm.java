
/**Author: Michael Tenkorang
*Course: CS231
*Purpose: Implementing a pursuit simulation with graphs
*/

import java.util.Random;
import java.util.HashMap;
import java.util.List;

public class SmartPursuitPlayerAlgorithm extends AbstractPlayerAlgorithm {
    private Random picker;

    public SmartPursuitPlayerAlgorithm(Graph graph) {
        super(graph);
        picker = new Random();
        curVertex = graph.getVertices().get(picker.nextInt(graph.size()));
    }

    @Override
    public Vertex chooseStart() {
        Vertex out = graph.getVertices().get(picker.nextInt(graph.size()));
        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(other);
        Vertex minVertex = null;

        for (Vertex vertex : graph.getVertices()) {
            if (vertex != other && (minVertex == null || distances.get(vertex) < distances.get(minVertex))) {
                minVertex = vertex;
            }
        }

        curVertex = minVertex;
        return minVertex;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        if (curVertex.adjacentVertices().contains(otherPlayer)) {
            curVertex = otherPlayer;
            return curVertex;
        }

        List<Vertex> shortestPath = graph.shortestPath(curVertex, otherPlayer);
        Vertex nextVertex = null;

        if (shortestPath != null && shortestPath.size() > 1) {
            nextVertex = shortestPath.get(1);
        } else {
            // Choose a random adjacent vertex if path is not found
            List<Vertex> adjacentVertices = curVertex.adjacentVertices();
            if (!adjacentVertices.isEmpty()) {
                nextVertex = adjacentVertices.get(picker.nextInt(adjacentVertices.size()));
            }
        }

        curVertex = nextVertex;
        return curVertex;
    }
}
