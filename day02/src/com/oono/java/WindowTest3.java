package com.oono.java;

/**
 * 使用同步方法来解决实现Runnable接口的线程安全问题
 *
 * 关于同步方法的总结：
 * 1. 同步方法也需要使用到同步监视器，只是不需要显示地声明
 * 2. 非静态的同步方法，同步监视器/锁 = this
 *    静态的同步方法，同步监视器/锁 = 当前类本身
 *
 *
 * @author oono
 * @date 2020 07 13
 */
class Window3 implements Runnable {

    private int ticket = 100;//这里就不像继承Thread类的方式需要给ticket加static了

    @Override
    public void run() {
        while (true) {
        show();
            }

    }

    private synchronized void show(){
        //synchronized同步方法源码其实是把this写成了同步监视器
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
            ticket--;
        }

    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();
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
