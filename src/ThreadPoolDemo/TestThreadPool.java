package ThreadPoolDemo;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Veronica
 * @create 2019-09-19 10:45
 */
public class TestThreadPool {
    /* public static void main(String[] args) {
         ExecutorService threadPool = new ThreadPoolExecutor(
                 2,5,3,
                 TimeUnit.SECONDS,new LinkedBlockingDeque<>(5),
                 Executors.defaultThreadFactory(),
                 new ThreadPoolExecutor.DiscardOldestPolicy()

         );
         try {
             for (int i = 1; i <=15 ; i++) {
                 threadPool.submit(() -> {
                     System.out.println(Thread.currentThread().getName()+"-----------"+ ((int) (Math.random()*100)));
                 });
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             threadPool.shutdown();
         }
     }*/
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 1; i <= 20; i++) {//模拟20个客户过来办理业务
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务，回执单：" + new Random().nextInt(5));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

