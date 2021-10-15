package quick_Sort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-12 19:26
 *
 * 快速排序
 *
 * 平均时间复杂度：nlogn
 * 最差时间复杂度：n^s(1<s<2)
 * 额外空间：O(1)
 * 不稳定：由于对数据是在组内操作的，不同组的相同数据在进行排序时有可能会被调换顺序，最终增量为1时，所有数据都在一个组中，这时候就
 * 可能出现相同数据被调换的问题，所以不稳定
 */
public class quickSort {
    private static int CAPACITY = 8000000;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行选择排序
        quick(arr, 0, arr.length - 1);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
//        print(arr);
    }

    private static void quick(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int base = arr[left];
        int l = left;
        int r = right;
        while (l < r) {
            /*
            这两个的顺序要根据base进行调换，如果选取左侧为基准数据，就先从右边开始；反之从左边开始
            因为右边是找比基准数据小的
            原因分析：
            情况一：base最小，右指针一直左移，左指针不动，最终两个指针重合。base和指针重合处交换，递归找排序右边
            情况二：base最大，右指针不动，左指针一直右移，最终两个指针重合。base和指针重合处交换，递归找排序最左边
            情况三：base不大不小，右指针先找到较小值，左指针右移，左指针找到较大值就进行交换，交换之后，左指针还是指向最小值
            如果此时右指针找不到较大值，指针重合出就是较小值，将该较小值与base进行交换。
             */
            while (l < r && arr[r] >= base) {
                r--;
            }
            while (l < r && arr[l] <= base) {
                l++;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        //出循环之后，l==r，此时将基数和该数据调换位置。
        arr[left] = arr[r];
        arr[r] = base;
        //排左边
        quick(arr, left, l - 1);
        //排完左边，排右边
        quick(arr, l + 1, right);
    }

    private static void init(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * CAPACITY);
        }
    }

    private static long getTime() {
        Date date = new Date();
        return date.getTime();
    }

    private static void print(int[] arr) {
        int i = 0;
        for (i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " < ");
        }
        System.out.println(arr[i]);
    }
}
