package oono.java;

/**
 * 创建多线程的方式二：实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现类去实现Runnable中的抽象方法：run()方法
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传递到Thread类的对应构造器中，去创建Thread类的对象
 * 5. 通过该Thread类的对象，来调用start()
 *
 * 比较创建线程的两种方式：
 * 开发当中，优先选择implement Runnable接口的方式。
 * 原因：
 * 1. 实现的方式没有单继承性的局限（让实现类还可以去继承其他的更有意义的父类）
 * 2. 实现的方式更适合去处理多个线程有共享数据的情况，不用static等去对属性进行调试，天然”共享数据“
 * 联系：
 * public class Thread implements Runnable
 * 相同点：两种方式都需要重写run()方法，将创建的要执行的线程的逻辑写在run()方法中
 *
 * @author oono
 * @date 2020 07 12
 */

// * 1. 创建一个实现了Runnable接口的类
class MThread implements Runnable{

// * 2. 实现类去实现Runnable中的抽象方法：run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }

        }
    }
}


public class ThreadTest1 {
    public static void main(String[] args) {

// * 3. 创建实现类的对象
        MThread obj1 = new MThread();

// * 4. 将此对象作为参数传递到Thread类的对应构造器中，去创建Thread类的对象
        Thread obj2 = new Thread(obj1);

// * 5. 通过该Thread类的对象，来调用start()：①启动线程 ②调用当前线程的run() --> 调用了Runnable类型的target的run()
        obj2.start();

        //在启动一个线程，遍历100以内的偶数
        Thread obj3 = new Thread(obj1);
        obj3.start();

    }
}

