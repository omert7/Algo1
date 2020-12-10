package test;

import java.util.*;

public class main {

    public static void printStartGraphs(Graph largeGraph ,Graph mst){
        System.out.println("Original test.Graph: ");
        System.out.println(largeGraph);
        System.out.println("MinSpanGraph: ");
        System.out.println(mst);

    }

    public static void addEdgeTest(Graph myGraph, Graph mst, int[] newIndexes, int edgeWeight, String edgeName){
        // CREATING HEAVY EDGE

        Edge newEdge = new Edge(myGraph.nodes.get(newIndexes[0]),myGraph.nodes.get(newIndexes[1]),edgeWeight);

        // PRINTING THE NEW HEAVY EDGE
        System.out.println("new "+ edgeName + " = {nodes: " + myGraph.getNodes().get(newIndexes[0]) +
                " , " + myGraph.getNodes().get(newIndexes[1]) + " ], weight="+ edgeWeight +"}");

        mst.addEdgeToGraph(newEdge);

        System.out.println(mst);

        mst.breakCircleUsingDfs();

        System.out.println(mst);

    }

    public static void main(String[] args) {


        // ************** //  creating a Large graph with 20 nodes and 50 edges
        // *** PART 1 *** //  &
        // ************** //  create an mst graph out of this graph using prim algorithm


        int numOfEdges = 7;
        int numOfNodes = 5;


        //  CREATING NODES AND EDGES FOR THE LARGE GRAPH - myGraph
        ArrayList<Node> nodes = Generator.generateNodes(numOfNodes);
        Set<Set<Integer>> indexes = Generator.generateIndexes(nodes.size(), numOfEdges); // generating indexes
        ArrayList<Edge> edges = Generator.generateEdges(nodes, indexes);
        Graph myGraph = new Graph(nodes, edges);

        // CREATING MST GRAPH FROM THE LARGE GRAPH - mst
        Graph mst =  myGraph.PrimMinGraph();

        // PRINTING THE GRAPHS
        printStartGraphs(myGraph,mst);


        // ************** //  adding an heavy edge to the mst graph ,find it and remove it from the mst
        // *** PART 2 *** //  &
        // ************** //  adding a light edge to the mst graph, and remove the heaviest that found.


        // ADDING AN HEAVY EDGE TO THE MST //
        int[] newIndexes = Generator.genIndexThatDoesntExists(indexes,numOfNodes); // generating indexes

        Set<Integer> indexSet = new HashSet<>();
        indexSet.add(newIndexes[0]);
        indexSet.add(newIndexes[1]);
        indexes.add(indexSet);

        // heavy edge test
        addEdgeTest(myGraph, mst, newIndexes,200,"Heavy Edge");


        // ADDING A light EDGE TO THE MST //
        newIndexes = Generator.genIndexThatDoesntExists(indexes, numOfNodes); // generating indexes

        // light edge test
        addEdgeTest(myGraph,mst,newIndexes,1,"Light Edge");



    }

}
