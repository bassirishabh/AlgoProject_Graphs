package AlgoGraph_Project;

import java.util.*;

public class Kruskal {

    static List<Integer> path_maxBW = new ArrayList<>();
    static int maxBW = 0;

    private static void dfs(Map<Integer, List<Class_Vertex>> maxSpanningTree, boolean[] isVisit, int nd, int destination, List<Integer> stPath, int bandwidth) {

        if (path_maxBW != null && !path_maxBW.isEmpty()) { return; }

        if (nd == destination) {
            path_maxBW = new ArrayList<>(stPath);
            maxBW = bandwidth;
            return;
        }

        isVisit[nd] = true;

        maxSpanningTree.get(nd).forEach(ed -> {
            if (!isVisit[ed.getVertex()]) {
                int nxNode = ed.getVertex();
                stPath.add(ed.getVertex());
                dfs(maxSpanningTree, isVisit, nxNode, destination, stPath, Math.min(bandwidth, ed.getEdge_wt()));
                stPath.remove(stPath.size() - 1);
            }
        });
    }

    public static void kruskal(Class_Graph classGraph, int source, int destination, int numberNodes) {

        //Get path by calling dfs on MST
        boolean[] visited = new boolean[numberNodes];

        ArrayList<Integer> path = new ArrayList<>();
        path.add(source);

        maxBW = 0;
        path_maxBW.clear();
        dfs(classGraph.getMaxST(), visited, source, destination, path, Integer.MAX_VALUE);

        System.out.print("Max Bandwidth: " + maxBW + "\npath: ");
        for (int i = path_maxBW.size() - 1; i > 0; i--) {
            System.out.print(path_maxBW.get(i) + " <-- ");
        }
        System.out.println(path_maxBW.get(0));
    }
}
