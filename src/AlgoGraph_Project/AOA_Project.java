package AlgoGraph_Project;

import java.util.*;
import java.lang.*;

import static AlgoGraph_Project.DijkstraHeap.dijkstra_with_heap;
import static AlgoGraph_Project.Dijkstra_withoutHeap.dijkstra_without_heap;
import static AlgoGraph_Project.Kruskal.kruskal;
import static AlgoGraph_Project.GeneratingGraphs.*;

public class AOA_Project {

    static int maxWeight = 5000;
    static int numberGraphs = 5;
    static int numberPairs = 5;

    static final int numberNodes = 5000;

    private static final Random random = new Random();

    public static void sparseGraph() {

        for(int i = 1; i <= numberGraphs; i++){
            long time1, time2, time3, time4, time5, time6, time7, time8,time9,time10;
            int source, destination;

            Class_Graph sparseClassGraph = new Class_Graph(numberNodes);
            System.out.println("\n ---------------------------- Sparse Graph   ---------------------------- "+i);
            time1 = System.currentTimeMillis();
            graphGeneration(sparseClassGraph, maxWeight, 6, "SPARSE",numberNodes);
            time2 = System.currentTimeMillis();
            System.out.println("Sparse Graph Generation " + (time2 - time1) + " milliseconds");

            time3 = System.currentTimeMillis();
            generateMST(sparseClassGraph,numberNodes);
            time4 = System.currentTimeMillis();
            System.out.println("Maximum Spanning Tree Generation for Sparse Graph " + (time4 - time3) + " milliseconds");


            for (int j = 1; j <= numberPairs; j++) {
                source = random.nextInt(numberNodes);
                destination = random.nextInt(numberNodes);
                System.out.println("\n========================== Sparse Graph " + i+ " Pair " + j + " - source: " + source + " destination: " + destination + " ==========================\n");
                time5 = System.currentTimeMillis();
                System.out.println("Algorithm: Dijkstra Without Heap Max BW in Sparse Graph between" + source + " and " + destination);
                dijkstra_without_heap(sparseClassGraph, source, destination, numberNodes);
                time6 = System.currentTimeMillis();
                time7 = System.currentTimeMillis();
                System.out.println("Algorithm: Dijkstra Heap - Max BW in Sparse Graph between " + source + " and " + destination);
                dijkstra_with_heap(sparseClassGraph, source, destination, numberNodes);
                time8 = System.currentTimeMillis();
                time9 = System.nanoTime();
                System.out.println("Algorithm: Kruskal - Max BW in Sparse Graph between " + source + " and " + destination);
                kruskal(sparseClassGraph, source, destination, numberNodes);
                time10 = System.nanoTime();
                System.out.println("Timings for Algorithm: Dijkstra Without Heap - Max BW in Sparse Graph " + (time6 - time5) + " milliseconds");
                System.out.println("Timings for Algorithm: Dijkstra With Heap - Max BW in Sparse Graph " + (time8 - time7) + " milliseconds");
                System.out.println("Timings for Algorithm: Kruskal - Max BW in Sparse Graph " + (time10 - time9) + " nanoseconds");
            }
        }
    }
    public static void denseGraph() {

        for(int i = 1; i <= numberGraphs ;i++) {
            int source, destination;
            long time1, time2, time3, time4, time5, time6, time7, time8, time9, time10;

            System.out.println("\n ---------------------------- Dense Graph   ---------------------------- "+i);

            Class_Graph denseClassGraph = new Class_Graph(numberNodes);
            time1 = System.currentTimeMillis();
            graphGeneration(denseClassGraph, maxWeight, (int) Math.round(numberNodes * 0.165), "DENSE",numberNodes);
            time2 = System.currentTimeMillis();
            System.out.println("Dense Graph Generation " + (time2 - time1) + " milliseconds");

            time3 = System.currentTimeMillis();
            generateMST(denseClassGraph,numberNodes);
            time4 = System.currentTimeMillis();
            System.out.println("Maximum Spanning Tree Generation for Dense Graph " + (time4 - time3) + " milliseconds");

            for (int j = 1; j <= numberPairs; j++) {
                source = random.nextInt(numberNodes);
                destination = random.nextInt(numberNodes);
                System.out.println("\n========================== Dense Graph " + i+ " Pair " + j + " - source: " + source + " destination: " + destination + " ==========================\n");
                time5 = System.currentTimeMillis();
                System.out.println("Algorithm: Dijkstra Without Heap Max BW in Dense Graph between" + source + " and " + destination);
                dijkstra_without_heap(denseClassGraph, source, destination, numberNodes);
                time6 = System.currentTimeMillis();
                time7 = System.currentTimeMillis();
                System.out.println("Algorithm: Dijkstra Heap - Max BW in Dense Graph between " + source + " and " + destination);
                dijkstra_with_heap(denseClassGraph, source, destination, numberNodes);
                time8 = System.currentTimeMillis();
                time9 = System.nanoTime();
                System.out.println("Algorithm: Kruskal - Max BW in Dense Graph between " + source + " and " + destination);
                kruskal(denseClassGraph, source, destination, numberNodes);
                time10 = System.nanoTime();
                System.out.println("Timings for Algorithm: Dijkstra Without Heap - Max BW in Dense Graph " + (time6 - time5) + " milliseconds");
                System.out.println("Timings for Algorithm: Dijkstra With Heap - Max BW in Dense Graph " + (time8 - time7) + " milliseconds");
                System.out.println("Timings for Algorithm: Kruskal - Max BW in Dense Graph " + (time10 - time9) + " nanoseconds");

            }
        }
    }

    public static void main(String[] args) {
        sparseGraph();
        System.out.println("\n\n************************************************************************************************************************\n\n");
        denseGraph();
    }
}
