import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-06 11:57
 */
public class PrimDemo {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        Group group = new Group(data.length, data, weight);
        group.printGroup();
        Prim(group, 0);
    }

    public static void Prim(Group group, int startVertex) {
        //用于标记是否被访问
        boolean[] isVisited = new boolean[group.numOfVertex];
        //标记当前结点已经被访问过
        isVisited[startVertex] = true;
        int minWeight = 10000;
        int row = 0, lin = 0;
        //numOfVertex个顶点，就有numOfVertex条边
        for (int k = 1; k < group.numOfVertex; k++) {
            //每次都遍历一遍邻接矩阵，找到符合条件的最小权值
            for (int i = 0; i < group.numOfVertex; i++) {
                for (int j = 0; j < group.numOfVertex; j++) {
                    if (isVisited[i] && !isVisited[j] && group.weight[i][j] < minWeight) {
                        //替换最小权值，并记录两个结点的在data中的下标
                        minWeight = group.weight[i][j];
                        row = i;
                        lin = j;
                    }
                }
            }
            //找到最小权值
            System.out.println("<" + group.data[row] + "," + group.data[lin] + ">,权重：" + minWeight);
            isVisited[lin] = true;
            minWeight = 10000;
        }
    }
}

class Group {
    int numOfVertex;//图结点个数
    char[] data;//图结点
    int[][] weight;//结点权重

    public Group(int numOfVertex, char[] data, int[][] weight) {
        this.numOfVertex = numOfVertex;
        this.data = new char[numOfVertex];
        this.weight = new int[numOfVertex][numOfVertex];
//        这种初始化方式可能会修改外部的数组，一般使用复制拷贝
//        this.data=data;
//        this.weight=weight;
        for (int i = 0; i < numOfVertex; i++) {
            this.data[i] = data[i];
            for (int j = 0; j < numOfVertex; j++) {
                this.weight[i][j] = weight[i][j];
            }
        }
    }

    public void printGroup() {
        for (int i = 0; i < this.numOfVertex; i++) {
            System.out.println(Arrays.toString(this.weight[i]));
        }
    }
}
