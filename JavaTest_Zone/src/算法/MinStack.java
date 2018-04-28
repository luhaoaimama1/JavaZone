package 算法;

import java.util.Stack;

/**
 * Created by fuzhipeng on 2018/4/27.
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 */
public class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    /**
     * 当推入的元素 大于 minstack站的值的话就不用存， 为什么呢？
     * 因为pop这个元素的 最小值还是是不变的。 所以不需要压入
     *
     * @param item
     * @return
     */
    public Integer push(Integer item) {
        stack.push(item);
        if (minStack.isEmpty())
            minStack.push(item);
        else {
            if (item < minStack.peek())
                minStack.push(item);
        }


        return item;
    }

    public Integer pop() {
        if (stack.peek() == minStack.peek())
            minStack.pop();
        return stack.pop();
    }

    public Integer peek() {
        return stack.peek();
    }

    public Integer minPeek() {
        return minStack.peek();
    }

}
