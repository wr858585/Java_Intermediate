package com.oono.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁 --> JDK5.0新增
 *
 * 1. 面试题：synchronized和Lock的异同？
 * 相同点：二者都是用来解决线程安全问题的
 * 不同点：
 * ① synchronized的方式（无论是用来修饰方法还是代码块），都是在执行完同步代码以后自动地释放同步监视器
 * ② Lock的方式需要手动的去启动同步（调用lock()方法），手动的去结束同步（调用unlock()方法）
 *
 * 2. recommended order of use
 * Lock --> synchronized block --> synchronized method
 * 只是一个建议，实际开发中均可使用
 * 建议的原因：Lock的方式可以让我们更灵活地设置同步结束的位置/时间，而synchronized无论修饰方法还是代码块都只能等各自代码执行完成后才能解锁（语法内置）
 *      而同步代码块又稍微比同步方法适用性更广一点，因为同步方法需要要求方法中的所有代码全部是需要同步的代码，不然很难达到我们预期的需求
 *
 * 3. 面试题：如何解决线程的安全问题，有几种方式？
 * ① synchronized去修饰代码块或者方法，被修饰的结构就是需要被同步的代码
 * ② Lock接口的实现对象，去调用lock()和unlock()方法，来设置需要被同步的代码
 *
 * @author oono
 * @date 2020 07 14
 */
class Window implements Runnable{

    private int ticket = 100;

    //1. 实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true){
            try {

                //2. 调用锁定的方法lock()
                lock.lock();

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "：售票，票号为：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            } finally {

                //3. 调用解锁的方法unlock()
                lock.unlock();
            }
        }
    }
}


public class LockTest {

    public static void main(String[] args) {

        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
