package sort;

import util.SortTestUtils;

/**
 * @author Ji YongGuang.
 * @date 21:26 2018/12/28.
 * @description 三路快速排序 默写版
 */
public class QuickSortThreeWays_ {

    private static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        sort(arr, 0, len - 1);
    }

    private static void sort(int[] arr, int l, int r) {

        if (r - l <= 15) {// 不足16个元素用插入效率更高
            InsertionSort.sort(arr, l, r);
            return;
        }

        // 随机化快速排序的priot
        swap(arr, l, (int) (Math.random() * (r - l + 1) + l));
        int temp = arr[l];

        int lt = l;// 指向 arr[i]<temp 的最后一个元素的索引
        int gt = r + 1;// 指向 arr[i]>temp 的第一个元素的索引
        int i = l + 1;// 元素比较的推移指标
        while (i < gt) {
            if (arr[i] < temp)
                swap(arr, i++, ++lt);
            else if (arr[i] > temp)
                swap(arr, i, --gt);
            else i++;
        }

        // temp，arr[l]归位，因为lt定位的是小于temp的最后一个元素。
        swap(arr, l, lt);

        sort(arr, l, lt - 1);
        sort(arr, gt, r);
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
