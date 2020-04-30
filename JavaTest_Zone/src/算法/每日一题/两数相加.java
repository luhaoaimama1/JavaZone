package 算法.每日一题;

import com.sun.istack.internal.Nullable;

/**
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 * Definition for singly-linked list.
 */
class 两数相加 {
    public static void main(String[] args) {
        addTwoNumbers2(new ListNode(5), new ListNode(5));
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int overflow = 0;
        add(l1, l2, overflow);
        return l1;
    }

    static public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int overflow = 0;
        ListNode result= new ListNode(0);
        ListNode p = l1, q = l2, curr = result;
        while (p != null || q != null) {
            int pval = p == null ? 0 : p.val;
            int qval = q == null ? 0 : q.val;

            int value = pval + qval + overflow;
            overflow = 0;
            if (value >= 10) overflow++;
            curr.next = new ListNode(value % 10); //计算出下当前循环的下一个元素的值
            curr = curr.next;//把这个一个元素 作为下次循环的起点
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (overflow > 0) {
            curr.next = new ListNode(overflow);
        }
        return result.next;
    }


    static private void add(ListNode l1, @Nullable ListNode l2, int overflow) {
        int value = l1.val + l2.val + overflow;
        overflow = 0;
        if (value >= 10) overflow++;
        l1.val = value % 10;
        if (overflow == 0 && l1.next == null && l2.next == null) {
            return;
        } else {
            if (l1.next == null) l1.next = new ListNode(0);
            if (l2.next == null) l2.next = new ListNode(0);
            add(l1.next, l2.next, overflow);
        }
    }
}