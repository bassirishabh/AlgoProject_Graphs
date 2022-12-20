package AlgoGraph_Project;

public class Class_Edge {
    private final int edge_wt;
    private final int v1;
    private final int v2;

    public Class_Edge(final int v1, final int v2, final int edge_wt) {
        this.edge_wt = edge_wt;
        this.v1 = v1;
        this.v2 = v2;
    }
    public int getEdge_wt() {
        return edge_wt;
    }
    public int getV1() {
        return v1;
    }
    public int getV2() {
        return v2;
    }

}