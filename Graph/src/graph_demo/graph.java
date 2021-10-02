package graph_demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-02 22:59
 */
public class graph {
    private int[][] edges;
    private ArrayList<String> vertexList;
    private int numOfEdges;
    private boolean[] isVisited;

    public static void main(String[] args) {
        graph g = new graph(5);
        g.insertVertes("A");
        g.insertVertes("B");
        g.insertVertes("C");
        g.insertVertes("D");
        g.insertVertes("E");
        g.insertEdges(0, 1, 1);
        g.insertEdges(0, 2, 1);
        g.insertEdges(0, 3, 1);
        g.insertEdges(1, 2, 1);
        g.insertEdges(3, 4, 1);
        g.printGraph();
        g.dfs();
    }

    public graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    /**
     * 深度优先遍历
     */
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param isVisited 判断结点是否被访问过
     * @param row       结点
     */
    private void dfs(boolean[] isVisited, int row) {
        System.out.print(getVertexByIndex(row) + "——>");
        isVisited[row] = true;
        int w = getFirstNeighbor(row);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(row, w);
        }
    }

    /**
     * 返回下一个邻接节点
     *
     * @param index    行号
     * @param preIndex 前一个邻接节点的下标
     * @return 返回下一个邻接节点的下标或者-1
     */
    private int getNextNeighbor(int index, int preIndex) {
        for (int i = preIndex + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到第一个邻接节点
     *
     * @param index 行号
     * @return 返回第一个邻接节点的下标或者-1
     */
    private int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 插入结点
     *
     * @param str 插入结点的结点内容
     */
    public void insertVertes(String str) {
        vertexList.add(str);
    }

    /**
     * 返回指定下标的值
     *
     * @param index 将要返回的下标
     * @return 返回指定下标的值
     */
    public String getVertexByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 设置边的关系
     *
     * @param vertex1 结点一
     * @param vertex2 结点二
     * @param weight  权重
     */
    public void insertEdges(int vertex1, int vertex2, int weight) {
        edges[vertex1][vertex2] = weight;
        edges[vertex2][vertex1] = weight;
        numOfEdges++;
    }

    public void printGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

}
