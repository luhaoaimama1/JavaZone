package a_b_改善;

public class JavaCCC {
    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
        }
        System.out.println(sb.toString());
    }
}
