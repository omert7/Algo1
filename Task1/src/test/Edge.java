package test;

import java.util.Objects;
import java.util.Set;

public class Edge implements Comparable<Edge> {
    Set<Node> nodes;
    int weight;


    public Edge(Node n1, Node n2, int weight) {
        this.nodes = Node.getSetOfNodes(n1, n2);
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