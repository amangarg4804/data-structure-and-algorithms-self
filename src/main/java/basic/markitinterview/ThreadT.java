package basic.markitinterview;

public class ThreadT implements Runnable {
  public static void main(String argv[]) {
    ThreadT t1 = new ThreadT();
    Thread t = new Thread(t1);
    t.start();
  }
  public void run() {
    while (true) {
      try {
        Thread.currentThread().sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("thread running");
    }
  }
}