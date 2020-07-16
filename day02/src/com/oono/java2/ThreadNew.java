package com.oono.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现Callable --> JDK5.0新增
 *
 * 如何理解：实现Callable接口的方式创建多线程比实现Runnable接口更强大？
 * 1. call()可以有返回值，而run()只能void
 * 2. call()可以抛出异常，从而被外面的操作捕获，获取异常信息，而run()不能throw了，只能在run()里面进行try-catch
 * 3. Callable类是支持泛型的
 *
 *
 *
 *
 * @author oono
 * @date 2020 07 16
 */

//1. 创建一个实现Callable接口的实现类
class NumThread implements Callable{
    //2. 实现call方法，将此线程需要执行的操作声明在call()方法中，call方法有返回值Object，所以必须写return语句，没有的话return null
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
               sum += i;
            }
        }
        return sum;//自动装箱成Integer类，因为返回值是Object类的，基本数类不是类所以也不是其子类，转换成包装类就是子类了，因为任何类都是Object的子类
    }
}


public class ThreadNew {
    public static void main(String[] args) {
        //3. 创建Callable接口实现类的对象
        NumThread numThread = new NumThread();

        //4. 将上述Callable接口实现类的对象作为形参传入FutureTask类的构造器中，new一个FutureTask类的对象
        FutureTask futureTask = new FutureTask(numThread);

        //不管是哪种方式：extends Thread, implements Runnable, implements Callable,最终在测试类中都需要new Thread类的对象来调用Thread类中的start()方法[开启多线程的核心]
        //如：继承Thread类是new了Thread子类的对象，通过子类调用父类中的start()，开启线程并执行子类中重写的run()
        //如：实现Runnable接口是new了实现类的对象，再把实现类的对象作为形参传给Thread类，new出Thread类的对象，通过调用本类Thread中的start()，开启线程并执行run()
        //如：实现Callable接口是new了FutureTask类的对象，再把它作为形参传给Thread类，new出Thread类的对象，通过调用本类Thread中的start()，开启线程并执行call()
        //所以，本质上，实现接口来完成多线程，都是new的Thread类的对象，只不过把实现了各种接口的实现类obj作为形参传入Thread类的构造器中，让Thread类new的对象具有接口中的功能（方法run或call）

        //5. 将FutureTask类的对象，作为形参传入Thread类的构造器中，创建Thread类的对象，并用start()调用，开启多线程
        Thread t = new Thread(futureTask);
        t.start();

        try {
            //6. 获取Callable中call()方法的返回值（optional，如果对返回值不感兴趣，不用调用FutureTask类中的get()方法）
            //get()方法的返回值是FutureTask构造器参数Callable实现类重写的call()方法的返回值，这里是sum
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
