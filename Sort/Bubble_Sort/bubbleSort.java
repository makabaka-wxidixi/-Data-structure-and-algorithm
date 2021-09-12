package Bubble_Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-11 11:46
 * <p>
 * 冒泡排序
 * 平均时间复杂度：n^2
 * 最差时间复杂度：n^2
 * 额外空间：O(1)
 * 当n小时可以用
 *
 * 稳定：冒泡排序就是把小的元素往前调或者把大的元素往后调。比较是相邻的两个元素比较，交换也发生在这两个元素之间。所以，如果
 * 两个元素相等，是不会再交换的；如果两个相等的元素没有相邻，那么即使通过前面的两两交换把两个相邻起来，这时候也不会交换，
 * 所以相同元素的前后顺序并没有改变，所以冒泡排序是一种稳定排序算法。
 *
 * 冒泡排序的比较次数为n*(n-1)/2次
 * 冒泡排序的交换次数为n*(n-1)/2次
 * 对冒泡排序进行了优化，排序期间如果发现已经有序，就会提前终止
 */
public class bubbleSort {
    //用于记录外部循环执行的次数
    private static int count = 0;

    public static void main(String[] args) {
        //创建数组
        int[] arr = new int[10000];
        //初始化数组
        init(arr);
        //得到排序前的微秒数
        long time1 = getTime();
        //进行冒泡排序
        bubble(arr);
        //获取排序之后的微秒数
        long time2 = getTime();
        //得到排序花费的微秒数
        System.out.println("冒泡排序所花的微秒数：" + (time2 - time1));
        //打印排序之后的数组和执行的外部循环次数
        print(arr);
    }

    private static void init(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
    }

    private static long getTime() {
        Date date = new Date();
        System.out.println(date.getTime());
        return date.getTime();
    }

    private static void bubble(int[] arr) {
        boolean flag = false;//标记
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            count++;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //优化
            if (flag) {//如果该次循环有进行排序操作
                flag = false;
            } else {//该次循环都没有进行排序操作，就证明已经是按顺序排好了，可以直接返回
                return;
            }
        }
    }

    private static void print(int[] arr) {
        int i = 0;
        for (i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " < ");
        }
        System.out.println(arr[i]);
        System.out.println("进行了 " + count + " 次排序");
    }
}
