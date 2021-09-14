package interpolation_Search;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-14 10:33
 * <p>
 * 插值查找
 * 插值查找是根据查找关键字与查找表中最大最小记录关键字比较后的查找方法。插值查找基于二分查找，将查找点的选择改进为自适应选择，
 * 提高查找效率。
 * 公式：
 * mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
 * <p>
 * 优点：对于那些较为连续的数据，插值查性能优于二分查找
 * 缺点：对于那些比较离散的数据，性能可能不如二分查找
 */
public class interpolationSearch {
    private static final int CAPACITY = 100;
    private static int time = 0;

    public static void main(String[] args) {
        int[] arr = new int[CAPACITY];
        //初始化数组
        init(arr);
        interpolation(arr, 0, arr.length - 1, 46);

        //这里和二分查找作比较----------------------------------------------
        int[] arr1 = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 65, 12331};
        interpolation(arr1, 0, arr1.length - 1, 12);
        System.out.println("执行次数：" + time);//改数据查询次数9：劣于二分查找
    }

    private static int interpolation(int[] arr, int left, int right, int value) {
        time++;
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {//结束条件
            return -1;
        }
        //从自适应mid处查找-*
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        //此处参照二分查找
        if (arr[mid] < value) {
            return interpolation(arr, mid + 1, right, value);
        } else if (arr[mid] > value) {
            return interpolation(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }


    private static void init(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }
}
