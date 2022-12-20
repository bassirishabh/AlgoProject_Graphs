package AlgoGraph_Project;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra_withoutHeap {

    public static Class_Vertex MaxFringe(List<Class_Vertex> list_fringes) {
        int index, maxBandwidth;

        Class_Vertex maximumFringe = null;
        maxBandwidth = list_fringes.stream().map(Class_Vertex::getEdge_wt).mapToInt(v -> v).max().getAsInt();

        index = -1;
        for (int j = 0; j < list_fringes.size(); j++) {
            if (list_fringes.get(j).getEdge_wt() == maxBandwidth) {
                index = j;
                maximumFringe = list_fringes.get(j);
                break;
            }
        }
        list_fringes.remove(index);
        return maximumFringe;
    }

    public static void update_fringe(List<Class_Vertex> list_fringes, int v, int update_weight) {
        for (Class_Vertex fringe : list_fringes) {
            if (fringe.getVertex() == v) {
                fringe.setEdge_wt(update_weight);
            }
        }
    }

    public static void dijkstra_without_heap(Class_Graph classGraph, int source, int destination, int numberNodes) {

        int[] status = new int[numberNodes];
        int[] bandwidth = new int[numberNodes];
        int[] dad = new int[numberNodes];

        int unseen = 0;
        int fringe = 1;
        int in_tree = 2;

        Class_Vertex[] vertices = classGraph.getVertices();
        Class_Vertex v1 = vertices[source];

        List<Class_Vertex> fringes = new ArrayList<>();
        status[source] = in_tree;

        while (v1 != null) {
            status[v1.getVertex()] = fringe;
            bandwidth[v1.getVertex()] = v1.getEdge_wt();
            dad[v1.getVertex()] = source;
            fringes.add(v1);
            v1 = v1.getNext();
        }

        // updating status of vertices and bandWidth.
        while (!fringes.isEmpty()) {
            Class_Vertex maxFringe = MaxFringe(fringes);
            status[maxFringe.getVertex()] = in_tree;
            Class_Vertex maxNode = vertices[maxFringe.getVertex()];
            while (maxNode != null) {
                if (status[maxNode.getVertex()] == unseen) {
                    status[maxNode.getVertex()] = fringe;
                    bandwidth[maxNode.getVertex()] = Math.min(bandwidth[maxFringe.getVertex()], maxNode.getEdge_wt());
                    dad[maxNode.getVertex()] = maxFringe.getVertex();
                    fringes.add(new Class_Vertex(maxNode.getVertex(), bandwidth[maxNode.getVertex()], 0, null));
                } else if (status[maxNode.getVertex()] == fringe
                        && bandwidth[maxNode.getVertex()] < Math.min(bandwidth[maxFringe.getVertex()], maxNode.getEdge_wt())) {
                    bandwidth[maxNode.getVertex()] = Math.min(bandwidth[maxFringe.getVertex()], maxNode.getEdge_wt());
                    dad[maxNode.getVertex()] = maxFringe.getVertex();
                    update_fringe(fringes, maxNode.getVertex(), bandwidth[maxNode.getVertex()]);
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
