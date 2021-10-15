package merge_Sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-13 9:22
 * <p>
 * 归并排序
 * 原理：分而治之，先将数据从中中间分成两部分，直至分成单个数据，之后再将相邻的数据进行比较，整合。再将整合后的相邻二级数据进行
 * 整合，直至恢复到原始长度。
 * <p>
 * 平均时间复杂度：nlogn
 * 最差时间复杂度：nlogn
 * 额外空间：O(n)
 * 稳定：比价数据时是相邻两个元素之间进行比较，所以稳定
 */
public class mergeSort {

    private static int CAPACITY = 8;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //辅助数组的创建
        int[] temp = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行归并排序
//        mergeByRecursion(arr, 0, arr.length - 1, temp); //归并排序——递归实现
        merge(arr, temp); //归并排序——迭代实现
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
        print(arr);
    }

    /**
     * 归并排序——迭代实现
     *
     * @param arr  要排序的数组
     * @param temp 辅助数组
     */
    public static void merge(int[] arr, int[] temp) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;//得到数组的长度
        for (int i = 1; i < len; i++) {//分组大小，1,2,4,8,16...直至最后整个数组是一个分组，此时也就排好序了
            int left = 0;
            int mid = left + i - 1;
            int right = mid + i;
            while (right < len) {//right=len-1 是临界条件，此时所有数据刚好分配在每个分组中，没有剩余数据
                merge_sort(arr, left, mid, right, temp);
                left = right + 1;
                mid = left + i - 1;
                right = mid + i;
            }
            /*
            len-1是数组最大下标，归并是两个已经排好序的分组再重新组合排序。
            mid是前面分组的最大下标
            mid+1是后面分组的最小下标
            当数组的最大下标（len-1）小于等于mid时就不用组合排序
             */
            if (mid < len) {
                merge_sort(arr, left, mid, len - 1, temp);
            }
        }
    }

    /**
     * 归并排序——递归实现
     *
     * @param arr   目标数组
     * @param left  最左下标
     * @param right 最右下标
     * @param temp  辅助数组
     */
    private static void mergeByRecursion(int[] arr, int left, int right, int[] temp) {
        if (left < right) {//当只有一个数据时，就不用再进行划分了
            //求出中间值
            int mid = (right - left) / 2 + left;
            //向左边拆分
            mergeByRecursion(arr, left, mid, temp);
            //向右边拆分
            mergeByRecursion(arr, mid + 1, right, temp);
            //合并操作
            merge_sort(arr, left, mid, right, temp);
        }
    }

    /**
     * @param arr 排序数组
     * @param left 最左坐标
     * @param mid 中间坐标
     * @param right 最右坐标
     * @param temp 辅助数组
     */
    private static void merge_sort(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;//记录左侧数组的最左边
        int r = mid + 1;//记录右侧数组的最左边
        int t = 0;//用于遍历temp数组
        while (l <= mid && r <= right) {//如果都没超过两个数组的范围就继续
            if (arr[l] <= arr[r]) {//将两个数组中的数据做比较，将较小的一个放入临时数组temp中
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }
        //检查两个数组中所有数据都放完了，如果没有就继续复制到temp数组中
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }
        //将temp中的数据放到arr中
        t = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = temp[t++];
        }
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
