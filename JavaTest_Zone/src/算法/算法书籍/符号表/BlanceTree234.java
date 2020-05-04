package 算法.算法书籍.符号表;

import 算法.算法书籍.工具.DrawUtils;

import java.awt.*;
import java.util.ArrayList;

/**
 * 可能存在 2  3 4 （两个子都是红色的） 节点
 * 左倾 代表右侧为红的时候那么左侧必定是红色。
 * 对于每个节点，从该节点到其后代叶节点的简单路径上，均包含相同数目的黑色节点
 * <p>
 * 节点是黑色的
 * 如果一个节点是红色，那么它的两个子节点就是黑色的（没有连续的红节点）
 *
 * @param <K>
 * @param <V>
 */
public class BlanceTree234<K extends Comparable<K>, V>
//        implements Iterable<Pair<K, V>>
{


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

        BlanceTree234<String, Integer> map = new BlanceTree234();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
        }

//        map.deleteMax();
//        map.deleteMin();
//        map.delete("X");
//        map.delete("E");
//        map.delete("D");
//        map.delete("J");
//        map.delete("A");
//        map.delete("Z");

        DrawUtils.drawNode2(map.root, new DrawUtils.NodeCallback<BlanceTree234.Node<String, Integer>>() {
            @Override
            public BlanceTree234.Node<String, Integer> getLeftNode(BlanceTree234.Node stringIntegerNode) {
                return stringIntegerNode.left;
            }

            @Override
            public BlanceTree234.Node<String, Integer> getRightNode(BlanceTree234.Node stringIntegerNode) {
                return stringIntegerNode.right;
            }

            @Override
            public Color getColor(BlanceTree234.Node stringIntegerNode) {
                if (stringIntegerNode.color == BlanceTree234.RED) return Color.RED;
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
            public String getText(BlanceTree234.Node stringIntegerNode) {
                return (String) stringIntegerNode.k;
            }

            @Override
            public int getNodeCount(BlanceTree234.Node<String, Integer> stringIntegerNode) {
                return BlanceTree234.getNodeSize(stringIntegerNode);
            }

            @Override
            public Point calNextTPoint(int cirX, int cirY, BlanceTree234.Node<String, Integer> nextT, boolean isLeft) {
                Point point = new Point();
                int gaps = 15; //不能超过半径的一半不然就回过头相交

                int nodeCount = isLeft ? getNodeCount(getRightNode(nextT)) : getNodeCount(getLeftNode(nextT));
                point.x = cirX + getRadius() * 2 * (nodeCount + 1) * (isLeft ? -1 : 1) + gaps * (isLeft ? -1 : 1);
                if (isBlanceShow)
                    //红色的话就持平现实
                    point.y = cirY + (nextT.color == BlanceTree234.RED ? 0 : getRadius() * 4);
                else
                    point.y = cirY + getRadius() * 4;
//                point.y=26; 让他们在一条线上
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

    public static int getNodeSize(BlanceTree234.Node a) {
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

    private Node flipColor(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        else return node.color == RED;
    }
    //为了理解加的操作 可不是效率

    private boolean isBLACK(Node node) {
        return !isRed(node);
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

        //先把4个节点的都分离了 。为什么这么做？ 因为这个是2 ，3，4节点树。
        // 不存在5，x+1后才能变成4 那么x最大就是3所以先把4的树都分离了
        if (isRed(node.left) && isRed(node.right)) flipColor(node);


        int compare = k.compareTo((K) node.k);
        if (compare < 0) node.left = put(node.left, k, v);
        else if (compare > 0) node.right = put(node.right, k, v);
        else {
            node.v = v;
            return node;
        }

        //左子不为红色 右侧为红色 右旋转当前节点
        if (isRed(node.right)) node = leftRorate(node);
        //这个修复可以产生  4节点
        if (isRed(node.left) && isRed(node.left.left)) node = rightRorate(node);

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

    //只有删除操作 才用到fixup所以就算没有4节点也关系 但是为什么呢？

    public Node fixUp(Node node) {
        //左子不为红色 右侧为红色 右旋转当前节点
        if (isRed(node.right)) node = leftRorate(node);
        //这个修复可以产生  4节点
        if (isRed(node.left) && isRed(node.left.left)) node = rightRorate(node);
        if (isRed(node.left) && isRed(node.right)) flipColor(node);
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    public Node deleteMax(Node node) {
        if (isRed(node.left)) node = rightRorate(node);
        if (node.right == null) return null;
        //右侧节点是2节点的话 处理
        // 2节点代表当前是黑色，并且他的左子也是黑色的
        //为什么考虑左子 因为我认为是在左倾平衡树的情况下删除哦
        if (isBLACK(node.right) && isBLACK(node.right.left)) { //右子是2节点
            node = moveRedToRight(node);
        }
        node.right = deleteMax(node.right);
        return fixUp(node); //归的时候要平衡节点
    }

    private Node moveRedToRight(Node node) {
        //左子是多节点的情况
        if (isRed(node.left.left)) { //不可能为null;  原因是deleteMax if (node.right == null) return null; 后面写的右边有 那么左边就有。
            flipColor(node);//临时组建多个节点
            //isRed(node.left) && isRed(node.left.left) 才能右旋转。但是这里是确定了
            //因为左右都是2节点既黑色  翻转颜色后就是红色， 而isRed(node.left.left) 已经判断了

            //为什么只考虑3节点的 4节点呢？ 4节点的情况我做了发现最后 node.right = deleteMax(node.right, k);这句话完全不管他的左侧 所以结果是一样的
            node = rightRorate(node);
            //把临时组建的多个节点 解除掉
            flipColor(node);

        } else {
            //左右子都是黑的 把右子的父亲也就是node借过来
            flipColor(node);
        }
        //flipColor 这么写是为了有助于理解 可以提出来的
        return node; //返回进来node的父亲用来指向他的
    }

    //moveRedToRight 反过来
    private Node moveRedToLeft(Node node) {
        //左子是多节点的情况
        if (isRed(node.right.left)) { //不可能为null;  原因是deleteMax if (node.right == null) return null; 后面写的右边有 那么左边就有。
            flipColor(node);//临时组建多个节点
            //isRed(node.left) && isRed(node.left.left) 才能右旋转。但是这里是确定了
            //因为左右都是2节点既黑色  翻转颜色后就是红色， 而isRed(node.left.left) 已经判断了

            //为什么只考虑3节点的 4节点呢？ 4节点的情况我做了发现最后 node.right = deleteMax(node.right, k);这句话完全不管他的左侧 所以结果是一样的
            node.right = rightRorate(node.right);
            node = leftRorate(node);
            //把临时组建的多个节点 解除掉
            flipColor(node);

        } else {
            //左右子都是黑的 把右子的父亲也就是node借过来
            flipColor(node);
        }
        //flipColor 这么写是为了有助于理解 可以提出来的
        return node; //返回进来node的父亲用来指向他的
    }

    public void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }

    public Node deleteMin(Node node) {
        //为什么不用了 因为最后删除的是在左侧。
//        if (isRed(node.left)) node = rightRorate(node);
        if (node.left == null) return null;
        //右侧节点是2节点的话 处理
        // 2节点代表当前是黑色，并且他的左子也是黑色的
        //为什么考虑左子 因为我认为是在左倾平衡树的情况下删除哦
        if (isBLACK(node.left) && isBLACK(node.left.left)) { //右子是2节点
            node = moveRedToLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node); //归的时候要平衡节点
    }

    public void delete(K k) {
        // if both children of root are black, set root to red
        if (isBLACK(root.left) && isBLACK(root.right))
            root.color = RED;

//        if (get(root, k) != null) {
//            root = delete(root, k);
//        }
        root = deleteMY(root, k);
    }

    //可以用他右侧最大的节点替换 或者左侧最小的替换
    //todo 无法理解 找到之前的左侧 用做小删除左的方法 右侧用删除右的方法这个
    //嗯也是从上面借 红色的。因为他都是为childe借的。所以当==的时候 ==的那个节点已经是最边上的了
    public Node delete(Node node, K k) {
        int compare = k.compareTo((K) node.k);
        if (compare < 0) {
            if (isBLACK(node.left) && isBLACK(node.left.left)) { //右子是2节点
                node = moveRedToLeft(node);
            }
            node.left = delete(node.left, k);
        } else {
//   算法第四版里 是这样的 不理解为什么=0  找到的时候也要旋转一下； 反正都要倒叙

            //特地找了一个"X"删除 ,因为X的左边V是红色的。
            // 发现这个是错的？我无法理解了 这个不是出版了么 咋还能出错呢？我老感觉我有问题但是我真的debug了
            //compare==0的时候不应该做 rightRorate(node)和moveRedToRight();
            // 然后我用deleteMY不包含 上面两部的就好使 我fuck.
            if (isRed(node.left)) node = rightRorate(node);
            if (compare == 0 && (node.right == null))
                return null; //在最底层 直接删除
            //右侧节点是2节点的话 处理
            // 2节点代表当前是黑色，并且他的左子也是黑色的
            //为什么考虑左子 因为我认为是在左倾平衡树的情况下删除哦
            if (isBLACK(node.right) && isBLACK(node.right.left)) { //右子是2节点
                node = moveRedToRight(node);
            }

            if (compare == 0) {
                //先把他 的kv删除了 其实这里就已经删除了
                node.k = findMin(node.right).k;
                node.v = get(node.right, (K) node.k).v;
                //删除他的右侧的最小元素。  deletemin归的整理了 节点。
                // 最后从他到上整理就好了
                node.right = deleteMin(node.right);
            } else node.right = delete(node.right, k);

        }
        return fixUp(node);
    }

    public Node deleteMY(Node node, K k) {
        int compare = k.compareTo((K) node.k);
        if (compare < 0) {
            if (node.left == null) {
                //如果删除之前判断是否含有的话 这个可以不写 ,现在这种情况要写 二叉树中没有的元素 既未命中 就是这种情况
                //fixme 最后发现还是 判断是否包含靠谱 为啥呢？因为如果没有删除的做多少冤枉操作啊 但是这个是为了理解 就不更改了
                return null;
            }
            if (isBLACK(node.left) && isBLACK(node.left.left)) { //右子是2节点
                node = moveRedToLeft(node);
            }
            node.left = deleteMY(node.left, k);
        } else if (compare > 0) {
            if (isRed(node.left)) node = rightRorate(node);
            if (node.right == null) {
                //如果删除之前判断是否含有的话 这个可以不写  , 现在这种情况要写 二叉树中没有的元素 既未命中 就是这种情况
                //fixme 最后发现还是 判断是否包含靠谱 为啥呢？因为如果没有删除的做多少冤枉操作啊 但是这个是为了理解 就不更改了
                return null;
            }
            //右侧节点是2节点的话 处理   2节点代表当前是黑色，并且他的左子也是黑色的
            //为什么考虑左子 因为我认为是在左倾平衡树的情况下删除哦
            if (isBLACK(node.right) && isBLACK(node.right.left)) { //右子是2节点
                node = moveRedToRight(node);
            }
            node.right = deleteMY(node.right, k);
        } else {
            //在右侧找到最小的元素 替换掉
            if (node.right != null) {
                Node willDeleteNode = node;
                //为什么要执行这个 因为要让右子 不是 2结点 ，这样才能对于deleteMin的root也就是不是2结点了
                //不加这个代码删除D,J就会有问题 为什么因为他的min那侧的root是2节点
                if (isRed(node.left)) node = rightRorate(node);
                if (isBLACK(node.right) && isBLACK(node.right.left)) { //右子是2节点
                    node = moveRedToRight(node);
                }
                if (willDeleteNode == node) { //为啥加这个 因为上面的旋转会导致节点当前node变更 。
                    // 例如删除A-Z中的X左侧V是红色的 所以他会旋转 。最后node是的K是'V'了。那么不能删除值了。
                    // 继续递归知道找到并旋转后节点还是还是compare==0那个节点 。
                    // 有人说为啥不用 node.k.compare(node.k)其实可以但是我怕有些人有点蒙理解不了反而问为啥不用compare这个引用了

                    //先把他 的kv删除了 其实这里就已经删除了
                    Node realDeleteNode = findMin(node.right);
                    node.k = realDeleteNode.k;
                    node.v = realDeleteNode.v;

                    //删除他的右侧的最小元素。  deletemin归的整理了 节点。
                    node.right = deleteMin(node.right);
                } else
                    node.right = deleteMY(node.right, k);
            } else {  //右侧为空的情况
                if (isRed(node.left)) {
                    //左侧是红色的  应对删除最大值的逻辑
                    // 如果不加这个段逻辑直接return null那么没法删除最大值
                    node = rightRorate(node);
                    //因为node.right==null 所以仅仅写rightRorate(node);这个就行了 跳过下面的
//                    if (isBLACK(node.right) && isBLACK(node.right.left)) {
//                        node = moveRedToRight(node);
//                    }
                    node.right = deleteMY(node.right, k);
                } else return null;//找到了 在最底层 直接删除
            }
        }
        return fixUp(node);
    }

    public Node findMin(Node node) {
        if (node.left == null) return node;
        else return findMin(node.left);
    }

}
