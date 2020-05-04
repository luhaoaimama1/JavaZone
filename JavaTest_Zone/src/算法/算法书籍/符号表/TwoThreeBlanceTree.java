package 算法.算法书籍.符号表;

import edu.princeton.cs.algs4.RedBlackBST;
import javafx.util.Pair;
import 算法.算法书籍.工具.DrawUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TwoThreeBlanceTree<K extends Comparable<K>, V>
//        implements Iterable<Pair<K, V>>
{

    //todo 删除还没有做呢

    public static void main(String[] args) {  //todo 怎么吧变量变成私有  和内部类不要变成静态类啊？\
        //用什么方式去展示？  平衡树( 2-3查找树) 还是 二叉树的方式去显示
        //不管什么都是 红色树。
        boolean isBlanceShow = true;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            list.add(String.valueOf((char) i));
        }
        String[] arrays = new String[list.size()];
        list.toArray(arrays);

        TwoThreeBlanceTree<String, Integer> map = new TwoThreeBlanceTree();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
        }

        map.deleteMin(map.root);


        DrawUtils.drawNode2(map.root, new DrawUtils.NodeCallback<TwoThreeBlanceTree.Node<String, Integer>>() {
            @Override
            public TwoThreeBlanceTree.Node<String, Integer> getLeftNode(TwoThreeBlanceTree.Node stringIntegerNode) {
                return stringIntegerNode.left;
            }

            @Override
            public TwoThreeBlanceTree.Node<String, Integer> getRightNode(TwoThreeBlanceTree.Node stringIntegerNode) {
                return stringIntegerNode.right;
            }

            @Override
            public Color getColor(TwoThreeBlanceTree.Node stringIntegerNode) {
                if (stringIntegerNode.color == TwoThreeBlanceTree.RED) return Color.RED;
                else return Color.black;
            }

            @Override
            public int getRadius() {
                return 26;
            }

            @Override
            public int getTextSize() {
                return 26;
            }

            @Override
            public String getText(TwoThreeBlanceTree.Node stringIntegerNode) {
                return (String) stringIntegerNode.k;
            }

            @Override
            public int getNodeCount(TwoThreeBlanceTree.Node<String, Integer> stringIntegerNode) {
                return TwoThreeBlanceTree.getNodeSize(stringIntegerNode);
            }

            @Override
            public Point calNextTPoint(int cirX, int cirY, TwoThreeBlanceTree.Node<String, Integer> nextT, boolean isLeft) {
                Point point = new Point();
                int gaps = 20;

                int nodeCount = isLeft ? getNodeCount(getRightNode(nextT)) : getNodeCount(getLeftNode(nextT));
                point.x = cirX + getRadius() * 2 * (nodeCount + 1) * (isLeft ? -1 : 1) + gaps * (isLeft ? -1 : 1);
                if (isBlanceShow)
                    //红色的话就持平现实
                    point.y = cirY + (nextT.color == TwoThreeBlanceTree.RED ? 0 : getRadius() * 4);
                else
                    point.y = cirY + getRadius() * 4;
                return point;
            }
        });
    }

    public static boolean RED = true;
    public static boolean BLACK = false;

    public static class Node<K, V> {
        public K k;
        public V v;

        public Node left;
        public Node right;

        public int size;
        public boolean color = RED;

        public Node(K k, V v, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public static int getNodeSize(TwoThreeBlanceTree.Node a) {
        if (a == null) return 0;
        return getNodeSize(a.left) + getNodeSize(a.right) + 1;
    }

    //因为使用他的时候已经判断子右侧是红了 所以这里不用再判断了
    private Node leftRorate(Node a) {
        Node b = a.right;
        b.color = a.color;
        a.color = RED;
        a.right = b.left;
        b.left = a;
        return b;
    }

    //因为使用他的时候已经判断子右侧是红了 所以这里不用再判断了
    private Node rightRorate(Node p) {
        Node b = p.left;
        b.color = p.color;
        p.color = RED;
        Node rightTemp = b.right;
        b.right = p;
        p.left = rightTemp;
        return b;
    }

    private void flipColor(Node p) {
        p.color = RED;
        p.left.color = BLACK;
        p.right.color = BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        else return node.color == RED;
    }

    public Node root;

    public void put(K k, V v) {
        root = put(root, k, v);
    }

    //递归
    public Node put(Node node, K k, V v) {
        //递归先考虑终止条件
        if (node == null) return new Node(k, v);
        //先一层一层对比 找到该插入的位置 或者真正的k替换值

        int compare = k.compareTo((K) node.k);
        if (compare < 0) node.left = put(node.left, k, v);
        else if (compare > 0) node.right = put(node.right, k, v);
        else {
            node.v = v;
            return node;
        }

        //nextNode 一定不为空
        if (!isRed(node.left) && isRed(node.right)) node = leftRorate(node);
        //经过左旋转后  左侧一定有子节点 所以不需要空判断
        if (isRed(node.left) && isRed(node.left.left)) node = rightRorate(node);
        if (isRed(node.left) && isRed(node.right)) flipColor(node);
        //最终把这个节点返回给上层 上层继续处理红链的问题
        return node;
    }

    public V get(K k) {
        Node node = get(root, k);
        if (node == null) return null;
        else return (V) node.v;
    }

    public Node get(Node node, K k) {
        //递归先考虑终止条件
        if (node == null) return null;
        //先一层一层对比 找到该插入的位置 或者真正的k替换值

        int compare = k.compareTo((K) node.k);
        if (compare < 0) return get(node.left, k);
        else if (compare > 0) return get(node.right, k);
        else return node;
    }
//
//    class IteratorImpl implements Iterator<Pair<K, V>> {
//
//        @Override
//        public boolean hasNext() {
//            return false;
//        }
//
//        @Override
//        public Pair<K, V> next() {
//            return null;
//        }
//    }
//
//    @Override
//    public Iterator<Pair<K, V>> iterator() {
//        return null;
//    }

    //换他的颜色 和左右即可。
    public void changeColor(Node node1, Node node2) {
        boolean tempColor = node1.color;
        node1.color = node2.color;
        node2.color = tempColor;
    }

    public void changeLeft(Node node1, Node node2) {
        Node temp = node1.left;
        node1.left = node2.left;
        node2.left = temp;
    }

    public void changeRight(Node node1, Node node2) {
        Node temp = node1.right;
        node1.right = node2.right;
        node2.right = temp;
    }


    //找到左侧节点的最大值 然后替换
    //find  replace a 的node并返回
    public Node changeLeftMaxNode(Node node, Node a) {
        if (a.left.right == null) { //如果是找到的节点 与就是a左子节点 那么交换方式如下
            Node b = a.left;

            a.left = b.left;
            b.left = a;
            changeRight(a, b);
            changeColor(a, b);
            return b;
        }

        if (node.right == null) { //找到可替换的了
            changeColor(a, node);
            changeRight(a, node);
            changeLeft(a, node);
            return node;
        }
        return changeLeftMaxNode(node.right, a);
    }

//    public void delete(K k) {
//        root = delete(root, k);
//    }
//
//    public Node delete(Node node, K k) {
//        if (node.left == null && node.right == null) return node;
//        node.left.color = RED;
//        node.right.color = RED;
//
//
//        return blance(node);
//    }

    public Node blance(Node node) {
        //nextNode 一定不为空
        if (isRed(node.right)) node = leftRorate(node);

        //经过左旋转后  左侧一定有子节点 所以不需要空判断
        if (isRed(node.left) && isRed(node.left.left)) node = rightRorate(node);
        if (isRed(node.left) && isRed(node.right)) flipColor(node);
        return node;
    }

    //保持层不变
    public void flipColorReal(Node node) {
        if (node.left != null)
            node.left.color = !node.left.color;
        if (node.right != null)
            node.right.color = !node.right.color;
        node.color = !node.color;
    }

    private Node downLeft(Node node) {
        if (isRed(node.left)) { //左侧是红色  翻转左侧的颜色
            Node leftNode = node.left;
            flipColorReal(leftNode);
            if (isRed(leftNode.right)) node.left = leftRorate(node);
            return leftNode;  //为什么不是leftNode因为 只有删除最小才是用那个
        } else { //左侧不是红色 那意思两边都是黑色 那么翻转层数不变
            flipColorReal(node);
            if (isRed(node.right)) node.left = leftRorate(node);
            return node;
        }
    }

    public Node deleteMin(Node node) {
        if (node.left == null) return null; //删除操作
        node.left = deleteMin(downLeft(node).left);
        return blance(node);
    }


}
