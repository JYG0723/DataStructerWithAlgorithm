package graph;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 17:19 2018/12/30.
 * @description 稀疏图 - 邻接表
 */
public class SparseGraph implements Graph{

    // 节点数
    private int n;
    // 边数
    private int m;
    // 是否有向图
    private boolean directed;
    // 表
    private ArrayList<Integer>[] table;

    public SparseGraph(int n, boolean directed) {

        assert n >= 0;

        this.n = n;
        this.m = 0;
        this.directed = directed;
        table = new ArrayList[n];
        for (int i = 0; i < table.length; i++) {
            table[i] = new ArrayList<>(n);
        }
    }

    @Override
    public int v() {
        return n;
    }

    @Override
    public int e() {
        return m;
    }

    @Override
    public void addEdge(int v, int w) {// 默认暂时不处理平行边的情况

        assert v >= 0 && v <= n;
        assert w >= 0 && w <= n;

        table[v].add(w);
        if (v != w && !directed)// 添加的边不是自环边(v!=w)且如果不是有向图，那么应该邻接表的两个集合中都有这个边
            table[w].add(v);
        m++;
    }

    /*验证图中是否有从v到w的边*/
    @Override
    public boolean hasEdge(int v, int w) {// 如果每次addEdge都判断的话addEdge函数也会变成时间复杂度为O(n)的函数
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        for (int i = 0; i < table[v].size(); i++) {
            if (table[v].get(i) == w)
                return true;
        }
        return false;
    }

    /*返回邻接表中一个节点的所有临边*/
    // 稀疏图及邻接表在插入一条边时的处理有可能会有平行边的问题，但是在返回某个节点的邻接点时的复杂度却是O(1)时间复杂度的。
    @Override
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v <= n;
        return table[v];
    }

    // 显示图的信息
    @Override
    public void show() {

        for (int i = 0; i < table.length; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < table[i].size(); j++)
                System.out.print(table[i].get(j) + "\t");
            System.out.println();
        }
    }
}
