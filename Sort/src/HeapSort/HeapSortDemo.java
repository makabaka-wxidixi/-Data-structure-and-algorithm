package HeapSort;

import java.util.Date;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-18 13:29
 *
 * 堆排序（是一种选择排序）
 *
 * 利用二叉树的存储原理来存储数组，先将数组按照大顶堆或者小顶堆来进行排序。之后进行循环，每次循环都将最大值或者最小值弹
 * 至根节点，然后和数组尾元素交换。由于数组最有一位已经是一个最值，所以下一轮循环就减少一个元素的参与。将剩余元素中的最值
 * 弹到根节点，然后再和剩余元素最后一位进行交换位置。以此类推
 *
 * 不稳定：比如：3 27 36 27，
 * 如果堆顶3先输出，则，第三层的27（最后一个27）跑到堆顶，然后堆稳定，继续输出堆顶，是刚才那个27，这样说明后面的27先于第
 * 二个位置的27输出
 * 最好、最差、平均时间复杂度都是n(logn)
 */
public class HeapSortDemo {
    private static int CAPACITY = 8;

    public static void main(String[] args) {
        //数组的创建
        int[] arr = new int[CAPACITY];
        //数组初始化
        init(arr);
        //获取排序前的微秒数
        long time1 = getTime();
        //进行选择排序
        HeapDesc(arr);
        //获取排序后的微秒数
        long time2 = getTime();
        //输出排序所花费的时间
        System.out.println("选择排序所花费的总时间为：" + (time2 - time1));
        print(arr);
    }

    static void HeapAsc(int[] arr) {
        int temp = 0;
        //通过循环先将数组所有数据进行大顶堆排序
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustAsc(arr, i, arr.length);
        }
        //此时的数组进行过堆排序
        for (int i = arr.length - 1; i > 0; i--) {
            //将数组最大的数（下标为0）的数据放到最后的位置
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustAsc(arr, 0, i);
        }
    }

    /**
     * @param arr    要排序的数组
     * @param i      通过堆排序，将最大值弹到下标为i的结点
     * @param length 需要排序的最大下标
     */
    static void adjustAsc(int[] arr, int i, int length) {
        //记录原始根节点，用于后续交换节点
        int temp = arr[i];
        //判断两个子节点和根节点的大小关系
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //找两个子节点的最大值
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            //如果子节点大于根节点，就进行交换，这点类似于插入循环，原始根节点的元素直到找到要放置的位置的时候才会插入
            //那么在程序运行过程中，子节点会进行赋值，并输送给当前根节点。所以整个过程中会有两个一样的根节点和子节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                //i定位到下一个子节点，进行循环
                i = k;
            } else {
                //由于是堆排序在排序过程中是从左往右，从下往上的，所以可以保证下面的子树都是能够保证是堆排序的
                break;
            }
        }
        //出循环之后就说明找到了temp要放得位置，将之前保留的根节点放到对应的位置上
        arr[i] = temp;
    }

    static void HeapDesc(int[] arr) {
        int temp = 0;
        //想将数据进行小顶堆排序
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustDesc(arr, i, arr.length);
        }
        //此时的数据就是小顶堆排序
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustDesc(arr, 0, i);
        }
    }

    static void adjustDesc(int[] arr, int i, int length) {
        //保存原始根节点
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //找两个子结点中的最小值
            if (k + 1 < length && arr[k + 1] < arr[k]) {
                k++;
            }
            //如果结点内容小于根节点，就进行替换数据，i移动至下一子节点
            if (arr[k] < temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //将目标位置赋值，完成数据的替换
        arr[i] = temp;
    }


    static void init(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * CAPACITY);
        }
    }

    static long getTime() {
        Date date = new Date();
        return date.getTime();
    }

    static void print(int[] arr) {
        int i = 0;
        for (i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " < ");
        }
        System.out.println(arr[i]);
    }
}
