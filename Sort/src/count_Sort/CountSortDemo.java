package count_Sort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-15 20:05
 * <p>
 * 计数排序
 * 时间复杂度：O(n+k)，其中k是临时数组(temp)的大小
 * 空间复杂度：O(k)，
 * 稳定性：稳定
 * 适用于最大值和最小值相差不多的排序。通过记录
 */
public class CountSortDemo {
    private static final int CAPACITY = 8;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行计数排序
        CountSort(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
        print(arr);
    }

    public static void CountSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int min = arr[0];
        int max = arr[0];
        //确定最大值和最小值，用于确定辅助数组的容量
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int tempCapacity = max - min + 1;
        int[] temp = new int[tempCapacity];//用于记录数据出现了几次
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i] - min]++;
        }
        int index = 0;
        for (int i = 0; i < tempCapacity; i++) {//遍历整个范围
            for (int j = temp[i]; j > 0; j--) {//如果有范围内有数据，那么就重新加入到arr中
                arr[index++] = i + min;//最小值加上temp数组的下标，就能恢复成原来的数，i最大就是tempCapacity-1，也就是max-min
            }
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





