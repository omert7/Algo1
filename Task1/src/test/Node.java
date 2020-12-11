package test;

/* Node class, represents a vertex in Graph.
*  every node has an id,parent,color and neigbours.
*  when node created all is null/0 except the node_id which get his own value
* */

import java.util.*;

public class Node {

    static int counter = 0;

    int id; // unique id given when Node created , every Node created the counter goes ++;
    int color; //white=0,gray=1,black=2
    Node parent; // parent Node when running bfs/dfs ...
    Set<Node> neighbours; // the Node neighbours in graph , init when graph call Graph.initNodeNeigbours

    public Node() {
        // Basic Constructor
        this.id = counter;
        neighbours = new HashSet<>();
        counter++;
    }

    public static Set<Node> getSetOfNodes(Node n1, Node n2) {
        // general method for gathering 2 nodes in a set
        Set<Node> setOfNodes = new HashSet<>();
        setOfNodes.add(n1);
        setOfNodes.add(n2);
        return setOfNodes;
    }

    @Override
    public String toString() {
        return "Node id=" + id;
    }


    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
