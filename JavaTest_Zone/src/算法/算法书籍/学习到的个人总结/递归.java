package 算法.算法书籍.学习到的个人总结;

import 算法.算法书籍.符号表.HalfTreeST;

public class 递归 {
    public static void main(String[] args) {
        int[] arrays=new int[10];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i]=i;
        }

        //递归的三种总结 + removeMin方法的范例解析
        递归(arrays,0);
        递归有返回值(arrays,0);
        递归Index(arrays,0, new RecursionIndex());
    }

    //为了让这里也能用到 我就把private改成了public

    // 这个递归 非常有研究意义 是在二叉查找书中 找到最小的元素 remove掉，
    // 但是如果最小的元素右边有节点的话。那么最小元素的父节点 的左连接 放到 最小元素的右侧节点上。没有的话放也是可以的

    //todo 注意：用递归来做就是  找到目标节点的话。目标节点的父节点在该方法中无法用到。所以要有返回值。 目标节点的父节点用这个返回值。
    // 那么注意删除目标的的父节点 用的返回值就是 目标节点的右侧节点。也就是node.left = removeMin(node.left);
    // 这个时候 需要弄个返回值。给当前node（目标节点的父节点）的父节点还走node.left = removeMin(node.left);  这段逻辑 。 如何让这段逻辑
    // 走过之后  保持 这步之前的二叉树相同呢？ 那么就是left还是left呗。所以返回值就是当前节点。

    //fixme 通过上面的分析。我明白了。我们做题的顺序一般都是从
    // 终止条件开始和找到目标开始 会返回值
    // 然后根据 终止的返回值 我们会走  归逻辑 【这个情况我们会重点分析 最后形成归的逻辑】
    // 然后走正常的返回值逻辑（无返回值的话其实一样 return void的呗 也是返回值逻辑）
    // 在然后 就是 往上一层 走归的逻辑  。这次归的逻辑要用到 正常的返回值。所以也要处理好边界
    public static HalfTreeST.Node removeMin(HalfTreeST.Node node) {
        //终止条件
        if (node.left == null) return node.right; //找到了最小的
        //node.left = removeMin(node.left); 拆分成递和归
        //递
        HalfTreeST.Node resultNode = removeMin(node.left);
        //归
        node.left =resultNode ;
        //正常的返回值
        return node;
    }

    //最后就是加标识的一种方式了 这个还不如递归Index
    public HalfTreeST.Node removeMin2(HalfTreeST.Node node) {
        if (node.left == null) {
            node.size = -1;
            return node;
        }
        ; //找到了最小的
        HalfTreeST.Node min = removeMin2(node.left);
        if (min.size == -1) {//
            node.left = min.right;
        }
        return min;
    }

    // 递归的基础理解
    public static void 递归(int[] arrays, int index){
        //终止条件能写在第一行 就写在第一行
        if(index>=arrays.length) return;

        int useIndex = index;
        System.out.println("---------------->递的逻辑："+ useIndex);
        递归( arrays, ++index);
        System.out.println("归的逻辑："+useIndex+"<----------------");
    }

    /**
     *  递归 ，归带index。可以用来判断第几次归操作
     */
    public static void 递归Index(int[] arrays, int index, RecursionIndex recursionIndex){
        //终止条件能写在第一行 就写在第一行
        if(index>=arrays.length) return;

        System.out.println("iterIndex ---------------->递的逻辑："+ recursionIndex.backIndex);
        递归Index( arrays, ++index, recursionIndex);
        recursionIndex.backCount();
        System.out.println("iterIndex 归的逻辑："+ recursionIndex.backIndex  +"\t isFirstBack()"+recursionIndex.isFirstBack()+"<----------------");
    }

    /**
     *  对于有返回值的，终点返回值 和正常的返回值一定不一样 这样才能区别出来。
     */
    public static int 递归有返回值(int[] arrays, int index){
        //终止条件能写在第一行 就写在第一行
        if(index>=arrays.length) return -1 ;

        int useIndex = index;
        System.out.println("---------------->递的逻辑："+ useIndex);
        if(递归有返回值(arrays, ++index)==-1){
            System.out.print("！！！从终点 ");
        }else{
            System.out.print("~~~~非从终点 ");
        }
        System.out.println("归的逻辑："+useIndex+"<----------------");
        return 0;
    }

    //用Integer不行 貌似自动装箱的问题
    public static class RecursionIndex {
        int backIndex =0; //当index=-1的时候 就是归的起点；
        public int backCount(){
            return --backIndex;
        }
        public boolean isFirstBack(){
            return backIndex==-1;
        }
    }

}
