package sort;

import util.SortTestUtils;

import java.util.Arrays;

/**
 * @author Ji YongGuang.
 * @date 16:53 2018/12/28.
 * @description 优化后的2路快速排序
 * 随机化优化 插入排序优化 及逻辑优化
 */
public class QuickSortTwoWays {

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        sort(arr, 0, len - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(int[] arr, int l, int r) {

        // 对于小规模数组, 使用插入排序
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        // 防止高度有序的序列影响快排性能。这样优化后的快排基本不会退化到O(n^2)的情况。
        swap(arr, (int) (Math.random() * (r - l + 1)) + l, l);
        int temp = arr[l];

        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < temp)// i，j两者只有在进入到对方的领域才会停止增长
                i++;
            while (j >= l + 1 && arr[j] > temp)
                j--;
            if (i > j)// i的最终定位是第一个大于temp的元素索引处。j的最终定位是最后一个小于temp的元素索引处。
                break;
            swap(arr, i, j);// 检索到待交换的两元素
            // 元素交替后，两变量要递增
            i++;
            j--;
        }
        // 因为上层while循环退出后代表着 i>j。
        // 此时arr[j]元素即是最后一个小于temp的元素。
        // 可以借该j的索引将arr[l]放置到它正确的位置。
        swap(arr, l, j);

        return j;
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args) {// 0.153s
        int[] arr = SortTestUtils.generateRandomArray(100000, 0, 10);

        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.println(SortTestUtils.isSorted(arr));
    }
}
