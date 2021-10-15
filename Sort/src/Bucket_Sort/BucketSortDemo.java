package Bucket_Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-10-15 20:51
 *
 * 桶排序
 * 时间复杂度：O(n+k)  k表示桶子个数
 * 空间复杂度：O(n+k)
 * 稳定排序
 *
 */
public class BucketSortDemo {
    private static int CAPACITY = 80000;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行基数排序
        BucketSort(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
//        print(arr);
    }

    public static void BucketSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < len; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int d = max - min;//最大差值
        int bucketNum = d / 5 + 1;//桶子的数量，每个桶子放的范围是5
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>(bucketNum);//存放所有桶子
        //初始化桶子集合
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Integer>());
        }
        //遍历原始数组，将数据放入到桶子中
        for (int i = 0; i < arr.length; i++) {
            bucketList.get((arr[i] - min) / d).add(arr[i]);
        }
        //利用自带的排序对每个桶子中的数据进行排序
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }
        int index = 0;
        //遍历每个桶子，将桶子中的数据放入到arr中
        for (int i = 0; i < bucketNum; i++) {
            for (Integer I: bucketList.get(i)) {
                arr[index++] = I;
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
