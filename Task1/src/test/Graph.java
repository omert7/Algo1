package test;

import java.util.*;

public class Graph {
    ArrayList<Node> nodes;
    Map<Set<Node>, Edge> edgesMap;

    public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.edgesMap = new HashMap<>();
        this.nodes = nodes;
        for (Edge e : edges) {
            addEdgeToMap(e);
        }
    }

    public void addEdgeToMap(Edge e) {
        edgesMap.put(e.nodes, e);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void addNodeIfNotIn(Node n) {
        if (!nodes.contains(n)) {
            nodes.add(n);
        }
    }

    public void clearNodesNeigbours(){
        for(Node n: nodes){
            n.neighbours.clear();
        }
    }

    public void initNodesNeigbours() {
        clearNodesNeigbours();
        for (Edge e : this.edgesMap.values()) {
            Object[] arr = e.nodes.toArray();
            Node n1 = (Node) arr[0];
            Node n2 = (Node) arr[1];
            n1.neighbours.add(n2);
            n2.neighbours.add(n1);
        }
    }

    public void addEdgeToGraph(Edge e) {
        addEdgeToMap(e);
    }


    @Override
    public String toString() {
        return "test.Graph{" +
                "nodes=" + nodes +
                ", edges=" + this.edgesMap.values() +
                '}';
    }

    public Graph PrimMinGraph() {

        ArrayList<Node> minNodes = new ArrayList<>();
        ArrayList<Edge> minEdges = new ArrayList<>();

        Node firstNode = this.nodes.get(0);
        minNodes.add(firstNode);

        Graph minGraph = new Graph(minNodes, minEdges);
        PriorityQueue<Edge> pq = new PriorityQueue<>(this.edgesMap.values());
        PriorityQueue<Edge> pq_b = new PriorityQueue<>();


        while (minGraph.nodes.size() != this.nodes.size() && minGraph.edgesMap.values().size() != this.nodes.size() - 1) {
            while (pq.size() > 0) {
                Edge e = pq.poll();
                Object[] arr = e.nodes.toArray();
                Node n1 = (Node) arr[0];
                Node n2 = (Node) arr[1];

                if (minGraph.nodes.contains(n1) && !minGraph.nodes.contains(n2) || minGraph.nodes.contains(n2) && !minGraph.nodes.contains(n1)) {
                    minGraph.addNodeIfNotIn(n1);
                    minGraph.addNodeIfNotIn(n2);
                    minGraph.addEdgeToGraph(e);
                    pq.addAll(pq_b);
                    pq_b.clear();
                    break;

                } else if (!minGraph.nodes.contains(n1) && !minGraph.nodes.contains(n2)) {
                    pq_b.add(e);
                }

            }

        }

        return minGraph;
    }

    public void removeRedundentEdge(Node u, Node nb) {
        // if reached here the u Node found nb Node that is gray and that means we have a circle
        // we will loop back every parent until we reched the begin of the circle and remove the heaviest edge
        // found in the circle.
        Node end = u;
        Edge edgeTobeRemoved = this.edgesMap.get(Node.getSetOfNodes(u, nb));
        Set<Node> setNodes = Node.getSetOfNodes(u, nb);

        while (end != nb) {
            Node now = end;
            end = end.parent;
            Edge e = this.edgesMap.get(Node.getSetOfNodes(now, end));

            if (e.weight > edgeTobeRemoved.weight) {
                edgeTobeRemoved = e;
                setNodes = Node.getSetOfNodes(now, end);
            }
        }

        this.edgesMap.remove(setNodes);
    }

    public void dfsVisit(Node u) {
        u.color = 1;
        for (Node nb : u.neighbours) {
            if (nb.color == 0) {
                nb.parent = u;
                dfsVisit(nb);

            } else if (nb.color == 1 && u.parent != nb) {

                // if found a neighbour Node that is gray and it is not the u parent then we found the circle
                removeRedundentEdge(u, nb);
            }

        }
        u.color = 2;

    }

    public void breakCircleUsingDfs() {
        /* this method finds using dfs search to look for circle and if found, it breaks it by removing
         the heaviest edge from the graph */

        // init all Nodes neigbours
        this.initNodesNeigbours();

        // coloring all nodes in white
        for (Node s : this.nodes) {
            s.color = 0;
            s.parent = null;
        }

        // Run dfs visit on every white vertex
        for (Node u : this.nodes) {
            if (u.color == 0) {
                dfsVisit(u);
            }
        }

    }

}
