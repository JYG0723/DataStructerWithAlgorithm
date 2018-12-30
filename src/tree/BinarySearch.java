package tree;

/**
 * @author Ji YongGuang.
 * @date 16:18 2018/12/29.
 * @description 二分查找法
 */
public class BinarySearch {

    /**
     * 检索到即返回在数组中的索引下标，否则返回-1
     */
    public static int find(int[] arr, int target) {

        int len = arr.length;
        if (len <= 1)
            return len == 0 ? -1 : arr[0] == target ? 0 : -1;

        int l = 0;
        int r = len - 1;
        while (l <= r) {

            int mid = (l + r) >>> 1;// l + (r - l) / 2。不需要+1哦
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] < target)// 则说明target在arr[mid+1...r]
                l = mid + 1;
            else // 则说明 arr[mid] > target target在arr[l...mid-1]
                r = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        int a = 1000000000;
        int b = 2000000000;
        System.out.println((a + b) / 2);
        System.out.println((a + (b - a) / 2));
        System.out.println((a + b) >>> 1);
        /*int N = 1000000;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = i;

        long startTime = System.currentTimeMillis();
        BinarySearch.find(arr, 67);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000.0 + "s");

        long startTime1 = System.currentTimeMillis();
        BinarySearchByRecursive.find(arr, 500000);
        long endTime1 = System.currentTimeMillis();
        System.out.println((endTime1 - startTime1) / 1000.0 + "s");*/
    }
}
