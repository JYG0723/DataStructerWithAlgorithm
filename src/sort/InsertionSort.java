package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 22:49 2018/12/26.
 * @description 插入排序
 */
public class InsertionSort {

    public static void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++)

            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--)
                SortTestUtils.swap(arr, j, j - 1);
                /*if (arr[j] < arr[j - 1])
                    SortTestUtils.swap(arr, j, j - 1);
                else
                    break;*/
    }

    public static void sort(int[] arr, int l, int r) {

        for (int i = l + 1; i <= r; i++) {
            int temp = arr[i];
            int j = i;
            // 注意这里控制j不能取到小于l的位置。因为我们是要对arr[l...r]之间进行排序。
            for (; j > l && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {// 0.051s
        int[] arr = SortTestUtils.generateRandomArray(10000, 0, 10000);
        int[] arr2 = SortTestUtils.intArrayCopy(arr);

        long startTime = System.currentTimeMillis();
        InsertionSort.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");// 0.043s
        System.out.println(SortTestUtils.isSorted(arr));

        long startTime1 = System.currentTimeMillis();
        InsertionSort2.sort(arr2);
        long endTime1 = System.currentTimeMillis();
        System.out.println((endTime1 - startTime1) / 1000.0 + "s");// 0.132s
        System.out.println(SortTestUtils.isSorted(arr2));
    }
}
