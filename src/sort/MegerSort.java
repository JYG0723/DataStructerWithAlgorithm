package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 15:44 2018/12/27.
 * @description 归并排序 自顶向下
 */
public class MegerSort {

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        mergeSort(arr, 0, len - 1);
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = l, j = mid + 1;
        int k = 0;

        while (i <= mid && j <= r) {
            if (arr[i] < arr[j])
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
        int[] arr = SortTestUtils.generateRandomArray(50000, 0, 10000);
        int[] arr2 = SortTestUtils.intArrayCopy(arr);

        // 插入排序用时
        long startTime = System.currentTimeMillis();
        InsertionSort2.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");// 0.043s
        // 归并排序用时
        long startTime1 = System.currentTimeMillis();
        MegerSort.sort(arr2);
        long endTime1 = System.currentTimeMillis();
        System.out.println((endTime1 - startTime1) / 1000.0 + "s");// 0.132s
    }
}
