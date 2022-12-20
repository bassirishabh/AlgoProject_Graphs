package AlgoGraph_Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Class_Graph {
    private final Class_Vertex[] vertices;
    private final List<Class_Edge> classEdges;
    private final Integer numberNodes;
    private final Map<Integer, List<Class_Vertex>> maxST;

    public Class_Graph(final Integer numberNodes) {
        this.vertices = new Class_Vertex[numberNodes];
        this.classEdges = new ArrayList<>();
        this.numberNodes = numberNodes;
        this.maxST = new HashMap<>(numberNodes);
    }
    public Class_Vertex[] getVertices() { return vertices; }
    public List<Class_Edge> getEdges() { return classEdges; }
    public Integer getNumberNodes() { return numberNodes; }
    public Map<Integer, List<Class_Vertex>> getMaxST() { return maxST; }
}