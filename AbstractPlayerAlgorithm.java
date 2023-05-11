public abstract class AbstractPlayerAlgorithm {

    Graph graph;
    Vertex curVertex;

    public AbstractPlayerAlgorithm(Graph graph){
        this.graph = graph;
        this.curVertex = new Vertex();
    }

    /**
     * Get the entire graph
     * @return
     */
    public Graph getGraph(){
        return this.graph;
    }

    /**
     * Get the current vertex of the player
     * @return
     */
    public Vertex getCurrentVertex(){
        return this.curVertex;
    }

    /**
     * Set the current Vertex of the player
     * @param vertex
     */
    public void setCurrentVertex(Vertex vertex){
        this.curVertex = vertex;
    }

    /**
     *  Returns a Vertex for the player to start at and updates the current Vertex to that location
     * @return
     */
    public abstract Vertex chooseStart();

    /**
     * Returns a Vertex for the player to start at based on where the other player chose to start. 
     * Updates the current Vertex to the chosen location.
     * @param other
     * @return
     */
    public abstract Vertex chooseStart(Vertex other);

    /**
     * Returns the next vertex to move to depending on the other player's vertex.
     * Updates the current Vertex to the chosen location.
     * @param otherPlayer
     * @return
     */
    public abstract Vertex chooseNext(Vertex otherPlayer);

}
