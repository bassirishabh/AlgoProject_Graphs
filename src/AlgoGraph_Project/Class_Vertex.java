package AlgoGraph_Project;

public class Class_Vertex {
    private final int vertex;
    private final int deg;
    private final Class_Vertex next;
    private int edge_wt;

    public Class_Vertex(int vertex, int edge_wt, int deg, Class_Vertex next) {
        this.vertex = vertex;
        this.deg = deg;
        this.next = next;
        this.edge_wt = edge_wt;
    }
    public int getEdge_wt() { return edge_wt; }
    public void setEdge_wt(int edgeWeight) { this.edge_wt = edgeWeight; }
    public int getDeg() { return deg; }
    public Class_Vertex getNext() { return next; }
    public int getVertex() { return vertex; }
}
