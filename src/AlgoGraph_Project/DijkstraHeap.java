package AlgoGraph_Project;

import java.util.ArrayList;
import java.util.List;

public class DijkstraHeap {
    public static void dijkstra_with_heap(Class_Graph classGraph, int source, int destination, int numberNodes) {

        int[] status = new int[numberNodes];
        int[] bandwidth = new int[numberNodes];
        int[] dad = new int[numberNodes];

        int unseen = 0;
        int fringe = 1;
        int in_tree = 2;


        status[source] = in_tree;
        Heap_HDP.initalize(numberNodes);

        Class_Vertex[] vertices = classGraph.getVertices();
        Class_Vertex v1 = vertices[source];
        while (v1 != null) {
            status[v1.getVertex()] = fringe;
            bandwidth[v1.getVertex()] = v1.getEdge_wt();
            dad[v1.getVertex()] = source;
            Heap_HDP.insert(v1.getVertex(), bandwidth[v1.getVertex()]);
            v1 = v1.getNext();
        }

        // updating status of vertices and bandwith.
        while (Heap_HDP.getSize() != 0) {
            int maximumIndex = Heap_HDP.extractmaximum(0);
            status[maximumIndex] = in_tree;
            Class_Vertex maxNode = vertices[maximumIndex];
            while (maxNode != null) {
                if (status[maxNode.getVertex()] == unseen) {
                    status[maxNode.getVertex()] = fringe;
                    bandwidth[maxNode.getVertex()] = Math.min(bandwidth[maximumIndex], maxNode.getEdge_wt());
                    dad[maxNode.getVertex()] = maximumIndex;
                    Heap_HDP.insert(maxNode.getVertex(), bandwidth[maxNode.getVertex()]);
                } else if (status[maxNode.getVertex()] == fringe
                        && bandwidth[maxNode.getVertex()] < Math.min(bandwidth[maximumIndex], maxNode.getEdge_wt())) {
                    Heap_HDP.delete(maxNode.getVertex());
                    bandwidth[maxNode.getVertex()] = Math.min(bandwidth[maximumIndex], maxNode.getEdge_wt());
                    dad[maxNode.getVertex()] = maximumIndex;
                    Heap_HDP.insert(maxNode.getVertex(), bandwidth[maxNode.getVertex()]);
                }
                maxNode = maxNode.getNext();
            }
        }

        //bandwidth from source to destination
        System.out.print("Max Bandwidth:  " + bandwidth[destination] + "\npath: ");
        while (destination != source) {
            System.out.print(destination + " <-- ");
            destination = dad[destination];
        }
        System.out.println(destination);
    }

}
