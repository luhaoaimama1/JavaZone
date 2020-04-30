package 算法.算法书籍.符号表;

import javafx.util.Pair;

import java.util.Iterator;

/**
 * 二叉树查找
 * 根据节点的特性。
 * 从零开始搭建， 每次add的数据 子上到下 对比。直到节点的某个子连接 为空 新建节点并放入。
 * 所以 一定是： 左边的节点的子树的任何节点 < 当前节点 < 右边节点的子树的任何节点
 */
public class HalfTreeST<K extends Comparable<K>, V>
//        implements Iterable<Pair<K, V>>
{
    public static void main(String[] args) {
        String[] arrays = UnSortLinkST.arrays;
        HalfTreeST<String, Integer> map = new HalfTreeST();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
        }
    }

    Node root;//只有root节点没有父节点

    /**
     * left<now<right
     */
    private class Node {
        K k;
        V v;
        int size = 1;
        Node left;
        Node right;

        public Node(K k, V v, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node node) {
        if (node == null) return 0;
//        return size(node.left) + size(node.right) + 1;
        return node.size;
    }

    public V get(K k) {
        return get(root, k);
    }

    //找数据  如果k小于node.k就在node的子节点递归。大于就右节点递归。如果等于那就返回了
    //当node=null 就代表找到最底层了也没有。 二叉树中就没有这个k值。
    public V get(Node node, K k) {
        if (node == null) return null;//未命中

        int compare = k.compareTo(node.k);
        if (compare < 0) return get(node.left, k);
        else if (compare > 0) return get(node.right, k);
        else return node.v;
    }

    public Node getNode(Node node, K k) {
        if (node == null) return null;//未命中

        int compare = k.compareTo(node.k);
        if (compare < 0) return getNode(node.left, k);
        else if (compare > 0) return getNode(node.right, k);
        else return node;
    }

    public void put(K k, V v) {
        root = put(root, k, v);
    }

    /**
     * 每次add的数据 子上到下 对比。直到节点的某个子链接 为空 新建节点并放入这个子链接
     *
     * @param node
     * @param k
     * @param v
     * @return
     */
    public Node put(Node node, K k, V v) {
        //为什么不判断node。left或者right为空呢为停止条件呢
        //因为这样compare 会出现两次。因为递归方法里有这个逻辑。而终止条件也有 这样很难看
        if (node == null) return new Node(k, v, null, null);
        int compare = k.compareTo(node.k);
        if (compare < 0) node.left = put(node.left, k, v);//左侧递归
        else if (compare > 0) node.right = put(node.right, k, v);//右侧递归
        else node.v = v; //找到了
        //put路径上 ，更新节点的size  类似归并的那种操作  运行逻辑是从下到上。就是分治 的治。
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    //分两段逻辑  要找到 第
    public void remove(K k) {

    }

    public Node remove(Node node, K k) {
        if (node == null) return null;//未命中

        int compare = k.compareTo(node.k);
        if (compare < 0) node.left = remove(node.left, k);
        else if (compare > 0) node.right = remove(node.right, k);
        else { //=0 找到了
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            //都不为空 的话就是一个怎么与两个交换呢？
            //记得这个结论把 一定是： 左边的节点的子树的任何节点 < 当前节点 < 右边节点的子树的任何节点
            //就是在排序中 比他大一个的那个元素。就是他的右节点的最小元素。
            node = findMin(node.right);
        }
        return node;
    }

    public Node removeMin(Node node) {
        if (node.left == null) return node.right; //找到了最小的
        node.left = removeMin(node.left);
        return node;
    }

    public Node removeMin2(Node node) {
        if (node.left == null) {
            node.size = -1;
            return node;
        }
        ; //找到了最小的
        Node min = removeMin2(node.left);
        if (min.size == -1) {//
            node.left = min.right;
        }
        return min;
    }


    public Node findMin(Node node) {
        if (node.left == null) return node;
        else return findMin(node.left);
    }


//    private class ReverseIt implements Iterator<Pair<K, V>> {
//
//        int cursor = 0;
//
//        @Override
//        public boolean hasNext() {
//            return cursor < size;
//        }
//
//        @Override
//        public Pair<K, V> next() {
//            K k = kList.get(cursor);
//            V v = vList.get(cursor);
//            cursor++;
//            return new Pair<K, V>(k, v);
//        }
//    }
//
//    @Override
//    public Iterator<Pair<K, V>> iterator() {
//        return new ReverseIt();
//    }
}
