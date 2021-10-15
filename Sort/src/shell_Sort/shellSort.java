package shell_Sort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-12 16:54
 *
 * 希尔排序
 * 本质上是插入排序，只不过进行了优化，使得效率更高，又称为缩小增量排序
 * 算法思想：把数据根据下标按照一定的增量进行分组，对每组进行插入排序算法，随着增量的减小，每组包含的数据越来越多，当增量减少至1时，所有数据都被
 * 包含在内，此时的所有数据就基本趋向于有序，然后再进行一次插入排序，最终就达到有序了
 *
 * 平均时间复杂度：nlogn
 * 最差时间复杂度：n^s(1<s<2)
 * 额外空间：O(1)
 * 不稳定：由于对数据是在组内操作的，不同组的相同数据在进行排序时有可能会被调换顺序，最终增量为1时，所有数据都在一个组中，这时候就
 * 可能出现相同数据被调换的问题，所以不稳定
 *
 */
public class shellSort {

    private static int CAPACITY = 80000;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行选择排序
        shell(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
//        print(arr);
    }

    private static void shell(int[] arr) {
        int insertVal;
        int insertIndex;
        for (int groph = arr.length / 2; groph > 0; groph /= 2) {
            //将每次的分组进行排序
            for (int i = groph; i < arr.length; i++) {
                insertVal = arr[i];//要插入的值
                insertIndex = i;//要插入的值的下标
                //这里类比插入排序
                while (insertIndex - groph >= 0 && insertVal < arr[insertIndex - groph]) {
                    arr[insertIndex] = arr[insertIndex - groph];
                    insertIndex -= groph;
                }
                arr[insertIndex] = insertVal;
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
