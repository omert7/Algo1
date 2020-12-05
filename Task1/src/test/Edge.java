package test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Edge implements Comparable<Edge> {
    public static Set<Set<Node>> allEdges = new HashSet<Set<Node>>();
    Set<Node> nodes;
    int weight;


    public Edge(Node n1, Node n2, int weight) {
        this.nodes = getSetOfNodes(n1, n2);
        this.weight = weight;
        addEdgeToNodes(n1, n2);
        allEdges.add(this.nodes);
    }

    public void addEdgeToNodes(Node n1, Node n2) {
        n1.addEdge(this);
        n2.addEdge(this);

    }

    public static Set<Node> getSetOfNodes(Node n1, Node n2) {
        Set<Node> setOfNodes = new HashSet<Node>();
        setOfNodes.add(n1);
        setOfNodes.add(n2);
        return setOfNodes;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "test.Edge{" +
                "nodes=" + nodes.toString() +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight && Objects.equals(nodes, edge.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes, weight);
    }
}