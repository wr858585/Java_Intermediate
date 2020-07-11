package com.oono.java;

/**
 * 测试Thread类中的常用方法:
 * 1. start()：启动当前线程；调用当前线程的run()
 * 2. run()：通常需要重写Thread类中的此方法，将创建的线程要执行的操作写在此方法中
 * 3. currentThread()：[静态方法]返回当前代码的线程
 * 4. getName()：获取当前线程的名字
 * 5. setName()：设置当前线程的名字
 * 6. yield()：释放当前CPU的执行权
 * 7. join()：在线程A中调用B的join()方法，此时线程A就进入阻塞状态；知道线程B完全执行完以后，线程A才结束阻塞状态
 * 8. stop()：[deprecated,反对使用, inherently unsafe]强制结束当前线程
 * 9. sleep(long millis)：让当前线程睡眠/阻塞指定的millis毫秒数的时间，1000毫秒=1秒。在指定milli时间内，线程阻塞
 * 10. isAlive()：判断线程是否还活着，从start()后到died之间为true，否则返回false
 * <p>
 * 线程的优先级：
 * 1. java定义的优先级有三个常量：
 * MAX_PRIORITY:10
 * MIN_PRIORITY:1
 * NORM_PRIORITY:5 --> 默认优先级
 * 2. 如何获取和设置当前线程的优先级：
 * getPriority()：获取当前线程的优先级
 * setPriority()：设置当前线程的优先级
 * 说明：高优先级的线程会抢占低优先级线程的CPU的执行权。但这只是从概率上来讲的，高优先级线程会较高概率被执行，并非只有当高优先级线程执行完以后低优先级线程才执行
 *
 * @author oono
 * @date 2020 07 11
 */

class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {

                //不能throws了，因为父类Thread中run()方法并没有throws抛异常，而OOP规定子类抛的异常<=父类抛的异常，所以只能try-catch
/*
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
*/

                System.out.println(getName() + ":" + getPriority() + ":" + i);
            }

/*  用于测试yield()方法
            if (i % 20 == 0) {
                yield();
            }
*/

        }
    }

    public HelloThread(String name) {
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {

        HelloThread h1 = new HelloThread("Thread:1");

//        h1.setName("线程1");

        //设置分线程的优先级
        h1.setPriority(7);

        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");

        //给主线程设置优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                //思考：为什么这里调用getName(),setName()加前缀，而HelloThread类中可以直接调用？
                //因为同一个类中的结构没有任何指向不明，调用同一类里的结构无需指明。而这里是ThreadMethodTest，没有这种结构，所以通过指明Thread.currentThread来返回Thread类，指明是Thread类中的结构
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + "i" + i);
            }
/*
            if (i == 20) {
                try {
                    h1.join();
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
*/
        }

        System.out.println(h1.isAlive());


    }
}
