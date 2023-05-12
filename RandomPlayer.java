
/**Author: Michael Tenkorang
*Course: CS231
*Purpose: Implementing a pursuit simulation with graphs
*/

import java.util.Random;

public class RandomPlayer extends AbstractPlayerAlgorithm {

    Random picker;

    public RandomPlayer(Graph graph) {
        super(graph);
        picker = new Random();
    }

    @Override
    public Vertex chooseStart() {
        Vertex out = graph.getVertices().get(picker.nextInt(0, graph.size()));
        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseStart(Vertex other) {
        this.curVertex = graph.getVertices().get(picker.nextInt(0, graph.size()));
        Vertex out = curVertex;
        while (out == other) {
            out = graph.getVertices().get(picker.nextInt(0, graph.size()));
        }

        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        Vertex out = curVertex;
        while (this.curVertex.equals(out)) {
            out = curVertex.adjacentVertices().get(picker.nextInt(0, curVertex.adjacentVertices().size()));
        }

        this.curVertex = out;
        return out;
    }

    public static void main(String[] args) {
        /*
         * Testing class
         */
    }

}
