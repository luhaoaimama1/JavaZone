package 算法.算法书籍;

public class CompareUtils {
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        StringBuilder sb = new StringBuilder();
        for (Comparable comparable : a) {
            sb.append(comparable + "\t");
        }
        System.out.println(sb.toString());
    }
}


