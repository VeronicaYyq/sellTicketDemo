package Demo02;

import javax.sound.midi.Soundbank;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Veronica @create 2019-09-18 20:31 题目：现在两个线程，可以操作初始值为零的一个变量， 实现一个线程对该变量加1，一个线程对该变量减1， 实现交替，来10轮，变量初始值为零。
 */
public class TestLock02 {
    public static void main(String[] args) {
       /* Demo demo = new Demo();
       new Thread(() -> {
           for (int i = 1; i <=5 ; i++) {
               try {
                   demo.add();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       },"A").start();
        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    demo.decr();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();*/

            Demo ec = new Demo();

            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {

                    try {
                        ec.add();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"A") .start();
            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {

                    try {
                        ec.decr();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"B") .start();
        }
    }


class Demo {
    private int i = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() throws Exception {
        lock.lock();
        try {
            if(i!=0){
                condition.await();
            }
            i++;
            System.out.println(Thread.currentThread().getName()+ i);
            condition.signalAll();
        } finally {
             lock.unlock();
        }
    }

    public void decr() throws Exception {
        lock.lock();
        try {
            if(i!=1){
                condition.await();
            }
            i--;
            System.out.println(Thread.currentThread().getName()+ i);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    }
