package sort;

/**
 * @author Ji YongGuang.
 * @date 20:33 2019/1/3.
 * @description 快速排序
 */
public class QuickSort1 {

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        sort(arr, 0, len - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r)
            return;

        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {

        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        int temp = arr[l];

        int j = l;// 初始时在待定位
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < temp) {// arr[i] > temp ，i往后推移
                j++;
                swap(arr, i, j);
            }
        }
        // temp 归位。程序跑到这里最后j指向的是小于temp的最后一个元素的数组下标
        swap(arr, j, l);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
