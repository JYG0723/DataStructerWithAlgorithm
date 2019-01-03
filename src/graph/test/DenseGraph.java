package graph.test;

import graph.Graph;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 13:02 2019/1/3.
 * @description 稠密图 - 邻接矩阵
 */
public class DenseGraph implements Graph {

    private int n;
    private int m;
    private boolean directed;
    private boolean[][] matrix;

    public DenseGraph(int n) {

        assert n >= 0;

        this.n = n;
        this.m = 0;
        this.directed = false;
        this.matrix = new boolean[n][n];
    }

    public DenseGraph(int n, boolean directed) {

        assert n >= 0;

        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.matrix = new boolean[n][n];
    }

    /*返回节点个数*/
    @Override
    public int v() {
        return n;
    }

    /*返回边的个数*/
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

        matrix[v][w] = true;
        if (!directed)
            matrix[w][v] = true;
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v <= n;
        assert w >= 0 && w <= n;
        return matrix[v][w];
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {

        assert v >= 0 && v <= n;

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (matrix[v][i])
                res.add(i);
        return res;
    }
}
