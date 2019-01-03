package graph;

/**
 * @author Ji YongGuang.
 * @date 19:56 2018/12/30.
 * @description 求无权图的联通分量
 */
public class Components {

    private Graph graph;
    private boolean[] visited;  // 记录dfs的过程中节点被访问的情况
    private int ccount;         // 记录联通分量个数
    private int[] id;           // 每个节点所对应的联通分量标记

    // 图的深度优先遍历
    void dfs(int v) {
        visited[v] = true;
        id[v] = ccount;

        for (int i : graph.adj(v)) {
            if (!visited[i])
                dfs(i);
        }
    }

    // 构造函数, 求出无权图的联通分量
    public Components(Graph graph) {

        // 算法初始化
        this.graph = graph;
        visited = new boolean[graph.v()];
        id = new int[graph.v()];
        ccount = 0;
        for (int i = 0; i < graph.v(); i++) {
            visited[i] = false;
            id[i] = -1;
        }

        // 求图的联通分量
        for (int i = 0; i < graph.v(); i++)
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
    }

    // 返回图的联通分量个数
    int count() {
        return ccount;
    }

    // 查询点v和点w是否联通
    boolean isConnected(int v, int w) {
        assert v >= 0 && v < graph.v();
        assert w >= 0 && w < graph.v();
        return id[v] == id[w];
    }
}
