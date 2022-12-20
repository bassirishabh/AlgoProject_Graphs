package AlgoGraph_Project;

import java.util.*;

public class MakeUnionFind {
    int[] dad;
    int[] rank;

    MakeUnionFind(int num_nodes) {

        dad = new int[num_nodes];
        rank = new int[num_nodes];

        for (int i = 0; i < num_nodes; i++) {
            dad[i] = i;
            rank[i] = 0;
        }
    }

    //find root of vertex
    public int find(int v) {
        if (dad[v] != v) {
            dad[v] = find(dad[v]);
        }
        return dad[v];
    }

    //merge shorter to taller tree
    public void union(int n1, int n2) {
        int r1 = find(n1);
        int r2 = find(n2);
        if (rank[r1] < rank[r2]) {
            dad[r1] = r2;
        } else if (rank[r1] > rank[r2]) {
            dad[r2] = r1;
        } else {
            dad[r2] = r1;
            rank[r1]++;
        }
    }

    //rearrange heap
    void heapify(List<Class_Edge> classEdges, int n, int i) {
        int largest = i;
        int leftch = 2 * i + 1;
        int rightch = 2 * i + 2;
        if (leftch < n && classEdges.get(leftch).getEdge_wt()  < classEdges.get(largest).getEdge_wt() ) {
            largest = leftch;
        }
        if (rightch < n && classEdges.get(rightch).getEdge_wt()  < classEdges.get(largest).getEdge_wt() ) {
            largest = rightch;
        }
        if (largest != i) {
            Class_Edge t = classEdges.get(i);
            classEdges.set(i, classEdges.get(largest));
            classEdges.set(largest, t);
            heapify(classEdges, n, largest);
        }
    }

    //Heap Sort - sort edges in decreasing order
    public void sort(List<Class_Edge> classEdges) {
        int num = classEdges.size();
        for (int i = num / 2 - 1; i >= 0; i--) {
            heapify(classEdges, num, i);
        }
        for (int i = num - 1; i > 0; i--) {
            Class_Edge t = classEdges.get(0);
            classEdges.set(0, classEdges.get(i));
            classEdges.set(i, t);
            heapify(classEdges, i, 0);
        }
    }
}
