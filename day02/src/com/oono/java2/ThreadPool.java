package com.oono.java2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方式之四：使用线程池
 *
 * 好处：
 * 1. 提高响应速度（节省了创建新线程的时间）
 * 2. 降低资源消耗（重复利用thread pool中的线程，避免重复建造）
 * 3. 便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：当线程没有任务时，最多保持多长时间后终止TERMINATED
 *
 *
 * 面试题：创建多线程有几种方式？ --> 4种（前两种基础，要掌握；最后一种实际开发中用得多，当然，还是没有直接用框架来造多线程的多）
 *
 * @author oono
 * @date 2020 07 16
 */

class NumberThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }
}
class NumberThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "：" + i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {

        //1. 提供指定线程数量的thread pool（类型为：ExecutorService接口）
        ExecutorService service = Executors.newFixedThreadPool(10);//ExecutorService是一个接口，这里是多态
        //由于ExecutorService是一个接口，接口中属性很少（因为是static的，所以不能被修改，实际意义不大）
        //所以属性一般都是定义在实现这个接口的实现类中。所以要把service向下转型，才能获取到子类ThreadPoolExecutor中的结构（主要指1.5中的方法）
        System.out.println(service.getClass());//用来查看ExecutorService的具体实现类是什么 --> 发现是一个叫ThreadPoolExecutor的实现类
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        //1.5（optional）设置线程池的属性，来thread management
        service1.setCorePoolSize(15);
        service1.setMaximumPoolSize(20);
//      service1.setKeepAliveTime();

        //2. 执行指定线程的操作，需要提供一个已实现了Runnable或Callable接口实现类的对象，对应execute()方法和submit()方法
        service.execute(new NumberThread());//适合适用于Runnable
        service.execute(new NumberThread1());//适合适用于Runnable
//      service.submit();//适合适用于Callable

        //3. 关闭连接池
        service.shutdown();//to terminate current thread otherwise running without being stopped

    }
}
