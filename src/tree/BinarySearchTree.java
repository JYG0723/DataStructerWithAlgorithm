package tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Ji YongGuang.
 * @date 17:51 2018/12/29.
 * @description 二分搜索树
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    /*二分搜索树根节点*/
    private Node root;
    /*二分搜索树的元素数量*/
    private int count;

    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(K key, V value) {
        root = insertByRecursive(root, key, value);
    }

    /**
     * 递归的实现二叉搜索树元素的插入
     */
    private Node insertByRecursive(Node node, K key, V value) {

        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) > 0)
            node.right = insertByRecursive(node.right, key, value);
        else if (key.compareTo(node.key) < 0)
            node.left = insertByRecursive(node.left, key, value);
        else node.value = value;

        return node;
    }

    /**
     * 非递归的实现二叉搜索树元素的插入
     * <p>
     * 非递归实现的关键即是对node的切换。如同二分查找中对arr[l...r]边界l,r的切换。
     */
    public void insertNoneRecursive(Node node, K key, V value) {

        if (node == null)
            node = new Node(key, value);

        Node parentNode = node;
        Node cur = node;
        while (cur != null) {// 退出while循环后则说明此时cur引用指到了null。则证明该待插入的Node(key,value)就应该插入到此时cur指向位置。

            parentNode = cur;

            if (key.compareTo(cur.key) == 0) {
                cur.value = value;
                return;
            } else if (key.compareTo(cur.key) > 0)
                cur = cur.right;
            else // key.compareTo(cur.key) < 0
                cur = cur.left;
        }

        // 而此时parentNode指向的是待插入位置的父节点
        // 接下来就需要判断新插入的Node(key,value)的key是大于parent的key还是小于，决定了其插在parent节点的左孩子还是右孩子
        if (key.compareTo(parentNode.key) > 0)
            parentNode.right = new Node(key, value);
        else // key.compareTo(parentNode.key) < 0 。没有等于0的原因是如果==的话那么在上面的while循环就处理了
            parentNode.left = new Node(key, value);
        count++;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null)
            return false;

        if (key.compareTo(node.key) == 0)
            return true;
        else if (key.compareTo(node.key) < 0)
            return contains(node.left, key);
        else
            return contains(node.right, key);
    }

    /**
     * 非递归实现对key值在二分搜索树中的检索
     */
    public boolean containsNoneRecursive(K key) {

        if (root == null)
            return false;

        Node cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0)
                return true;
            else if (key.compareTo(cur.key) < 0)
                cur = cur.left;
            else // key.compareTo(cur.key) > 0
                cur = cur.right;
        }

        return false;
    }

    /**
     * value不能为value。如果函数返回value则证明没有从二叉搜索树中检索到该key值对应的value
     */
    public V search(K key) {
        return search(root, key);
    }

    private V search(Node node, K key) {

        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node.value;
        else if (key.compareTo(node.key) < 0)
            return search(node.left, key);
        else return search(node.right, key);
    }

    public V searchNoneRecursive(K key) {

        if (root == null)
            return null;

        Node cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0)
                return cur.value;
            else if (key.compareTo(cur.key) < 0)
                cur = cur.left;
            else cur = cur.right;
        }

        return null;
    }

    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    /**
     * 二分搜索树后序遍历的非递归实现
     * <p>
     * 先左右，后中。如果用栈实现的话，那么必然先入栈左子树。
     */
    public void postOrderNonRecursive() {

        if (root == null)
            return;

        /*负责存储待吐元素*/
        Stack<Node> stack = new Stack<>();
        /*负责存储二分搜索树中元素的待输出元素*/
        Stack<Integer> output = new Stack<>();

        stack.push(root);
        while (!stack.empty()) {

            Node cur = stack.pop();
            // 二分搜索树的元素越早入该元素输出栈的一定越晚被检出。
            // 所以一定是中间元素先入，然后右子树再入，然后左子树最后入
            output.push((Integer) cur.value);

            if (cur.left != null)// 对于stack栈一定是左子树先入。这样才能让后入的右子树先被吐出。
                // 才能让右子树的元素先于左子树入output元素输出栈
                stack.push(cur.left);
            if (cur.right != null)
                stack.push(cur.right);
        }

        while (!output.empty())
            System.out.println(output.pop());
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    /**
     * 根据中序遍历的特性。先输出各节点的左子树，所以必然root节点会一直向左深入，然后深入到底去输出最左下的孩子节点。
     * 然后再往上退的输出节点。被退到的节点就相当于其父节点的左孩子。输出该节点后，然后去指向并处理其右子树。
     * <p>
     * 中序遍历即遍历到该节点的时候，其父节点还没有被输出，要等待左子树作用完，其父亲节点才会被输出。
     */
    public void inOrderNonRecursive() {
        // 默写
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {

            if (cur != null) {// 左子树还没取完，那么先将遍历到的父亲节点依次压入栈。待左子树取完，栈才开始出栈
                stack.push(cur);
                cur = cur.left;
            } else {// 左子树取完，该栈做出栈操作了。即左孩子向上找其父节点了。即上一轮的左子树，需要让stack弹出
                cur = stack.pop();
                System.out.println(cur.value);
                // 中间元素处理完了。该去处理其右子树了
                cur = cur.right;
            }
        }

        /*if (root == null)
            return;

        Stack<Node> stack = new Stack<Node>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {// 两个作用条件。一个是树的不断左深入取左子树元素，一个是关系到栈中元素的吐出。

            if (cur != null) {// 一直向下，左孩子还没有拿完
                stack.push(cur);
                cur = cur.left;
            } else {// 左 ->左孩子被拿光。此次while循环进来的条件是 !stack.isEmpty()
                cur = stack.pop();
                // 中
                System.out.println(cur.value);
                // 右
                cur = cur.right;
            }
        }*/
    }

    private void preOrder(Node node) {

        if (node == null)
            return;

        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 栈实现 二分搜索树的非递归前序遍历
     * <p>
     * 前序遍历即遍历到该节点的时候，其父节点一定被输出过了。
     */
    public void preOderNoneRecursive() {

        if (root == null)
            return;

        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.value);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    public void levelOrder() {

        if (root == null)
            return;

        Queue<Node> queue = new ArrayBlockingQueue<>(count);
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur);

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 寻找二分搜索树中最小值的key
     */
    public K minimum() {
        assert count > 0;

        Node minimumNode = minimum(root);
        return minimumNode.key;
    }

    // 寻找二分搜索树的最大的键值
    public K maximum() {
        assert count != 0;

        Node maxNode = maximum(root);
        return maxNode.key;
    }

    // 返回以node为根的二分搜索树的最大键值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        else // node.left != null
            return minimum(node.left);
    }

    public K minimumNonRecursive() {

        assert count > 0;

        Node cur = root;
        while (cur.left != null)
            cur = cur.left;

        return cur.key;
    }

    public K maximumNonRecursive() {

        assert count > 0;

        Node cur = root;
        while (cur.right != null)
            cur = cur.right;

        return cur.key;
    }
}
