package 算法.算法书籍.符号表;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 有序查找的符号表
 * rank(k) 输入一个key 在有kList序列表中返回比他小的数量。也就是k应该放入index
 * 放入都是有序的。 查找也是有序的
 */
public class HalfFindST<K extends Comparable<K>, V> implements Iterable<Pair<K, V>> {

    public static void main(String[] args) {
        String[] arrays = UnSortLinkST.arrays;
        HalfFindST<String, Integer> map = new HalfFindST();
        for (int i = 0; i < arrays.length; i++) {
            map.put(arrays[i], i);
        }

        for (Pair<String, Integer> pair : map) {
            System.out.println("key:" + pair.getKey() + "\t" +
                    "value:" + pair.getValue() +
                    "\t");
        }

    }

    List<K> kList = new ArrayList<K>();
    List<V> vList = new ArrayList<V>();

    int size = 0;

    public int size() {
        return size;
    }

    public V get(K k) {
        int rank = rank(k);
        // 如果小于size 并且key和当前位置的key一样就是命中了
        if (rank < size && kList.get(rank).compareTo(k) == 0)
            return vList.get(rank);
        return null;
    }


    public void put(K k, V v) {
        int rank = rank(k);
        // 如果小于size 并且key和当前位置的key一样就是命中了。不一样的话代表插入的位置是当前的位置。
        if (rank < size && kList.get(rank).compareTo(k) == 0) {
            vList.set(rank, v);
        } else { //未找到
            vList.add(rank, v);
            kList.add(rank, k);
            size++;
        }
    }

    // 想好快速理解 就要 画图
    // 在有序数组 小于k元素的数量。二分法 分到一个元素的时候 对比k.然后就知道k应该放在那里了
    // lo-mid-hi，不停的用中值切分一半。 直到分成一个元素。对比k的话就知道k应该放哪里了。
    // 其实就是和一个元素对比 就能知道范围。对于中值的切分元素 如果=这个元素。那么也就知道位置了。
    // 放哪里的意思就是小于k的数量 因为index=前面元素的数量。 在不懂的话就是a[5]的话是不是前面5个元素。
    // @return 返回k元素应该放入的index，也就是小于k元素的数量。
    public int rank(K k) {
        //hi 包括N 因为 小于k的数量 是0-N.
        int lo = 0, hi = kList.size() - 1, mid = 0;
        while (lo <= hi) { //>就退出了  对于lo=hi那就是二分到最后的那次 。然后对比k
            mid = (lo + hi) / 2;
            int compare = k.compareTo(kList.get(mid));
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    private class ReverseIt implements Iterator<Pair<K, V>> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Pair<K, V> next() {
            K k = kList.get(cursor);
            V v = vList.get(cursor);
            cursor++;
            return new Pair<K, V>(k, v);
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new ReverseIt();
    }
}
