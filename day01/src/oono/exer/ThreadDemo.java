package oono.exer;

/**
 * 练习：创建两个分线程，一个遍历100以内的偶数，一个遍历100以内的基数
 *
 * @author oono
 * @date 2020 07 11
 */

class Thread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class Thread2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {

/*  除了这种规范的写法外，还可以用匿名类简便地去实现
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();
*/

        //创建Thread类的匿名子类的方式
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();//注意这里的结构，new出来的匿名子类直接.start()调用来启动多线程

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }.start();


    }
}

