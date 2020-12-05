package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Generator {

    public static ArrayList<Node> generateNodes(int numOfNodes) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < numOfNodes; i++) {
            Node node = new Node();
            nodes.add(node);

        }
        return nodes;
    }

    public static ArrayList<Edge> generateEdges(ArrayList<Node> nodes, int numOfEdges) {
        /* This Method Generate edges and return ArrayList<test.Edge> */
        ArrayList<Edge> edges = new ArrayList<Edge>();
        Set<Set<Integer>> indexes = generateIndexes(nodes.size(), numOfEdges); // generating indexes

        for (Set<Integer> si : indexes) {
            Random r = new Random(); // generating random for weight of edge
            Object[] arr = si.toArray();

            Edge e = new Edge(nodes.get((int) arr[0]), nodes.get((int) arr[1]), r.nextInt(100) + 1);
            edges.add(e);
        }

        return edges;
    }

    public static Set<Set<Integer>> generateIndexes(int range, int num) {

        Set<Set<Integer>> indexes = new HashSet<Set<Integer>>();

        for (int i = 0; i < range-1; i++) {
            Set<Integer> set = new HashSet<Integer>();
            set.add(i);
            set.add(i+1);
            indexes.add(set);
        }

        for (int i = 0; i < num-range+1; i++) {
            Set<Integer> set = getSetOfInts(range);
            while (indexes.contains(set)) {
                set = getSetOfInts(range);
            }
            indexes.add(set);
        }

        return indexes;
    }

    public static Set<Integer> getSetOfInts(int range) {
        /* Getting random set of 2 integers in some range */
        Set<Integer> set = new HashSet<Integer>();
        Random r = new Random();
        int first = r.nextInt(range);
        int second = r.nextInt(range);

        while (second == first) {
            second = r.nextInt(range);
            first = r.nextInt(range);
        }
        set.add(first);
        set.add(second);

        return set;
    }

}
