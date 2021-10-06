import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-06 15:08
 */
public class KrusKalDemo {
    public static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {0, 12, MAX, MAX, MAX, 16, 14},
                {12, 0, 10, MAX, MAX, 7, MAX},
                {MAX, 10, 0, 3, 5, 6, MAX},
                {MAX, MAX, 3, 0, 4, MAX, MAX},
                {MAX, MAX, 5, 4, 0, 2, 8},
                {16, 7, 6, MAX, 2, 0, 9},
                {14, MAX, MAX, MAX, 8, 9, 0}};
        Group group = new Group(data, weight);
        group.printGroup();
        Group.Edge[] edges = group.getEdges();
        System.out.println(Arrays.toString(edges));
        group.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
    }
}

class Group {
    int numOfVertex;//节点数量
    char[] vertex;//结点数组
    int[][] weight;//路径权值
    int numOfEdges;//边的数目

    /**
     * 构造器
     *
     * @param vertex 结点数组
     * @param weight 邻接矩阵
     */
    public Group(char[] vertex, int[][] weight) {
        this.numOfVertex = vertex.length;
        this.vertex = new char[vertex.length];
        this.weight = new int[vertex.length][vertex.length];
        //复制初始化
        for (int i = 0; i < this.numOfVertex; i++) {
            this.vertex[i] = vertex[i];
            for (int j = 0; j < this.numOfVertex; j++) {
                this.weight[i][j] = weight[i][j];
                if (this.weight[i][j] != Integer.MAX_VALUE) {
                    numOfEdges++;
                }
            }
        }
        //遍历数组的时候会出现重复叠加
        this.numOfEdges -= this.numOfVertex;
        this.numOfEdges /= 2;
    }

    /**
     * 打印图
     */
    public void printGroup() {
        for (int i = 0; i < this.numOfVertex; i++) {
            for (int j = 0; j < this.numOfVertex; j++) {
                System.out.printf("%-12d", this.weight[i][j]);
            }
            System.out.println();
        }
    }

    //返回目标结点的下标
    public int getVertexPosition(char target) {
        for (int i = 0; i < this.numOfVertex; i++) {
            if (this.vertex[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 利用插入排序，对Edge数组进行排序（从小到大）
     */
    public void sortEdges(Edge[] edges) {
        if (edges == null || edges.length <= 1) {
            System.out.println("数组长度小于等于1，没必要排序");
            return;
        }
        int insertIndex = 0;
        Edge insertVal = edges[0];
        for (int i = 1; i < edges.length; i++) {
            insertIndex = i;
            insertVal = edges[i];
            while (insertIndex - 1 >= 0 && insertVal.weight < edges[insertIndex - 1].weight) {
                edges[insertIndex] = edges[--insertIndex];
            }
            edges[insertIndex] = insertVal;
        }
    }

    /**
     * @return 返回边数组
     */
    public Edge[] getEdges() {
        int index = 0;
        Edge[] edges = new Edge[this.numOfEdges];
        for (int i = 0; i < this.numOfVertex; i++) {
            for (int j = i + 1; j < this.numOfVertex; j++) {
                if (this.weight[i][j] != Integer.MAX_VALUE) {
                    edges[index++] = new Edge(this.vertex[i], this.vertex[j], this.weight[i][j]);
                }
            }
        }
        return edges;
    }

    public int getEnd(int[] ends, int target) {
        while (ends[target] != 0) {
            target = ends[target];
        }
        return target;
    }

    class Edge {
        char start;//边的起点
        char end;//边的重点
        int weight;//边的权重

        public Edge(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + start +
                    "——>" + end +
                    " weight=" + weight + ")";
        }
    }
}
