package select_Sort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-11 14:27
 * <p>
 * 选择排序
 * 平均时间复杂度：n^2
 * 最差时间复杂度：n^2
 * 不稳定
 * 额外空间：O(1)
 * 当n小时可以用
 * <p>
 * 尽管时间复杂一致，选择排序一般要比冒泡排序快
 * 因为冒泡排序每次都要进行数据的交换，而选择排序一般只会进行少量的交换数据。
 */
public class selectSort {

    private static final int CAPACITY = 10000;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行选择排序
        slsect(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
        print(arr);

    }

    private static void slsect(int[] arr) {
        //定义两个变量，用来存最小值和对应的下标
        int min = 0;
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //如果发现还有更小的值，就将最小值进行替换
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //数据交换
            arr[minIndex] = arr[i];
            arr[i] = min;
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
