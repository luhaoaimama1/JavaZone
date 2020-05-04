package 算法.算法书籍.符号表;

import javafx.util.Pair;

import java.util.Iterator;

/**
 * 二叉树查找
 * 根据节点的特性。
 * 从零开始搭建， 每次add的数据 子上到下 对比。直到节点的某个子连接 为空 新建节点并放入。
 * 所以 一定是： 左边的节点的子树的任何节点 < 当前节点 < 右边节点的子树的任何节点
 */
public class HalfTreeST<K extends Comparable<K>, V> implements Iterable<Pair<K, V>> {
    public static void main(String[] args) {
        String[] arrays = UnSortLinkST.arrays;
        HalfTreeST<String, Integer> map = new HalfTreeST();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
        }


        for (Pair<String, Integer> pair : map) {
            System.out.println("key:" + pair.getKey() + "\t" +
                    "value:" + pair.getValue() +
                    "\t");
        }

        System.out.println(map.rank("A"));

        map.remove("E");

        for (Pair<String, Integer> pair : map) {
            System.out.println("key:" + pair.getKey() + "\t" +
                    "value:" + pair.getValue() +
                    "\t");
        }

        System.out.println(map.rank("A"));
    }

    Node root;//只有root节点没有父节点

    /**
     * left<now<right
     */
    public class Node {
        public K k;
        public V v;
        public int size = 1;
        public Node left;
        public Node right;

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
        root = remove(root, k);
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
            Node t = node;
            node = findMin(t.right);
            //= node.right=t.right;  removeMin(t.right) 删除最小的元素
            node.right = removeMin(t.right);

            node.left = t.left;//删除完了才能给他左侧放节点
        }
        node.size = size(node.left) + size(node.right) + 1; //从下到上对该路径进行更新
        return node; //这个给父亲 父亲会链接上他的
    }

    public Node removeMin(Node node) {
        if (node.left == null) return node.right; //找到了最小的
        node.left = removeMin(node.left);
        node.size = size(node.left) + size(node.right) + 1; //从下到上对该路径进行更新
        return node;
    }

    public Node findMin(Node node) {
        if (node.left == null) return node;
        else return findMin(node.left);
    }

    //找到K应该放的位置
    public int rank(K k) {
        return rank(root, k);
    }

    //小于k的个数
    public int rank(Node node, K k) {
        if (node == null) return 0;
        int compare = k.compareTo(node.k);
        //k<node 左侧
        if (compare < 0) return rank(node.left, k);
            //k>node 右侧。 左侧和当前节点 都小于K
        else if (compare > 0) return rank(node.right, k) + size(node.left) + 1;
        else return size(node.left);
    }

    public Node select(int k) {
        return select(root, k);
    }

    //找到排名k 从 0开始 的节点， 就是正好有k个小于他的键。
    public Node select(Node node, int k) {
        if (node == null) return null;
        int size = size(node.left);  //小于的node的数量
        //左侧 继续找
        if (k < size) return select(node.left, k);
            //右侧 因为左侧和当前节点都小于 所以 要在右侧找 k-leftsize-1
        else if (k > size) return select(node.right, k - size - 1);
        else return node;
    }


    private class ReverseIt implements Iterator<Pair<K, V>> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size(root);
        }

        @Override
        public Pair<K, V> next() {
            Node select = select(cursor++);
            return new Pair<K, V>(select.k, select.v);
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new ReverseIt();
    }
}
