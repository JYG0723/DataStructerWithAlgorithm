package tree;

/**
 * @author Ji YongGuang.
 * @date 16:18 2018/12/29.
 * @description 二分查找法
 */
public class BinarySearchByRecursive {

    /**
     * 检索到即返回在数组中的索引下标，否则返回-1
     */
    public static int find(int[] arr, int target) {
        return findByRecursive(arr, 0, arr.length - 1, target);
    }

    /**
     * 递归的在数组arr的arr[l....r]区间中找到数值等于target的元素，并返回其数组下标
     */
    private static int findByRecursive(int[] arr, int l, int r, int target) {

        if (l > r)
            return -1;

        int mid = (l + r) >>> 1;

        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < target)// 则说明target在arr[mid+1...r]
            return findByRecursive(arr, mid + 1, r, target);
        else // 则说明 arr[mid] > target target在arr[l...mid-1]
            return findByRecursive(arr, l, mid - 1, target);
    }

    public static void main(String[] args) {
        /*int a = 1000000000;
        int b = 2000000000;
        System.out.println((a + b) / 2);
        System.out.println((a + b) >>> 1);*/
        int N = 1000000;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = i;

        // 对于我们的待查找数组[0...N)
        // 对[0...N)区间的数值使用二分查找，最终结果应该就是数字本身
        // 对[N...2*N)区间的数值使用二分查找，因为这些数字不在arr中，结果为-1
        for (int i = 0; i < 2 * N; i++) {
            int v = BinarySearchByRecursive.find(arr, i);
            if (i < N)
                assert v == i;
            else
                assert v == -1;
        }

        return;
    }
}
