package 算法.算法书籍.符号表.前后中序遍历;

import java.util.Stack;

public class 前序遍历 {
    public static void main(String[] args) {


        TreeNode tnD = new TreeNode("D", null, null);
        TreeNode tnE = new TreeNode("E", null, null);
        TreeNode tnB = new TreeNode("B", tnD, tnE);


        TreeNode tnF = new TreeNode("F", null, null);
        TreeNode tnC = new TreeNode("C", tnF, null);

        TreeNode tnA = new TreeNode("A", tnB, tnC);




        System.out.print("中序遍历:");
        midOrderIterator(tnA);
        System.out.println();

        System.out.print("前序遍历:");
        beforeOrderIterator(tnA);
        System.out.println();

        System.out.print("后序遍历:");
        afterOrderIterator(tnA);
        System.out.println();
    }

    //todo  总结就是中在哪 中在左右的中间就是中序遍历，在左也就是前 就是前序遍历 在后也就是右侧就是后续遍历
    //左中右
    private static void midOrderIterator(TreeNode tnA) {
        if (tnA == null) return;
        midOrderIterator(tnA.left);
        System.out.print(tnA.value+"\t");
        midOrderIterator(tnA.right);
    }



    //中左右
    private static void beforeOrderIterator(TreeNode tnA) {
        if (tnA == null) return;
        System.out.print(tnA.value+"\t");
        midOrderIterator(tnA.left);
        midOrderIterator(tnA.right);
    }

    //左右中
    private static void afterOrderIterator(TreeNode tnA) {
        if (tnA == null) return;
        midOrderIterator(tnA.left);
        midOrderIterator(tnA.right);
        System.out.print(tnA.value+"\t");
    }
//
//    //非递归思想
//    private static void afterOrderIterator2(TreeNode tnA) {
//        Stack<TreeNode> stack = new Stack<>();
//
//        while(tnA.){
//            stack.add(tnA);
//            stack.add(tnA.right);
//            stack.add(tnA.left);
//        }
//
//
//    }


    public static class TreeNode {
        private String value;
        public TreeNode right;
        public TreeNode left;

        public TreeNode(String value, TreeNode left, TreeNode right) {
            this.value = value;
            this.right = right;
            this.left = left;
        }
    }
}
