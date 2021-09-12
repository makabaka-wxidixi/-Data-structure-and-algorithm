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
    private static int CAPACITY = 80000;

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
        if (left > right) {
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
            情况一：基准数据右边全是大于base的，r先左移一个位置，然后停止。l继续右移，由于都是小于
            base的，l要找的数据是大于base的，那么就会一直右移，直到遇到r就停止，base一定大于该返回值
            情况二：基准数据右边有大有小，r先左移动。定位一个小于base的数。然后l右移，定位一个大于base的数据
            然后进行交换，然后r再左移，之后如果l右移时，没有大于base的数了，当l等于r时也会停止，此时base一定大于该返回值
            情况三：基准数据右边全是小于base的，r先左移，并且会一直移动，l不动。当遇到l时，停止，然后自己和自己交换
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
