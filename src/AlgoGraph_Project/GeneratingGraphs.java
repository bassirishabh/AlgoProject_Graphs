package AlgoGraph_Project;

import java.util.*;
import java.lang.*;

public class GeneratingGraphs {
    private static final Random random = new Random();

    private static void denseGraph(Class_Graph classGraph, int wtLimit, int degree, int numberNodes) {

        List<Class_Edge> classEdges = classGraph.getEdges();
        int wt, dest;
        int currDeg;

        for (int i = 0; i < numberNodes; i++) {
            currDeg = classGraph.getVertices()[i].getDeg();
            while (currDeg < degree) {
                dest = random.nextInt(numberNodes);
                if (!adjList(classGraph.getVertices()[i], dest)
                        && i != dest
                ) {
                    wt = random.nextInt(wtLimit) + 1;
                    classEdges.add(new Class_Edge(i, dest, wt));
                    classGraph.getVertices()[i] = new Class_Vertex(dest, wt,
                            classGraph.getVertices()[i].getDeg() + 1, classGraph.getVertices()[i]);
                    classGraph.getVertices()[dest] = new Class_Vertex(i, wt,
                            classGraph.getVertices()[dest].getDeg() + 1, classGraph.getVertices()[dest]);
                    currDeg++;
                }
            }
        }
    }
    //	Forming Circular Graph
    private static void generatingGraph(Class_Graph classGraph, int wtLimit, int numberNodes) {

        int randomWeight;
        List<Class_Edge> classEdges = classGraph.getEdges();
        for (int i = 0; i < numberNodes; i++) {
            randomWeight = random.nextInt(wtLimit) + 1;

            // add vertex in adj list
            classEdges.add(new Class_Edge(i, (i + 1) % numberNodes, randomWeight));
            if (classGraph.getVertices()[i] == null) {
                classGraph.getVertices()[i] = new Class_Vertex((i + 1) % numberNodes, randomWeight, 1, null);
            } else {
                Class_Vertex newClassVertex = new Class_Vertex((i + 1) % numberNodes, randomWeight,
                        classGraph.getVertices()[i].getDeg() + 1,
                        classGraph.getVertices()[i]);
                classGraph.getVertices()[i] = newClassVertex;
            }

            if (classGraph.getVertices()[(i + 1) % numberNodes] == null) {
                classGraph.getVertices()[(i + 1) % numberNodes] = new Class_Vertex(i % numberNodes, randomWeight, 1, null);
            } else {
                Class_Vertex newClassVertex = new Class_Vertex(i % numberNodes, randomWeight,
                        classGraph.getVertices()[(i + 1) % numberNodes].getDeg() + 1,
                        classGraph.getVertices()[(i + 1) % numberNodes]);
                classGraph.getVertices()[(i + 1) % numberNodes] = newClassVertex;
            }
        }
    }
    public static void graphGeneration(Class_Graph classGraph, int wtLimit, int degree, String type, int numberNodes) {
        generatingGraph(classGraph, wtLimit, numberNodes);
        if (type == "SPARSE") {
            sparseGraph(classGraph, wtLimit, degree,numberNodes);
        } else {
            denseGraph(classGraph, wtLimit, degree,numberNodes);
        }
    }

    static void generateMST(Class_Graph classGraph, int numberNodes) {
        MakeUnionFind unionFind = new MakeUnionFind(numberNodes);
        Map<Integer, List<Class_Vertex>> maxST = classGraph.getMaxST();

        List<Class_Edge> classEdgeList = classGraph.getEdges();
        //sort the edges
        unionFind.sort(classEdgeList);

        //Getting MST by Union and Find Operations
        for (int i = 0; i < numberNodes; i++) {
            maxST.put(i, new ArrayList<>());
        }
        for (Class_Edge classEdge : classEdgeList) {
            int root1 = unionFind.find(classEdge.getV1());
            int root2 = unionFind.find(classEdge.getV2());
            if (root1 != root2) {
                unionFind.union(classEdge.getV1(), classEdge.getV2());
                maxST.get(classEdge.getV1()).add(new Class_Vertex(classEdge.getV2(), classEdge.getEdge_wt(), 1, null));
                maxST.get(classEdge.getV2()).add(new Class_Vertex(classEdge.getV1(), classEdge.getEdge_wt(), 1, null));
            }
        }
    }

    private static boolean adjList(Class_Vertex classVertex, int dest) {
        while (classVertex != null) {
            if (classVertex.getVertex() == dest) {
                return true;
            } else {
                classVertex = classVertex.getNext();
            }
        }
        return false;
    }
    public static void sparseGraph(Class_Graph classGraph, int wtLimit, int deg, int numberNodes) {

        Class_Vertex[] vertices = classGraph.getVertices();
        List<Class_Edge> classEdges = classGraph.getEdges();
        int avSum_edge = numberNodes * deg / 2;
        int curSum_edge = numberNodes;

        int src, dest;
        while (curSum_edge < avSum_edge) {
            src = random.nextInt(numberNodes);
            dest = random.nextInt(numberNodes);

            if (!adjList(vertices[src], dest) && src != dest) {
                // random edge weight generator variable
                int weight = random.nextInt(wtLimit) + 1;
                classEdges.add(new Class_Edge(src, dest, weight));
                vertices[src] = new Class_Vertex(dest, weight, vertices[src].getDeg() + 1, vertices[src]);
                vertices[dest] = new Class_Vertex(src, weight, vertices[dest].getDeg() + 1, vertices[dest]);
                curSum_edge++;
            }
        }
    }
}
