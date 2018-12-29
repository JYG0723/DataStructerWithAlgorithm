package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 15:44 2018/12/27.
 * @description 归并排序 优化版
 */
public class MegerSort2 {

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        mergeSort(arr, 0, len - 1);
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        // 数据规模小到一定程度使用插入排序
        if (end - start <= 15) {
            InsertionSort.sort(arr, start, end);
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        // 优化归并的步骤
        if (arr[mid] > arr[mid + 1])// 如果arr[mid] <= arr[mid + 1] 则证明两数组已经有序，不需要合并。
            merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1;
        int k = 0;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= r) {
            temp[k++] = arr[j++];
        }

        for (int index = 0; index < temp.length; index++) {
            arr[index + l] = temp[index];
        }
    }

    public static void main(String[] args) {
        // 测试数组的生成
        int[] arr = SortTestUtils.generateRandomArray(1000000, 0, 10);
        int[] arr2 = SortTestUtils.intArrayCopy(arr);

        // 插入排序用时
        long startTime = System.currentTimeMillis();
        QuickSortTwoWays_.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");// 0.043s
        // 归并排序用时
        long startTime1 = System.currentTimeMillis();
        MegerSort2.sort(arr2);
        long endTime1 = System.currentTimeMillis();
        System.out.println((endTime1 - startTime1) / 1000.0 + "s");// 0.132s
        System.out.println(SortTestUtils.isSorted(arr2));
    }
}
