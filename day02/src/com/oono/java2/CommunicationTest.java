package com.oono.java2;

/**
 * 线程通信的例子：使用两个线程打印1-100。
 * 线程1，线程2，交替打印（即：通信）
 *
 * 涉及到的一组方法：
 * wait() --> 一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器/unlock
 * notify() --> 一旦执行此方法，就会唤醒被wait()的线程。如果有多个线程调用了wait()，则唤醒优先级高的一个（同级别的话，随机唤醒一个）
 * notifyAll() --> 唤醒所有wait的线程
 *
 * 注意点：
 * ① wait(),notify(),notifyAll()这一组方法，必须/只能使用在同步代码块或同步方法中，即被synchronized修饰的block或method中
 *      lock()+unlock()的方式来同步的代码都不行，因为内置语法不一样。（有其他的方式实现线程的通信，但不能用这一组方法）
 * ② wait(),notify(),notifyAll()这一组方法的调用者，必须是同步代码块或同步方法中的同步监视器
 *      ie同步监视器要和这三个方法调用者一样，默认调用者为this，则同步监视器为this；如果同步监视器是另外传的一个obj，则使用这三个方法时要加上obj.xxx
 *      否则出现异常IllegalMonitorStateException
 * ③ wait(),notify(),notifyAll()这一组方法是定义在Object类中的（而非Thread类中）
 *
 * 面试题：sleep()和wait()方法的异同
 * 相同点：都能让线程进入阻塞状态
 * 不同点：
 * ① 两个方法声明的位置不同：Thread类中定义的sleep()且为静态；Object类中定义的wait()
 * ② 调用的要求不一样：sleep()可以在任何需要的场景下调用；wait()必须由同步监视器synchronized monitor调用，所以只能在synchronized修饰的代码块或方法中调用
 * ③ 关于是否释放同步监视器：如果两个方法都使用在同步代码块/同步方法中，sleep()不会释放monitor/lock，而wait()会释放（否则wait()后其他线程根本进不来，锁还拿着线程阻塞的话，notify()定义在需要被同步的代码中的）
 *
 *
 *
 * @author oono
 * @date 2020 07 15
 */
class Number implements Runnable{
    private int num = 1;

    @Override
    public void run() {
        while(true){
            //如何加同步：看需要被同步的代码是什么就可以了 --> 操作共享数据的代码！
            synchronized (this) {

                notify();

                if (num <= 100) {

/*
                    //为了放大线程安全出现问题的概率
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
*/

                    System.out.println(Thread.currentThread().getName() + "：" + num);
                    num++;

                    //使得（当前执行的）调用如下wait()方法的线程进入阻塞状态
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;//while(true)结构如果不显式地使用break，则就算if进不去，也会一直重复进if这个操作，因为循环条件始终成立（所以要手动跳出循环）
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();

    }
}
