package graph.test;

import graph.Graph;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 13:21 2019/1/3.
 * @description 稀疏图 - 邻接表
 */
public class SparseGraph implements Graph {

    /*node节点数*/
    private int n;
    /*edge边数*/
    private int m;
    /*是否为有向图*/
    private boolean directed;
    /*邻接表*/
    private ArrayList<Integer>[] table;

    public SparseGraph(int n, boolean directed) {

        assert n >= 0;

        this.n = n;
        this.m = 0;// 初始时各节点不互连通
        this.directed = directed;

        for (int i = 0; i < n; i++)
            table[i] = new ArrayList<>();
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
    public void addEdge(int v, int w) {

        assert v >= 0 && v <= n;
        assert w >= 0 && w <= n;

        if (hasEdge(v, w))
            return;

        table[v].add(w);
        if (v != w && !directed)// 预防自环边
            table[w].add(v);
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {

        assert v >= 0 && v <= n;
        assert w >= 0 && w <= n;

        for (int i = 0; i < table[v].size(); i++)
            if (table[v].get(i) == w)
                return true;
        return false;
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < table[i].size(); j++) {
                System.out.printf(table[i].get(j) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {

        assert v >= 0 && v <= n;
        return table[v];
    }
}
