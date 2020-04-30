package a_b_改善;

public class TryReturn测试 {
    public static void main(String[] args) {

        System.out.println(caculate());

    }

    private static int caculate() {
        int x = 2;
        try {
            x = 3;
            return x;
        } finally {
            System.out.println("finally");
            x = 4;
//            return x;
        }
    }
}
