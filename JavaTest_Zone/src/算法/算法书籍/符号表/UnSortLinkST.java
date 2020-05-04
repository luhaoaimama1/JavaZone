package 算法.算法书籍.符号表;

import javafx.util.Pair;
import java.util.Iterator;

/**
 * 无需链表 的符号表
 * @param <K>
 * @param <V>
 */
public class UnSortLinkST<K extends Comparable<K>, V> implements Iterable<Pair<K, V>> {
    public static String[] arrays = new String[]{
            "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"
    };

    public static void main(String[] args) {
        UnSortLinkST<String, Integer> map = new UnSortLinkST();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
        }

        for (Pair<String, Integer> pair : map) {
            System.out.println("key:" + pair.getKey() + "\t" +
                    "value:" + pair.getValue() +
                    "\t");
        }

    }

    class Node {
        K k;
        V v;
        Node next;

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    int size = 0;

    Node firstNode;

    public int size() {
        return size;
    }

    public V get(K k) {
        //和while的不同 就是先做 在赋值，
        for (Node i = firstNode; i != null; i = i.next) {
            if (i.k.compareTo(k) == 0) {
                return i.v;
            }
        }
        return null;
    }


    public void put(K k, V v) {
        //和while的不同 就是先做 在赋值，
        for (Node i = firstNode; i != null; i = i.next) {
            if (i.k.compareTo(k) == 0) {
                i.v = v;
                return;
            }
        }
        //没找到  把新建节点 把当前第一个放到他的next中 然后他变成第一个节点
        firstNode = new Node(k, v, firstNode);
        size++;
    }


    private class ReverseIt implements Iterator<Pair<K, V>> {
        public Node cursorNode= new Node(null, null, firstNode);

        @Override
        public boolean hasNext() {
            return cursorNode.next != null;
        }

        @Override
        public Pair<K, V> next() {
            Node next = cursorNode.next;
            cursorNode = cursorNode.next;
            return new Pair<K, V>(next.k, next.v);
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new ReverseIt();
    }
}
