import java.util.*;

import javafx.util.Pair;

public class Graph {
    public ArrayList<Vertex> Vertices = new ArrayList<>();

    public Vertex addvertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

    public void addvertex(Vertex v) {
        Vertices.add(v);
    }

    public Vertex getvertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    public void newedge(Vertex from, Vertex to, int dist, int tim) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = tim;
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex zink) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();
        Vertex a;

        // initialize arrays
        for (Vertex v : Vertices) {
            DistanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
        }

        DistanceMap.put(source, 0);

        while (!DistanceMap.isEmpty()) {
            a = getmin(DistanceMap);

            if (a == null) {
                return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(zink), PredecessorMap));
            }

            ArrayList<Edge> edges = a.getOutEdges();

            for (Edge e : edges) {
                if (DistanceMap.containsKey(e.getTovertex())) {
                    Integer distance_edge = DistanceMap.get(a) + e.distance;
                    if (distance_edge < DistanceMap.get(e.getTovertex())) {
                        DistanceMap.put(e.getTovertex(), distance_edge);
                        PredecessorMap.put(e.getTovertex(), a);
                        System.out.println(" Predecessor pairing : " + e.getTovertex().Name + ", " + a.Name);
                    }
                }
            }
            DistanceMap.remove(a);
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(zink), PredecessorMap));
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex zink) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> TimeMap = new HashMap<>();
        Vertex a;

        // initialize arrays
        for (Vertex v : Vertices) {
            TimeMap.put(v, 1000);
            PredecessorMap.put(v, null);
        }

        TimeMap.put(source, 0);

        System.out.println("\n");

        while (!TimeMap.isEmpty()) {
            a = getmin(TimeMap);

            if (a == null) {
                return (new Pair<Integer, Map<Vertex, Vertex>>(TimeMap.get(zink), PredecessorMap));
            }

            ArrayList<Edge> edges = a.getOutEdges();

            for (Edge e : edges) {
                if (TimeMap.containsKey(e.getTovertex())) {
                    Integer time_edge = TimeMap.get(a) + e.time;
                    if (time_edge < TimeMap.get(e.getTovertex())) {
                        TimeMap.put(e.getTovertex(), time_edge);
                        PredecessorMap.put(e.getTovertex(), a);
                        System.out.println(" Predecessor pairing : " + e.getTovertex().Name + ", " + a.Name);
                    }
                }
            }

            TimeMap.remove(a);

        }

        return (new Pair<Integer, Map<Vertex, Vertex>>(TimeMap.get(zink), PredecessorMap));
    }

    public Vertex getmin(Map<Vertex, Integer> qmap) {
        Vertex vertex = null;
        int infinity_value = 1000;

        for (Map.Entry<Vertex, Integer> entry : qmap.entrySet()) {
            Vertex current_vertex_iteration = entry.getKey();
            int current_vertex_value = entry.getValue();
            if (current_vertex_value < infinity_value) {
                vertex = current_vertex_iteration;
                infinity_value = current_vertex_value;
            }
        }
        return vertex;
    }

}


class Vertex {
    public String Name;
    public ArrayList<Edge> OutEdges = new ArrayList<>();

    public Vertex(String id) {
        Name = id;
    }

    public void addOutEdge(Edge outedge) {
        OutEdges.add(outedge);
    }

    public ArrayList<Edge> getOutEdges() {
        return OutEdges;
    }
}

class Edge {
    private Vertex fromvertex;
    private Vertex tovertex;
    public int distance = 0;
    public int time = 0;

    public Vertex getTovertex() {
        return tovertex;
    }

    public Edge(Vertex from, Vertex to) {
        fromvertex = from;
        tovertex = to;
        fromvertex.addOutEdge(this);
        //If not directional
        tovertex.addOutEdge(this);
    }
}
