package a_新手;

public class MainActivity {

    String l = "aab";
    MyRunable a = new MyRunable();
    Runnable b = new Runnable() {
        @Override
        public void run() {

        }
    };

    public void onCreate() {
        new Runnable() {
            @Override
            public void run() {
                System.out.println(MainActivity.this.l);
            }
        }.run();
    }

    public static void main(String[] args) {

    }
}
