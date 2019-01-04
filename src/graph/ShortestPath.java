package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Ji YongGuang.
 * @date 16:53 2019/1/3.
 * @description 最短路径 BFS
 */
public class ShortestPath {

    private Graph graph;
    // 起始点
    private int s;
    // 记录bfs的过程中节点是否被访问
    private boolean[] visited;
    // 记录bfs的过程中被访问到的节点的前驱节点
    private int[] from;
    // 记录路径中节点的次序。ord[i]表示i节点在路径中的次序。
    private int[] ord;

    public ShortestPath(Graph graph, int s) {

        this.graph = graph;
        assert s >= 0 && s < graph.v();

        this.s = s;
        this.visited = new boolean[graph.v()];
        this.from = new int[graph.v()];
        this.ord = new int[graph.v()];

        for (int i = 0; i < graph.v(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        ord[s] = 0;
        // from 根节点的from不动

        while (!queue.isEmpty()) {
            int v = queue.remove();
            graph.adj(v).forEach(item -> {
                if (!visited[item]) {
                    queue.add(item);
                    visited[item] = true;
                    from[item] = v;
                    ord[item] = ord[v] + 1;
                }
            });
        }
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

        while (!stack.isEmpty())
            res.add(stack.pop());

        return res;
    }

    // 打印出从s点到w点的路径
    public void showPath(int w) {

        ArrayList<Integer> path = path(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i == path.size() - 1)
                System.out.println();
            else
                System.out.print(" -> ");
        }
    }

    // 查看从s点到w点的最短路径长度
    // 若从s到w不可达，返回-1(默认)
    public int length(int w) {
        assert w >= 0 && w < graph.v();
        return ord[w];
    }
}
