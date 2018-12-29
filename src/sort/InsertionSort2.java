package sort;

/**
 * @author Ji YongGuang.
 * @date 16:29 2018/12/17.
 * @description 插入排序优化版
 */
public class InsertionSort2 {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {// 逐步递增向 前面的有序数列添加元素

            int temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > temp; j--) // j代表着新插入元素要放置的位置
                arr[j] = arr[j - 1];
            arr[j] = temp;
        }
        return;
    }
}
