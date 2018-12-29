package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 17:41 2018/12/28.
 * @description 快速排序
 */
public class QuickSort {

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * @param arr   待排序数组
     * @param start 待排序数组arr的元素比较的起始位置，数组下标
     * @param end   待排序数组arr的元素比较的终点位置，数组下标
     */
    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int i = start, j = end;
        int mid = arr[i];// 待排序数组的首位置
        while (i < j) {
            while (i < j && arr[j] >= mid) {// 找后半数组小于mid的元素的数组下标
                j--;
            }
            if (i < j)
                arr[i++] = arr[j];

            while (i < j && arr[i] <= mid) {// 找前半数组大于mid的元素的数组下表
                i++;
            }
            if (i < j)
                arr[j--] = arr[i];
        }
        // 安置前面比较过程使用过的中间元素
        arr[i] = mid;
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

    public static void main(String[] args) {// 0.153s
        int[] arr = SortTestUtils.generateRandomArray(100000, 0, 10);

        long startTime = System.currentTimeMillis();
        quickSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.println(SortTestUtils.isSorted(arr));
    }
}
