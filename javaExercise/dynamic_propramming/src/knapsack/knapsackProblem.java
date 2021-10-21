package knapsack;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-04 16:31
 */
public class knapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};//物品重量
        int[] val = {1500, 3000, 2000};//物品价值
        int kanpsackCapacity = 4;//背包容量
        int numOfObject = val.length;//物品种类

        //创建二维数组
        //v[i][j] 表示前i个物品能装入容量为j的背包中的最大价值
        int[][] v = new int[numOfObject + 1][kanpsackCapacity + 1];
        //用于记录放入商品的情况
        int[][] path = new int[numOfObject + 1][kanpsackCapacity + 1];

        //填充数组
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                        //代表放入的是新的物品
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //打印放入商品的情况
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------");

        int row = path.length - 1;
        int lin = path[0].length - 1;
        while (lin >= 1 && row >= 1) {
            if (path[row][lin] == 1) {
                System.out.println("放入第" + row + "个商品");
                lin -= weight[row - 1];
            }
            row--;
        }
        //打印数组
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
