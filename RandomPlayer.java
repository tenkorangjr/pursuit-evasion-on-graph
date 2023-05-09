import java.util.Random;

public class RandomPlayer extends AbstractPlayerAlgorithm{

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
        Vertex out = curVertex;
        while (this.curVertex.equals(out) && out != other){
            out = graph.getVertices().get(picker.nextInt(0, graph.size()));
        }

        this.curVertex = out;
        return out;
    }

    @Override
    public Vertex chooseNext(Vertex otherPlayer) {
        Vertex out = curVertex;
        while (this.curVertex.equals(out)){
            out = graph.getVertices().get(picker.nextInt(0, graph.size()));
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
