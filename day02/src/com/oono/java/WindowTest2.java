package com.oono.java;

/**
 * @author oono
 * @date 2020 07 13
 */

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用Thread类继承的方式实现的
 * <p>
 * 使用同步代码块来解决继承Thread类的方式的线程安全问题
 *
 * @author oono
 * @date 2020 07 12
 */

class Window2 extends Thread {

    private static int ticket = 100;//如果不是声明为静态的，则每个对象w1，w2，w3都会new出来自己的ticket属性，各卖票100张。
    //因为ticket如果不是静态的，则随着obj的加载而加载，所以w1，w2，w3会各自在自己的结构中有一份ticket属性，一共三份ticket属性
    //而如果ticket是静态的，则随着class的加载而加载，那么w1，w2，w3都只会共用类里面的ticket属性，每次无论哪个obj对其--都会影响这唯一的一份ticket属性

    private static Object obj = new Object();
    //同理，这个也需要被static，否则这种继承Thread类的方式创建了三个子类的对象，从而每个window_obj都有一份属性锁，三个锁，达不到线程安全的目的
    //所以，这里又再次体现了通过实现Runnable接口的方式 --> 天然“共享数据”的一种处理方式

    @Override
    public void run() {

        while (true) {

//            synchronized (obj) {
//            synchronized (this){
//              而继承Thread类的方式来实现多线程，是不具有“天然共享数据”的优势的，这里this代表当前对象，而三窗口都有一个对象，所以线程不安全
//              但是可以用当前类来充当同步监视器，因为类也是对象（之后反射mapping会讲到)
            synchronized (Window2.class) {
                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}


public class WindowTest2 {

    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();

    }

}

