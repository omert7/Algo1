package test;

/* Generator Class to generate indexes for the graph */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Generator {

    public static ArrayList<Node> generateNodes(int numOfNodes) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numOfNodes; i++) {
            Node node = new Node();
            nodes.add(node);

        }
        return nodes;
    }

    public static ArrayList<Edge> generateEdges(ArrayList<Node> nodes, Set<Set<Integer>> indexes) {
        /* This Method Generate edges and return ArrayList<test.Edge> */
        ArrayList<Edge> edges = new ArrayList<>();

        for (Set<Integer> si : indexes) {
            Random r = new Random(); // generating random for weight of edge
            Object[] arr = si.toArray();

            Edge e = new Edge(nodes.get((int) arr[0]), nodes.get((int) arr[1]), r.nextInt(100) + 1);
            edges.add(e);
        }

        return edges;
    }

    public static Set<Set<Integer>> generateIndexes(int range, int num) {

        Set<Set<Integer>> indexes = new HashSet<>();

        for (int i = 0; i < range-1; i++) {
            Set<Integer> set = new HashSet<>();
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
        Set<Integer> set = new HashSet<>();
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

    public static int[] genIndexThatDoesntExists(Set<Set<Integer>> indexes, int numOfNodes){
        int[] arrr = new int[2];
        Set<Integer> set = Generator.getSetOfInts(numOfNodes);
        while(indexes.contains(set)){ // looking for a set of indexes that doesn't exists in the graph
            set = Generator.getSetOfInts(numOfNodes);
        }

        Object[] arr = set.toArray();
        arrr[0] = (int) arr[0];
        arrr[1] = (int) arr[1];

        return arrr;
    }

}
