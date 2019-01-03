package graph;

/**
 * @author Ji YongGuang.
 * @date 19:57 2018/12/30.
 * @description
 */
public interface Graph {

    int v();

    int e();

    void addEdge(int v, int w);

    boolean hasEdge(int v, int w);

    void show();

    Iterable<Integer> adj(int v);
}
