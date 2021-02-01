package a_b_改善;

public class JavaCCC {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
        }
        System.out.println(sb.toString());

        try {
            haha();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void haha() {
        haha2();
    }

    public static void haha2() {
        throw new IllegalStateException("天真");
    }
}
