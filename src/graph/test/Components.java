package graph.test;

import graph.Graph;

/**
 * @author Ji YongGuang.
 * @date 13:30 2019/1/3.
 * @description 求无权图(稀疏 ， 稠密图)的连通分量
 */
public class Components {

    private Graph graph;
    // 记录各节点的访问情况
    private boolean[] visited;
    // 记录连通分量个数
    private int ccount;
    // 记录各节点所在的连通分量
    private int[] ids;

    private void dfs(int v) {
        visited[v] = true;
        ids[v] = ccount;

        graph.adj(v).forEach(item -> {
            if (!visited[item])
                dfs(item);
        });
    }

    public Components(Graph graph) {

        this.graph = graph;
        this.visited = new boolean[graph.v()];
        this.ccount = 0;
        this.ids = new int[graph.v()];

        for (int i = 0; i < graph.v(); i++) {
            visited[i] = false;
            ids[i] = -1;
        }

        for (int i = 0; i < graph.v(); i++)
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
    }

    // 返回图的联通分量个数
    public int count() {
        return ccount;
    }

    boolean isConnected(int v, int w) {
        assert v >= 0 && v < graph.v();
        assert w >= 0 && w < graph.v();
        return ids[v] == ids[w];
    }
}
