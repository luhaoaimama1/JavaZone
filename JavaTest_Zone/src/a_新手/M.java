package a_新手;

import java.util.Timer;
import java.util.TimerTask;

public class M {
    public static void main(String[] args) {  
        // TODO todo.generated by zoer  
        Timer timer = new Timer();

        timer.schedule(new MyTask(), 1000, 2000);  
        timer.schedule(new MyTask2(), 1000, 2000);
        timer.purge();
        timer.schedule(new MyTask2(), 1000, 2000);
    }
}  
  
class MyTask extends TimerTask {
  
    @Override  
    public void run() {  
        System.out.println("dddd");  
  
    }    
}


class MyTask2 extends TimerTask {

    @Override
    public void run() {
        System.out.println("dddd222");

    }
}