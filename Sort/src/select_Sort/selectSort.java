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
 * 额外空间：O(1)
 *
 * 不稳定：选择排序是给每个位置选择当前元素最小的，比如给第一个位置选择最小的，在剩余元素里面给第二个元素选择第二小的，依次
 * 类推，直到第n-1个元素，第n个元素不用选择了，因为只剩下它一个最大的元素了。那么，在一趟选择，如果一个元素比当前元素小，
 * 而该小的元素又出现在一个和当前元素相等的元素后面，那么交换后稳定性就被破坏了。举个例子，序列5 8 5 2 9，我们知道第一遍选
 * 择第1个元素5会和2交换，那么原序列中两个5的相对前后顺序就被破坏了，所以选择排序是一个不稳定的排序算法。
 *
 * 当n小时可以用
 * <p>
 * 选择排序的交换次数位于0~(n-1)次之间，最好已经有序，是0；最坏n-1次，逆序交换n/2次
 * 选择排序的比较次数为n*(n-1)/2次
 * 由于交换所占cpu时间比比较所占cpu时间多，当n较小时，比冒泡要快
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
//        print(arr);

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
