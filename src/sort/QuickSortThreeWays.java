package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 20:51 2018/12/28.
 * @description 三路快速排序
 */
public class QuickSortThreeWays {

    public static void sort(int[] arr) {

        int len = arr.length;
        if (len <= 1)
            return;
        sort(arr, 0, len - 1);
    }

    private static void sort(int[] arr, int l, int r) {

        // 对于小规模数组, 使用插入排序
        if (r - l <= 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        int temp = arr[l];

        // 三组数据的指针区间
        int lt = l;     // arr[l+1...lt] < v ，lt指向的是小于temp的区间的最后一个元素的索引。
        int gt = r + 1; // arr[gt...r] > v ， gt指向的是大于temp的区间的第一个元素的索引。
        int i = l + 1;    // arr[lt+1...i) == v
        while (i < gt) {
            if (arr[i] < temp)// arr[i]交换过来的arr[lt+1]是数组arr ==v的第一个元素。已经比较过，所以i需要++
                swap(arr, i++, ++lt);
            else if (arr[i] > temp)
                swap(arr, i, --gt);
            else // arr[i] == v
                i++;
        }

        swap(arr, l, lt);

        sort(arr, l, lt - 1);
        sort(arr, gt, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {// 0.153s
        int[] arr = SortTestUtils.generateRandomArray(1000000, 0, 10);

        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.println(SortTestUtils.isSorted(arr));
    }
}
