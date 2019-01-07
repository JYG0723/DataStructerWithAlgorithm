package graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Ji YongGuang.
 * @date 16:06 2019/1/3.
 * @description 寻路
 */
public class Path {

    private Graph graph;
    // 图的起始节点
    private int s;
    // 记录图中各节点是否被访问的情况
    private boolean[] visited;
    // 记录图中各节点的前驱节点
    private int[] from;

    private void dfs(int v) {
        visited[v] = true;
        // from[s] 为 -1.
        graph.adj(v).forEach(item -> {
            if (!visited[item]) {// 检索图中未访问过的节点
                from[item] = v;
                dfs(item);
            }
        });
    }

    public Path(Graph graph, int s) {
        assert s >= 0 && s < graph.v();

        this.graph = graph;
        this.s = s;
        visited = new boolean[graph.v()];
        from = new int[graph.v()];
        for (int i = 0; i < graph.v(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        // 寻路
        dfs(s);
    }


    // 查询从s点到w点是否有路径
    public boolean hasPath(int w) {
        assert w >= 0 && w < graph.v();
        return visited[w];
    }

    // 查询从s点到w点的路径, 存放在vec中
    public ArrayList<Integer> path(int w) {
        assert hasPath(w);

        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }

        while (!stack.empty())
            res.add(stack.pop());

        return res;
    }

    // 打印出从s点到w点的路径
    public void showPath(int w) {

        ArrayList<Integer> res = path(w);
        res.forEach(item -> {
            System.out.printf("%d -> ", item);
        });
    }
}
