package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 16:53 2018/12/28.
 * @description 2路快速排序 默写的
 */
public class QuickSortTwoWays_ {

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        sort(arr, 0, len - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        // 小序列优化
        if (r - l <= 15) {// 16个元素
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {

        // 增加随机性，减少快排出现O(n^2)复杂度的情况
        swap(arr, (int) (Math.random() * (r - l + 1) + l), l);
        int temp = arr[l];

        int i = l + 1;
        int j = r;
        while (true) {
            // 定位i，j
            while (i <= r && arr[i] < temp)
                i++;
            while (j >= l + 1 && arr[j] > temp)
                j--;

            // 已经有序
            if (i > j)
                break;

            // 否则i，j已定位，接下来交换元素
            swap(arr, i, j);
            i++;
            j--;
        }
        // 上层while循环退出的条件只有可能是(i>j)，此时i指向大于temp的第一个元素，j指向小于temp的最后一个元素。
        // 遗弃的arr[l]归位。
        swap(arr, l, j);
        // 返回中位元素的数组下标，j指标即是最终的中位。
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {// 0.153s
        int[] arr = SortTestUtils.generateNearlyOrderedArray(1000000, 0);

        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.println(SortTestUtils.isSorted(arr));
    }
}
