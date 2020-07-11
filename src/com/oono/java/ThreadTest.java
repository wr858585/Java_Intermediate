package com.oono.java;

/**
 * 多线程的创建，方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类中run()方法 --> 将此线程中执行的操作声明在run()方法中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象来调用Thread类中的start()方法
 * <p>
 * 例子：遍历100以内所有的偶数
 *
 * @author oono
 * @date 2020 07 10
 */

//1. 创建一个继承于Thread类的子类
class MyThread extends Thread {

//2. 重写run()方法


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
// * 3. 创建Thread类的子类的对象
        MyThread t1 = new MyThread();
// * 4. 通过此对象来调用Thread类中的start()方法：①启动当前线程 ②调用当前线程的run()方法

        //注意点1：我们不能直接调用obj.run()的方式来启动线程（只是调了一下run方法而已），obj.start()才是真正让线程启动了
        t1.start();

        //注意点2：再启动一个线程，来遍历100以内的偶数。不可以让已经start的线程去执行，否则会报异常IllegalThreadStatusException
        //t1.start();是错误的，此时该线程已经打开，不能再start()一遍
        MyThread t2 = new MyThread();
        t2.start();
        //也就是说，我们需要重新创建一个线程的对象t2，来新建一个线程完成相关操作

        //用于证明：主线程main和线程MyThread中的任务是同时并发进行。（如下的操作仍然是在主线程main线程中执行的）
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

        }
    }
}

