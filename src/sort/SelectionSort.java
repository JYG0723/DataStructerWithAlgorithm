package sort;

import util.SortTestUtils;

/**
 * 选择排序 小到大
 */
public class SelectionSort {

    /**
     * 选择排序，每次选最大的元素并且置后
     */
    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        for (int i = 0; i < len - 1; i++) {

            // 寻找未排序序列中最大元素
            int soldier = 0;
            for (int j = soldier + 1; j < len - i; j++)
                if (arr[j] > arr[soldier])
                    soldier = j;

            SortTestUtils.swap(arr, soldier, len - i - 1);
        }
    }

    public static void main(String[] args) {// 0.153s
        int[] arr = SortTestUtils.generateRandomArray(10000, 0, 10000);

        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.println(SortTestUtils.isSorted(arr));
    }
}
