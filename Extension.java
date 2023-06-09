/**
 * Author: Michael Tenkorang
 * Course: CS231
 * Purpose: Implementing a pursuit simulation with graphs
 */

public class Extension {

    public Extension(int n, Double p, boolean multiplePursuers) throws InterruptedException {

        // Create a new graph
        Graph graph = new Graph(n, p);

        if (multiplePursuers) {

            // Create the pursuer and evader
            AbstractPlayerAlgorithm pursuer1 = new MoveTowardsPlayerAlgorithm(graph);
            AbstractPlayerAlgorithm pursuer2 = new MoveTowardsPlayerAlgorithm(graph);
            MoveAwayPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);

            // Have each player choose a starting location
            pursuer1.chooseStart();
            pursuer2.chooseStart();
            // Since the evader has a harder objective, they get to play second
            // and see where the pursuer chose
            evader.chooseStart(pursuer1.getCurrentVertex());

            // Make the display
            GraphDisplay display = new GraphDisplay(graph, pursuer1, pursuer2, evader, 40);
            display.repaint();

            while ((pursuer2.getCurrentVertex() != evader.getCurrentVertex())
                    || (pursuer1.getCurrentVertex() != evader.getCurrentVertex())) {
                Thread.sleep(500);
                pursuer1.chooseNext(evader.getCurrentVertex());
                display.repaint();
                if ((pursuer1.getCurrentVertex() == evader.getCurrentVertex())
                        || (pursuer2.getCurrentVertex() == evader.getCurrentVertex()))
                    break;
                Thread.sleep(500);
                evader.chooseNext(pursuer1.getCurrentVertex(), pursuer2.getCurrentVertex());
                display.repaint();
                if ((pursuer1.getCurrentVertex() == evader.getCurrentVertex())
                        || (pursuer2.getCurrentVertex() == evader.getCurrentVertex()))
                    break;
                Thread.sleep(500);
                pursuer2.chooseNext(evader.getCurrentVertex());
                display.repaint();
                if ((pursuer1.getCurrentVertex() == evader.getCurrentVertex())
                        || (pursuer2.getCurrentVertex() == evader.getCurrentVertex()))
                    break;
                Thread.sleep(500);
                evader.chooseNext(pursuer1.getCurrentVertex(), pursuer2.getCurrentVertex());
                display.repaint();
            }

        } else {
            new Driver(n, p);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int n = 8;
        double p = 0.22;
        if (args.length > 0 && args[0].equals("-m"))
            new Extension(n, p, true);
        else
            new Extension(n, p, false);
    }
}
