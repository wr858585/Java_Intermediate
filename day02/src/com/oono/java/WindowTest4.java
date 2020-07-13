package com.oono.java;

/**
 * 使用同步方法来继承Thread类中的线程安全问题
 *
 * @author oono
 * @date 2020 07 13
 */
class Window4 extends Thread {

    private static int ticket = 100;//如果不是声明为静态的，则每个对象w1，w2，w3都会new出来自己的ticket属性，各卖票100张。

    @Override
    public void run() {

        while (true) {

            show();
        }
    }

//    private synchronized void show(){   同步监视器：t1,t2,t3。这种解决方式是错误的
    private static synchronized void show() { //同步监视器：当前类Window4.class（唯一，安全）

        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
            ticket--;
        }

    }

}


public class WindowTest4 {

    public static void main(String[] args) {
        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();

    }

}


