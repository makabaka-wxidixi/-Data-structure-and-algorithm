package insert_Sort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-12 8:35
 * <p>
 * 插入排序：
 * 原理：将数据看成由一个有序表和一个无序表组成（有序表在前，无序表在后），每次从无序表中取出一个数据，将其与有序
 * 表最大值作比较，如果大于最大值就加在有序表最后面，如果小于就将有序表最大值后移一位，并和次大值作比较，依次循环
 * 最差时间复杂度：n^2
 * 平均时间复杂度：n^2
 * 额外空间：O(1)
 * 稳定：关键词相同的数据元素将保持原有位置不变，所以该算法是稳定的
 */
public class insertSort {

    private static final int CAPACITY = 80000;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行选择排序
        insert(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
//        print(arr);
    }

    private static void insert(int[] arr) {
        int insertVal;//要插入的数据
        int insertIndex;//要插入的数据的下标
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i;
            insertVal = arr[i];
            //当要插入的数据比有序表的数据小
            while (insertIndex - 1 >= 0 && insertVal < arr[insertIndex - 1]) {
                //将有序表中做比较的数据往后移一位
                arr[insertIndex] = arr[insertIndex - 1];
                //要查入的位置前移一位
                insertIndex--;
            }
            //插入数据
            arr[insertIndex] = insertVal;
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
 