package test;

import java.util.*;

public class main {

    public static void main(String[] args) {
        int numOfEdges = 50;
        int numOfNodes = 20;
        // 0 1 2 3 4
        // 0,1 1,2 2,3 3,4
        ArrayList<Node> nodes = Generator.generateNodes(numOfNodes);
        ArrayList<Edge> edges = Generator.generateEdges(nodes, numOfEdges);

        Graph myGraph = new Graph(nodes, edges);
        Graph minGraph =  myGraph.PrimMinGraph();
        System.out.println("Original test.Graph: ");
        System.out.println(myGraph);
        System.out.println("MinSpanGraph: ");
        System.out.println(minGraph);

        //        PriorityQueue<test.Edge> e1 = new PriorityQueue<>(edges);
//        PriorityQueue<test.Edge> e1_b = new PriorityQueue<>();
//        System.out.println(e1.size());
//        while(e1.size()>0){
//            test.Edge e = e1.poll();
//
//            e1_b.add(e);
//        }

//        System.out.println(e1);
//        System.out.println(e1_b .size());
//        e1_b.addAll(e1);
//        System.out.println(e1_b .size());

        //        System.out.println(e1_b);

    }

}
