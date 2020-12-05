package test;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Graph {
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }


    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public void addNodeIfNotIn(Node n) {
        if (!nodes.contains(n)) {
            nodes.add(n);
        }
    }

    public void addEdgeToGraph(Node from, Node to, int weight) {
        Edge edge = new Edge(from, to, weight);
        edges.add(edge);

        if (!nodes.contains(from))
            nodes.add(from);

        if (!nodes.contains(to))
            nodes.add(to);
    }


    @Override
    public String toString() {
        return "test.Graph{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }

//    public PriorityQueue<test.Edge> cutWithGraph(test.Graph g) {
//
//        PriorityQueue<test.Edge> pq = new PriorityQueue<test.Edge>(1, (e1, e2) -> e1.weight - e2.weight);
//
//        for (test.Edge e : g.edges) {
//            Object[] arr = e.nodes.toArray();
//            test.Node n1 = (test.Node) arr[0];
//            test.Node n2 = (test.Node) arr[1];
//            if (this.nodes.contains(n1) && !this.nodes.contains(n2) || this.nodes.contains(n2) && !this.nodes.contains(n1)) {
//                if (!this.edges.contains(e) && !pq.contains(e)) {
//                    pq.add(e);
//                }
//            }
//        }
//        return pq;
//    }

    public Graph PrimMinGraph() {
        ArrayList<Node> minNodes = new ArrayList<Node>();
        ArrayList<Edge> minEdges = new ArrayList<Edge>();
        Node firstNode = this.nodes.get(0);
        minNodes.add(firstNode);
        Graph minGraph = new Graph(minNodes, minEdges);

        PriorityQueue<Edge> pq = new PriorityQueue<>(this.edges);
        PriorityQueue<Edge> pq_b = new PriorityQueue<>();


        while (minGraph.nodes.size() != this.nodes.size() && minGraph.edges.size() != this.nodes.size() - 1) {
            while (pq.size() > 0) {
                Edge e = pq.poll();
                Object[] arr = e.nodes.toArray();
                Node n1 = (Node) arr[0];
                Node n2 = (Node) arr[1];
                if (minGraph.nodes.contains(n1) && !minGraph.nodes.contains(n2) || minGraph.nodes.contains(n2) && !minGraph.nodes.contains(n1)) {
                    minGraph.addNodeIfNotIn(n1);
                    minGraph.addNodeIfNotIn(n2);
                    minGraph.edges.add(e);
                    pq.addAll(pq_b);
                    pq_b.clear();
                    break;
                } else if (!minGraph.nodes.contains(n1) && !minGraph.nodes.contains(n2)) {
                    pq_b.add(e);
                }

            }


//            System.out.println("@@@@@@@@@@@@");
//            System.out.println(minGraph.edges.size());
//            System.out.println(this.nodes.size()-1);
//            System.out.println(minGraph);
//            System.out.println("@@@@@@@@@@@@");
//            System.out.println();

//            PriorityQueue<test.Edge> pq = minGraph.cutWithGraph(this);

//            System.out.println("###############");
//            System.out.println(pq);
//            System.out.println("###############");
//            System.out.println();

//            try {
//                test.Edge e = pq.poll();
//                while (minGraph.edges.contains(e)) {
//                    e = pq.poll();
//                }
//                minGraph.edges.add(e);
//                Object[] arr = e.nodes.toArray(); //TODO watch this

//            } catch (NullPointerException exception) {
//                System.out.println("IM HERE");
//                break;
//            }


        }

        return minGraph;
    }
}
