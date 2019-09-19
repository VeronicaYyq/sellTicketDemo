package Demo;

import jdk.internal.dynalink.support.TypeConverterFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MK
 * @create 2019-09-18 20:22
 */
public class TestDemo {
    public static void main(String[] args) {
        Demo01  s= new Demo01();
        new Thread(() -> {
            for (int i = 1; i <= 80; i++) {
                s.sell();//调用资源类的方法
            }
        },"D").start();
       /* new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 80; i++) {
                    s.sell();//调用资源类的方法
                }
            }
        }, "A").start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 80; i++) {
                    s.sell();//调用资源类的方法
                }
            }
        }, "B").start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 80; i++) {
                    s.sell();//调用资源类的方法
                }
            }
        }, "C").start();*/
    }
}
class Demo01 extends Thread{
    private int num = 80;
    private Lock lock =new ReentrantLock();
    public void sell() {
        lock.lock();

        try {
            if(num>0) {
                System.out.println(Thread.currentThread().getName()+"正在卖"+num--+"还剩余"+num+"张票");

            }
        } finally {
            lock.unlock();
        }
    }
}
