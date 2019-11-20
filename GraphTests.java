import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {

        System.out.println(" ");

        System.out.println(" Path (distance and time) analysis for smaller graph.");
        System.out.println(" ");

        // Create graph
        GraphTests TestGraph = new GraphTests();
        Graph g = TestGraph.MakeSmallGraph();

        Vertex source = g.getvertex("A");
        Vertex zink = g.getvertex("F");

        ProcessGraph(g, source, zink);

        System.out.println(" Path (distance and time) analysis for bigger graph.");
        System.out.println(" ");

        // Create graph
        Graph big = TestGraph.BiggerGraph();

        source = big.getvertex("J");
        zink = big.getvertex("F");

        ProcessGraph(big, source, zink);

    }

    public static void ProcessGraph(Graph incomingGraph, Vertex source, Vertex zink){

        Pair<Integer, Map<Vertex, Vertex>> results = incomingGraph.ShortestDistance(source, zink);
        Vertex current = zink;

        ArrayList<Vertex> Path = new ArrayList<>();
        Path.add(zink);

        while ((current != source) && (results.getValue().get(current) != null)) {
            current = results.getValue().get(current);
            Path.add(0, current);
        }

        System.out.print(" Edges path (distance) : ");
        for (Vertex v : Path) {
            System.out.print(v.Name);
            if (v != zink)
                System.out.print(" \u2192  ");
        }

        Pair<Integer, Map<Vertex, Vertex>> result_time = incomingGraph.ShortestTime(source, zink);
        current = zink;

        ArrayList<Vertex> Path_time = new ArrayList<>();
        Path_time.add(zink);

        while ((current != source) && (result_time.getValue().get(current) != null)) {
            current = result_time.getValue().get(current);
            Path_time.add(0, current);
        }

        System.out.print(" Edges path (time)   : ");
        for (Vertex v : Path_time) {
            System.out.print(v.Name);
            if (v != zink)
                System.out.print(" \u2192  ");
        }
        System.out.println("\n");

    }

    public Graph MakeSmallGraph() {
        Graph mygraph = new Graph();
        final Vertex A = mygraph.addvertex("A");
        final Vertex B = mygraph.addvertex("B");
        final Vertex C = mygraph.addvertex("C");
        final Vertex D = mygraph.addvertex("D");
        final Vertex E = mygraph.addvertex("E");
        final Vertex F = mygraph.addvertex("F");

        mygraph.newedge(A, B, 1, 2);
        mygraph.newedge(A, C, 5, 1);
        mygraph.newedge(A, D, 4, 6);
        mygraph.newedge(B, C, 3, 2);
        mygraph.newedge(B, D, 2, 3);
        mygraph.newedge(B, E, 2, 4);
        mygraph.newedge(C, F, 1, 8);
        mygraph.newedge(C, E, 2, 2);
        mygraph.newedge(D, F, 2, 7);
        mygraph.newedge(E, F, 3, 6);

        return mygraph;
    }

    public Graph BiggerGraph() {
        Graph BiggerGraph = new Graph();
        final Vertex A = BiggerGraph.addvertex("A");
        final Vertex B = BiggerGraph.addvertex("B");
        final Vertex C = BiggerGraph.addvertex("C");
        final Vertex D = BiggerGraph.addvertex("D");
        final Vertex E = BiggerGraph.addvertex("E");
        final Vertex F = BiggerGraph.addvertex("F");
        final Vertex G = BiggerGraph.addvertex("G");
        final Vertex H = BiggerGraph.addvertex("H");
        final Vertex I = BiggerGraph.addvertex("I");
        final Vertex J = BiggerGraph.addvertex("J");

        BiggerGraph.newedge(A, B, 10, 0);
        BiggerGraph.newedge(A, D, 20, 0);
        BiggerGraph.newedge(A, E, 20, 0);
        BiggerGraph.newedge(A, F, 5,0);
        BiggerGraph.newedge(A, G, 15,0);
        BiggerGraph.newedge(B, C, 5, 0);
        BiggerGraph.newedge(B, D, 10, 0);
        BiggerGraph.newedge(C, B, 15,0);
        BiggerGraph.newedge(C, D, 5,0);
        BiggerGraph.newedge(D, E, 10,0);
        BiggerGraph.newedge(E, F, 5,0);
        //BiggerGraph.newedge(F, null, 0, 0);
        BiggerGraph.newedge(G, F, 10,0);
        BiggerGraph.newedge(H, A, 5,0);
        BiggerGraph.newedge(H, G, 5,0);
        BiggerGraph.newedge(H, B, 20,0);
        BiggerGraph.newedge(I, H, 20,0);
        BiggerGraph.newedge(I, B, 15,0);
        BiggerGraph.newedge(I, J, 10,0);
        BiggerGraph.newedge(J, B, 5,0);
        BiggerGraph.newedge(J, C, 15,0);

        return BiggerGraph;
    }

    public void printGraph(Graph Vertices) {
        Vertex currentv;

        System.out.println(" ");
        System.out.println(" Adjacency List");
        System.out.println(" ==============");

        for (int fromv = 0; fromv < Vertices.Vertices.size(); fromv++) {

            currentv = Vertices.Vertices.get(fromv);

            System.out.print(" Edges from Vertex : " + currentv.Name);

            for (int tov = 0; tov < currentv.getOutEdges().size(); tov++) {
                if (currentv.Name != currentv.getOutEdges().get(tov).getTovertex().Name)
                    System.out.print(" \u2192 " + currentv.getOutEdges().get(tov).getTovertex().Name);
            }

            System.out.print("\n");

        }
    }
}
