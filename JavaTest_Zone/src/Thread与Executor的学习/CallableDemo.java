package Thread与Executor的学习;

//: concurrency/CallableDemo.java
import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {
  private int id;
  public TaskWithResult(int id) {
    this.id = id;
  }
  public String call() {
    return "result of TaskWithResult " + id;
  }
}
class TaskWithResult2 implements Callable<String> {
	public String call() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "result 2222";
	}
}

public class CallableDemo {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    ArrayList<Future<String>> results =
      new ArrayList<Future<String>>();
    for(int i = 0; i < 10; i++)
      results.add(exec.submit(new TaskWithResult(i)));
    for(Future<String> fs : results)
      try {
        // get() blocks until completion:
        System.out.println(fs.get());
      } catch(InterruptedException e) {
        System.out.println(e);
        return;
      } catch(ExecutionException e) {
        System.out.println(e);
      } finally {
//    	 List<Runnable> notOver = exec.shutdownNow();
//        exec.shutdown();
// 		 上面那段代码很清楚地显示出二者的区别。
// 		 shutdown调用后，不可以再submit新的task，已经submit的将继续执行。
// 		 shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list
      }
    Future<String> temp = exec.submit( new TaskWithResult2());
    try {
		if (temp.isDone()) {
			System.out.println("你妹啊:" + temp.get());
		}
		//get为阻塞状态　而submit不为阻塞状态　即跑另一个线程　此线程继续执行
		 System.out.println("疯子:" + temp.get());
	} catch (InterruptedException | ExecutionException e) {
		e.printStackTrace();
	}
   
    System.out.println("over");
  }
} /* Output:
result of TaskWithResult 0
result of TaskWithResult 1
result of TaskWithResult 2
result of TaskWithResult 3
result of TaskWithResult 4
result of TaskWithResult 5
result of TaskWithResult 6
result of TaskWithResult 7
result of TaskWithResult 8
result of TaskWithResult 9
*///:~
