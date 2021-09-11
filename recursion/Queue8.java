/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-11 8:35
 * <p>
 * 八皇后问题（回溯）
 * 在一个8 x 8的棋盘上放置8个皇后，两两不构成威胁（不在同一行，同一列，同一对角线），问有多少种摆法？
 */
public class Queue8 {
    int max = 8;
    int[] arr = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("总共有" + count + "种摆法");
    }

    //放置第n个皇后
    void check(int n) {
        if (n == max) {//n==8，这时已将8个格子全部放了一遍
            //打印结果
            print();
            return;
        }
        for (int i = 0; i < max; i++) {//从第一个位置开始放置，放置8次
            arr[n] = i;//暂时先将皇后放置到对应位置上
            if (judge(n)) {//判断该皇后摆放的位置是否合理
                check(n + 1);//放置下一个皇后
            }
        }
    }

    //判断n位置上的皇后和之前摆放的皇后是否冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        //走到这一步就说明都不冲突，可以放置
        return true;
    }

    //打印皇后摆法
    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + 1 + " ");
        }
        System.out.println();
    }
}
