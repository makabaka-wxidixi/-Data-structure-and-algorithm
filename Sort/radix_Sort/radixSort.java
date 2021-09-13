package radix_Sort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-13 12:00
 *
 * 基数排序
 *
 * 原理：先遍历一遍数组，获取最大元素的长度——用于确定外层循环。创建10个桶子（二维数组），遍历数组，根据每个元素的个位数，对应得找到桶子
 * 然后放到桶子中去。再通过遍历10个桶子，将得到的数据依次放到原始数组中去。然后依次再得到十位数，百位数......（如果最大数据有的话），
 * 在找到对应的桶子，放入数据。再遍历桶子，取出数据放到原始数组中去......以此类推
 * 平均时间复杂度：O(nxk)
 * 最坏时间复杂度：O(nxk)
 * 空间复杂度O(n+k)
 * 稳定：得到相同数据后，先从原始数组中得到的数据会先放到桶子中，当从桶子中取数据时，先放进去的数据就先出来，所以还能保证相同数据的顺序
 * 是不变的
 *
 * 值得注意的是：该方法虽然速度很快，但是会占用大量的额外空间（原始数组的11倍），当数据十分大时，可能会内存不够！！！
 */
public class radixSort {
    private static int CAPACITY = 80000;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行选择排序
        radix(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
//        print(arr);
    }

    private static void radix(int[] arr) {
        //定义二维数组（桶子），用于存放数据
        int[][] bucket = new int[10][arr.length];
        //定义一维数组，用于记录每个桶中存放了几个数据
        int[] bucketElementCounts = new int[10];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();//获取最大数据的长度
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {//根据最大值的长度来确定循环次数
            for (int j = 0; j < arr.length; j++) {//遍历原始数组，来讲数据放到桶子中去
                int element = arr[j] / n % 10;//确定要放入的桶子
                bucket[element][bucketElementCounts[element]] = arr[j];
                bucketElementCounts[element]++;
            }
            int index = 0;//跟踪arr数组
            //遍历每个桶子中的数据，将数据放到arr中
            for (int j = 0; j < 10; j++) {
                if (bucketElementCounts[j] != 0) {//如果桶子中有数据，就从桶子中取出数据，放到原始数组中去
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                    bucketElementCounts[j] = 0;//就清空桶子，用于盛装下一轮的数据
                }
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
