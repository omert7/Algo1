package test;

import java.util.Objects;
import java.util.PriorityQueue;

public class Node {
    PriorityQueue<Edge> edgesQueue;
    static int counter = 0;
    int id;

    public Node() {
        this.id = counter;
        edgesQueue = new PriorityQueue<Edge>(1, (e1, e2) -> e1.weight - e2.weight);
        ;
        counter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    void addEdge(Edge e) {
        edgesQueue.add(e);
    }

    @Override
    public String toString() {
        return "test.Node id= " + id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id && Objects.equals(edgesQueue, node.edgesQueue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edgesQueue, id);
    }
}
