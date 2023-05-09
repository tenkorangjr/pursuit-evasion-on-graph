import java.util.Random;
import java.util.HashMap;
import java.util.LinkedList;

public class MoveTowardsPlayerAlgorithm extends AbstractPlayerAlgorithm{

    Random picker;

    public MoveTowardsPlayerAlgorithm(Graph graph) {
        super(graph);
        picker = new Random();
        curVertex = ((LinkedList<Vertex>)graph.getVertices()).get(picker.nextInt(graph.size()));
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
        Vertex minVertex = null;

        for (Vertex vertex: graph.getVertices()){
            if (minVertex == null){
                minVertex = vertex;
            }
            else if (distances.get(vertex) < distances.get(minVertex) && vertex != other){
                minVertex = vertex;
            }
        }

        curVertex = minVertex;
        return minVertex;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        HashMap<Vertex, Double> distances = graph.distanceFrom(otherPlayer);
        Vertex leastNeighbor = null;

        for (Vertex vertex: curVertex.adjacentVertices()){
            if (leastNeighbor == null){
                leastNeighbor = vertex;
            } else if (distances.get(vertex) < distances.get(leastNeighbor)){
                leastNeighbor = vertex;
            }
        }

        curVertex = leastNeighbor;
        return leastNeighbor;
    }

    public static void main(String[] args) {
        /*
         * Testing class 
         */
    }
    
}
