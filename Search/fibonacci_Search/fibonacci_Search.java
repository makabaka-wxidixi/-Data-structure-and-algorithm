package fibonacci_Search;

import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-15 10:14
 */
public class fibonacci_Search {
    private static int CAPACITY = 20;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 23, 54, 67, 122, 234, 345};
        //得到斐波那契数列
        int[] f = fib();
        int index = fibonacci(arr, 122, f);
        System.out.println("下标为：" + index);
    }

    private static int fibonacci(int[] arr, int val, int[] f) {
        int low = 0;
        int high = arr.length - 1;
        int mid;
        int k = 0;
        while (f[k] - 1 < high) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k]);
        //you肯能f[k]-1会大于arr，补齐之后后面的数据默认都是0，将其全部分配为最大数
        for (int i = arr.length; i < f[k]; i++) {
            temp[i] = arr[arr.length - 1];
        }
        //以下过程类似于二分查找
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (temp[mid] < val) {
                k -= 2;
                low = mid + 1;
            } else if (val < temp[mid]) {
                k--;
                high = mid - 1;
            } else {
                //由于temp是扩充之后的数组，当遍历到最后时，有可能mid会大于high，选最小值输出
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }


    private static int[] fib() {
        int[] arr = new int[CAPACITY];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < CAPACITY; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }
}
