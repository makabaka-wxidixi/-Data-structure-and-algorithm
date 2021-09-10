package maze;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-10 22:13
 * <p>
 * 迷宫游戏
 * 输入一个起始位置，判断是否能达到终点，终点默认右下角
 * 0 代表还没有走过的路，1 代表障碍， 2代表已经走过的路， 3代表无路可走
 */
public class mazeTest {
    static final int ROW = 10;
    static final int LIN = 9;

    public static void main(String[] args) {
        //创建迷宫
        int[][] arr = new int[ROW][LIN];
        //初始化迷宫
        initArr(arr);
        //输入起点，判断是否能够达到终点
        route1(arr, 1, 1);// 查找顺序 下  右  上  左
        int route1 = getStep(arr);//得到路径1的长度
        print(arr);//显示路径1
        System.out.println("-------------------------------------");

        //初始化迷宫
        initArr(arr);
        route2(arr, 1, 1);// 查找顺序 右  下  左  上
        int route2 = getStep(arr);//得到路径2的长度
        print(arr);//显示路径2

        System.out.println(route1);
        System.out.println(route2);
    }

    public static void print(int[][] arr) {
        for (int[] i : arr) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    public static void initArr(int[][] arr) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < LIN; j++) {
                arr[i][j] = 0;
            }
        }
        //增加边界
        for (int i = 0; i < LIN; i++) {
            arr[0][i] = 1;
            arr[9][i] = 1;
        }
        for (int i = 0; i < ROW; i++) {
            arr[i][0] = 1;
            arr[i][8] = 1;
        }
        //增加障碍物
        obstacle(arr);
    }

    public static void obstacle(int[][] arr) {
        arr[4][1] = 1;
        arr[4][2] = 1;
        for (int i = 2; i < 6; i++) {
            arr[6][i] = 1;
        }
        for (int i = 2; i < 6; i++) {
            arr[i][5] = 1;
        }
    }

    public static boolean route1(int[][] arr, int row, int lin) {
        if (arr[ROW - 2][LIN - 2] == 2) {//如果最后终点被访问就返回true
            return true;
        } else {
            if (arr[row][lin] == 0) {//如果该点还没有被访问
                arr[row][lin] = 2;//将该点标记为已访问，然后根据设定的规则走下一步
                if (route1(arr, row + 1, lin)) {
                    return true;
                } else if (route1(arr, row, lin + 1)) {
                    return true;
                } else if (route1(arr, row - 1, lin)) {
                    return true;
                } else if (route1(arr, row, lin - 1)) {
                    return true;
                } else {//能走到这一步就证明，是个死路，无路可走了
                    arr[row][lin] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean route2(int[][] arr, int row, int lin) {
        if (arr[ROW - 2][LIN - 2] == 2) {//如果最后终点被访问就返回true
            return true;
        } else {
            if (arr[row][lin] == 0) {//如果该点还没有被访问
                arr[row][lin] = 2;//将该点标记为已访问，然后根据设定的规则走下一步
                if (route2(arr, row, lin + 1)) {
                    return true;
                } else if (route2(arr, row + 1, lin)) {
                    return true;
                } else if (route2(arr, row, lin - 1)) {
                    return true;
                } else if (route2(arr, row - 1, lin)) {
                    return true;
                } else {//能走到这一步就证明，是个死路，无路可走了
                    arr[row][lin] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static int getStep(int[][] arr) {
        int step = 0;
        for (int i = 1; i < ROW; i++) {
            for (int j = 1; j < LIN; j++) {
                if (arr[i][j] == 2) {
                    step++;
                }
            }
        }
        return step;
    }
}