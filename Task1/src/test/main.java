package test;

/* Main:
   1) generate and create 20 nodes and 50 edges *(RANDOMLY)*
   2) init LARGE graph from those edges and nodes
   3) run createMstUsingPrim over the graph and create MST graph
   4) print out the LARGE graph and the MST GRAPH
   5) add a new 'HEAVY' edge to the MST graph (between 2 nodes that didn't had an edge in the LARGE graph between them)
   6) print the 'MST' Graph
   7) now the MST graph is not a proper MST so we run removeRedundantEdgeMst() and find the HEAVY edge and remove her from the graph.
   8) print the 'MST' Graph after removal
   9) add a new 'LIGHT' edge to the MST graph (between 2 nodes that didn't had an edge in the LARGE graph between them)
   10) print the 'MST' Graph
   11) now the MST graph is not a proper MST so we run removeRedundantEdgeMst() and find the 'HEAVY edge in the circle' and remove her from the graph.
   12) print the 'MST' Graph after removal */

import java.util.*;

public class main {

    public static void printStartGraphs(Graph largeGraph ,Graph mst){
        System.out.println(largeGraph);
        System.out.println(mst);
    }

    public static void addEdgeTest(Graph myGraph, Graph mst, int[] newIndexes, int edgeWeight, String edgeName){
        // CREATING HEAVY EDGE

        Edge newEdge = new Edge(myGraph.nodes.get(newIndexes[0]),myGraph.nodes.get(newIndexes[1]),edgeWeight);

        // PRINTING THE NEW HEAVY EDGE
        System.out.println("new "+ edgeName + " = {nodes: " + myGraph.getNodes().get(newIndexes[0]) +
                " , " + myGraph.getNodes().get(newIndexes[1]) + " ], weight="+ edgeWeight +"}");
        mst.addEdgeToGraph(newEdge);// adding the new edge to the MST graph
        System.out.println("GRAPH AFTER ADDING " + edgeName + " EDGE");
        System.out.println(mst);    // printing the graph after adding the edge
        mst.removeRedundantEdgeMst();  // removing the heaviest edge found in the cirle in the graph
        System.out.println("GRAPH AFTER REMOVING '" + edgeName + "' EDGE");
        System.out.println(mst);    // printing the graph after the removal

    }

    public static void main(String[] args) {


        // ************** //  creating a Large graph with 20 nodes and 50 edges
        // *** PART 1 *** //  &
        // ************** //  create an mst graph out of this graph using prim algorithm


        final int numOfEdges = 7;
        final int numOfNodes = 5;


        //  CREATING NODES AND EDGES FOR THE LARGE GRAPH - myGraph
        ArrayList<Node> nodes = Generator.generateNodes(numOfNodes);
        Set<Set<Integer>> indexes = Generator.generateIndexes(nodes.size(), numOfEdges); // generating indexes
        ArrayList<Edge> edges = Generator.generateEdges(nodes, indexes);
        Graph myGraph = new Graph(nodes, edges, "LARGE");

        // CREATING MST GRAPH FROM THE LARGE GRAPH - mst
        Graph mst =  myGraph.createMstUsingPrim();

        // PRINTING THE GRAPHS
        printStartGraphs(myGraph,mst);


        // ************** //  adding an heavy edge to the mst graph ,find it and remove it from the mst
        // *** PART 2 *** //  &
        // ************** //  adding a light edge to the mst graph, and remove the heaviest that found.

        System.out.println("\n**********************************************");
        System.out.println("********** HEAVY EDGE ADDITION TEST **********");
        System.out.println("**********************************************\n");


        // ADDING AN HEAVY EDGE TO THE MST //
        int[] newIndexes = Generator.genIndexThatDoesntExists(indexes,numOfNodes); // generating indexes

        Set<Integer> indexSet = new HashSet<>();
        indexSet.add(newIndexes[0]);
        indexSet.add(newIndexes[1]);
        indexes.add(indexSet);

        // heavy edge test: edge with weight 200
        addEdgeTest(myGraph, mst, newIndexes,200,"Heavy Edge");

        System.out.println("\n**********************************************");
        System.out.println("********** LIGHT EDGE ADDITION TEST **********");
        System.out.println("**********************************************\n");

        // ADDING A light EDGE TO THE MST //
        newIndexes = Generator.genIndexThatDoesntExists(indexes, numOfNodes); // generating indexes

        // light edge test : edge with weight 1
        addEdgeTest(myGraph,mst,newIndexes,1,"Light Edge");


    }

}
