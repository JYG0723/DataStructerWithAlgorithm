package sort;

/**
 * @author Ji YongGuang.
 * @date 13:52 2018/12/29.
 * @description 堆
 */
public class MaxHeap {

    /*堆数据区*/
    private int[] data;
    /*堆当前元素数*/
    private int count;
    /*堆容量*/
    private int capacity;

    public MaxHeap(int capacity) {
        this.data = new int[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(int[] arr) {
        int len = arr.length;
        this.data = new int[len + 1];
        this.capacity = len;

        for (int i = 0; i < len; i++) {// data[0] 不存元素，所以所以元素后移移位
            data[i + 1] = arr[i];
        }
        count = len;

        // shiftDown()
        for (int j = count / 2; j >= 1; j--) {
            shiftDown(j);
        }
    }

    private void shiftDown(int k) {
        int temp = data[k];
        for (int j = k * 2; j <= count; k = j, j *= 2) {
            if (j < count && data[j] < data[j + 1]) {
                ++j;
            }
            if (temp > data[j])
                break;
            else data[k] = data[j];
        }
        data[k] = temp;
    }

    public void insert(int value) {
        assert count < capacity;
        data[++count] = value;
        // 调整该入堆的元素位置
        shiftUp(count);
    }

    // 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
    public int extractMax() {
        assert count > 0;
        int max = data[1];

        swap(1, count--);
        // 有序化
        shiftDown(1);

        return max;
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k] > data[k / 2]) {
            swap(k, k / 2);
            k = k / 2;// 继续向上定位
        }
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    // 获取最大堆中的堆顶元素
    public int getMax() {
        assert (count > 0);
        return data[1];
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for (int i = 0; i < N; i++)
            maxHeap.insert((int) (Math.random() * M));

        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for (int i = 1; i < N; i++)
            assert arr[i - 1] >= arr[i];
    }
}
