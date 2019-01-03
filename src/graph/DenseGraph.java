package graph;

import java.util.ArrayList;

/**
 * @author Ji YongGuang.
 * @date 17:18 2018/12/30.
 * @description 稠密图 - 邻接矩阵
 */
public class DenseGraph implements Graph{

    /*node节点数*/
    private int n;
    /*edge边数*/
    private int m;
    /*是否为有向图*/
    private boolean directed;
    /*矩阵*/
    private boolean[][] matrix;

    public DenseGraph(int n, boolean directed) {

        assert n >= 0;

        this.n = n;
        this.m = 0;// 初始时各节点不互连通
        this.directed = directed;
        matrix = new boolean[n][n];
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

    /*向图中添加一条边*/
    @Override
    public void addEdge(int v, int w) {

        assert v >= 0 && v <= n;
        assert w >= 0 && w <= n;

        if (hasEdge(v, w)) {// 自动处理了平行边时间复杂度O(1)。
            // 仅在邻接矩阵中如此高效，邻接表不可以这么判断，否则时间复杂度会上升到O(n)
            return;
        }

        matrix[v][w] = true;
        if (!directed) // 如果不是有向图s
            matrix[w][v] = true;
        m++;
    }

    /*验证图中是否有从v到w的边*/
    @Override
    public boolean hasEdge(int v, int w) {

        assert v >= 0 && v <= n;
        assert w >= 0 && w <= n;
        return matrix[v][w];// matrix[w][v]也可以，因为是无向图且邻接矩阵的原因
    }

    // 显示图的信息
    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    /*返回邻接矩阵一个节点的所有临边*/
    @Override
    public Iterable<Integer> adj(int v) {

        assert v >= 0 && v < n;

        ArrayList<Integer> adjV = new ArrayList<>();
        for (int i = 0; i < n; i++)// 迭代规律，x轴先定y轴渐变
            if (matrix[v][i])// 如果为true则代表有边
                adjV.add(i);
        return adjV;
    }
}
