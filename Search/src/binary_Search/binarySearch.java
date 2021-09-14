package binary_Search;

/**
 * @Author mkbk
 * @Description
 * @Create 2021-09-14 9:07
 * <p>
 * 二分查找
 * 采用递归
 */
public class binarySearch {
    private static final int CAPACITY = 100;
    private static int time = 0;

    public static void main(String[] args) {
//        int[] arr = new int[CAPACITY];
//        init(arr);
//        int index = binary(arr, 0, arr.length - 1, 46);

        //这里和插值查找作比较----------------------------------------
        int[] arr1 = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 65,12331};
        int index1 = binary(arr1, 0, arr1.length - 1, 12);
        System.out.println("查找次数：" + time);//改数据查询次数4，优于插值查找
    }

    private static int binary(int[] arr, int left, int right, int value) {
        time++;
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {//如果left>right说明所有数据就已经查完
            return -1;
        }
        int mid = left + (right - left) / 2;//中间值
        if (arr[mid] < value) {
            return binary(arr, mid + 1, right, value);
        } else if (arr[mid] > value) {
            return binary(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    private static void init(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }
}
