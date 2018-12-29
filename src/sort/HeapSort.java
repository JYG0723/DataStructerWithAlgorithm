package sort;

import util.SortTestUtils;

import java.util.Arrays;

/**
 * @author Ji YongGuang.
 * @date 11:16 2018/12/29.
 * @description 堆排序
 */
public class HeapSort {

    private static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1)
            return;

        for (int i = len / 2 - 1; i >= 0; i--)// 从最后一个父节点开始构建堆
            adjustHeap(arr, i, len - 1);

        for (int i = len - 1; i >= 0; i--) {// 交换记录
            swap(arr, 0, i);
            adjustHeap(arr, 0, i - 1);// 调整堆
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 构建大顶堆
     * size 待排序数据的取值范围，数组下标
     */
    private static void adjustHeap(int[] arr, int start, int size) {
        int temp = arr[start];
        for (int j = start * 2 + 1; j <= size; start = j, j = j * 2 + 1) {
            if (j < size && arr[j] < arr[j + 1])// 这里j一定是小于size。否则++j就超出了size
                ++j;
            if (temp >= arr[j])// 已平衡
                break;
            else
                arr[start] = arr[j];// 子替父
        }
        arr[start] = temp;
    }

    public static void main(String[] args) {// 0.153s
        int[] arr = SortTestUtils.generateRandomArray(10000, 0, 10000);

        long startTime = System.currentTimeMillis();
        sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        System.out.println(Arrays.toString(arr));
        System.out.println(SortTestUtils.isSorted(arr));
    }
}
