package com.oono.java1;

import static java.lang.Thread.sleep;

/**
 * 演示线程的死锁问题
 *
 * 1. 死锁的理解：
 * 不同的线程分别占用对方需要的同步资源不放弃，都在等待对方先放弃自己需要同步的资源，就形成了死锁
 *
 * 2. 说明：
 * ① 我们些程序时，要避免写死锁
 * ② 出现死锁后，不会出现异常，不会有提示，但是运行时所有线程都会处于阻塞状态，无法执行相关代码（当然，有程度高低。如以下例子没有sleep能运行，有sleep几乎一直死锁）
 *
 * 3. 解决方法：
 * ① 专门的algorithm
 * ② 尽量少用同步的资源
 * ③ 尽量避免同步嵌套
 *
 * @author oono
 * @date 2020 07 14
 */
public class ThreadTest {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        new Thread() {
            @Override
            public void run() {

                synchronized (s1) {

                    s1.append("a");
                    s2.append("1");

                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (s2) {

                    s1.append("c");
                    s2.append("3");

                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.getStackTrace();
                    }
                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }
            }
        }).start();


    }


}
