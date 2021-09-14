package binary_Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-14 9:42
 * <p>
 * 二分查找------优化版本
 * 利用集合可以返回多个相同的值，并且集合最后一个元素代表第一个元素的下标
 */
public class binarySearch_opt {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 7, 7, 7, 7, 8, 8, 9, 10};
        List<Integer> binary = binary(arr, 0, arr.length - 1, 4);
        System.out.println(binary);
    }

    /**
     * @param arr   数组
     * @param left  左索引
     * @param right 右索引
     * @param value 要查找的值
     * @return
     */
    private static List<Integer> binary(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return new ArrayList<Integer>();
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] < value) {
            return binary(arr, mid + 1, right, value);
        } else if (value < arr[mid]) {
            return binary(arr, left, mid - 1, value);
        } else {
            //创建一个集合
            List<Integer> list = new ArrayList<>();
            //从mid开始向左遍历，看有没有相同的值
            int temp = mid - 1;
            while (temp >= left && arr[temp] == value) {
                list.add(arr[temp--]);
            }
            int index = temp + 1;//用于记录第一个目标值的下标
            list.add(arr[mid]);
            //从mid开始向右遍历，看有没有相同的值
            temp = mid + 1;
            while (temp <= right && arr[temp] == value) {
                list.add(arr[temp++]);
            }
            //在集合最后一位补上下标
            list.add(index);
            return list;
        }
    }
}
