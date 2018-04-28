package 算法;

import java.util.Stack;

/**
 * Created by fuzhipeng on 2018/4/27.
 * <p>
 * 从尾到头打印链表   stack先进后出的收集方式
 */
public class LinkPrintEndToStart {
    public static void main(String[] args) {
        Node root = new Node();
        Node element2 = new Node();
        element2.value = 2;
        root.next = element2;
        root.value = 1;
        print(root);
    }

    private static void print(Node root) {
        Stack<Node> stack = new Stack<Node>();

        Node node = root;
        while (node != null) {
            stack.add(node);
            node = node.next;
        }


        while (!stack.empty()) {

            System.out.println(stack.pop().value);
        }
    }

    static class Node {
        public int value;
        public Node next;

    }
}
