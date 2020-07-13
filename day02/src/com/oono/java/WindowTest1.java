package com.oono.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用实现Runnable接口的方式
 *
 * 仍然待解决：线程安全问题
 *
 * 1. 问题：卖票过程中，出现了重票和错票 --> 出现了线程的安全问题
 * 2. 问题出现的原因：当某个线程来操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票（车票就是一个共享的数据）
 * 3. 如何解决：当一个线程A在操作共享数据（ticket）的时候，其他线程不能参与进来，直到线程A操作完ticket的时候，其他线程才能操作ticket。这种情况即使线程A出现了阻塞也不能被改变
 * 4. 在java中，用同步机制来解决线程安全问题
 *
 * 方式一：同步代码块
 *
 * synchronized(同步监视器){
 *      //需要被同步的代码
 *
 * }
 *
 * 说明：
 * 1. 操作共享数据的代码，就是需要被同步的代码 --> 不能包含代码多了，也不能包含代码少了，得恰好是需要同步的代码
 * 2. 共享数据Df 指多个线程共同操作的变量
 * 3. 同步监视器，俗称：锁。任何一个类的对象都可以来充当锁。
 *      要求：多个线程必须要共用同一个锁
 *
 * 补充：
 * 在实现Runnable接口来创建多线程的方式中，可以考虑使用this来充当同步监视器
 * 在继承Thread类来创建多线程的方式中，慎用this来充当同步监视器（因为没有天然共享数据的特性），可以考虑用当前类来充当同步监视器，形如：className.class
 *
 *
 * 方法二：同步方法
 *
 * 如果操作共享数据的代码正好完整地声明/写在了一个方法中，我们可以直接将此方法声明为同步的，此时该方法可以叫做同步方法
 *
 *
 * 5. 同步的方式，解决了线程的安全问题 --> 好处
 *      但是，操作同步代码时，只能有一个线程参与，其他线程等待，相当于是一个单线程的过程，效率低 --> 一个局限
 *
 *
 *
 * @author oono
 * @date 2020 07 12
 */

class Window1 implements Runnable {

    private int ticket = 100;//这里就不像继承Thread类的方式需要给ticket加static了
    Object obj = new Object();

    @Override
    public void run() {
        while (true) {
//            方式一：用一个外面造好的对象来充当锁
//            synchronized(obj) {
//            方式二：用this来充当，代表当前对象来充当锁。由于implements Runnable的方式天然共享数据，所以没问题
        synchronized (this){
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();
        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();

    }
}
