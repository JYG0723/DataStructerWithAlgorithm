package util;

import java.util.Arrays;

/**
 * @author Ji YongGuang.
 * @date 20:20 2018/12/26.
 * @description 排序工具类
 */
public class SortTestUtils {

    /**
     * 生成大小为length且数值范围在[rangL,rangR]的数组
     */
    public static int[] generateRandomArray(int length, int rangL, int rangR) {
        assert rangL < rangR;

        int[] arr = new int[length];
        for (int i = 0; i < length; i++)
            arr[i] = (int) (Math.random() * (rangR - rangL + 1) + rangL);
        return arr;
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...length-1]的完全有序数组, 之后随机交换swapTimes对数据
     * <p>
     * swapTimes定义了数组的无序程度:
     * swapTimes == 0 时, 数组完全有序
     * swapTimes 越大, 数组越趋向于无序
     */
    public static int[] generateNearlyOrderedArray(int length, int swapTimes) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++)
            arr[i] = i;

        for (int i = 0; i < swapTimes; i++) {// 任意交换两数
            int a = (int) (Math.random() * length);
            int b = (int) (Math.random() * length);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }

    /**
     * 数组两个索引处的元素数值交换
     */
    public static void swap(int[] arr, int lIndex, int rIndex) {
        int temp = arr[lIndex];
        arr[lIndex] = arr[rIndex];
        arr[rIndex] = temp;
    }

    /**
     * 判断数组是否有序
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > arr[i + 1])
                return false;
        return true;
    }

    /**
     * 赋值一个与arr元素一致的数组
     */
    public static int[] intArrayCopy(int[] arr) {
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        return arr2;
    }
}
