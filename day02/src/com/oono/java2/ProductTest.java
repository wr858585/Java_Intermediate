package com.oono.java2;

/**
 * 线程通信的应用（经典的OS和多线程例题）：生产者 & 消费者问题
 * <p>
 * 生产者Producer将产品交给店员Clerk，而消费者Customer从店员处取走产品。
 * 店员一次只能持有固定数量的产品（比如20个，因为货架有限），所以：
 * ① 如果生产者试图生产更多产品，店员会叫生产者停一下，如果店中有空位了再通知生产者继续生产
 * ② 如果店中没有产品了，店员会叫消费者等一下，如果店中有产品了再叫消费者来取走产品
 * <p>
 * 分析：
 * 1. 是否为多线程问题？ --> 是（生产者线程 & 消费者线程）
 * 2. 是否涉及线程安全？ --> 是（有共享数据：产品数量）
 * 3. 是否涉及线程通信？ --> 是（存在两个线程的交互，wait和notify）
 *
 * @author oono
 * @date 2020 07 15
 */

class Clerk {

    //店员拥有产品
    private static int product;

    public int getProduct() {
        return product;
    }

    public synchronized void addProduct() {

//        synchronized (this) {
            if (product < 20) {
                product++;
                System.out.println(Thread.currentThread().getName() + " adds 1 product. " + product + " remaining.");
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }


    public synchronized void takeProduct() {

//        synchronized (this) {
            if (product > 0) {
                product--;
                System.out.println(Thread.currentThread().getName() + " takes 1 product. " + product + " remaining.");
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }
}


class Producer implements Runnable {
    Clerk clerk = new Clerk();

    @Override
    public void run() {
        System.out.println("生产者开始生产产品");
        while (true) {
            clerk.addProduct();
        }
    }
}

class Customer implements Runnable {
    Clerk clerk = new Clerk();

    @Override
    public void run() {
        System.out.println("消费者开始消费产品");
        while (true) {
            clerk.takeProduct();
        }
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Producer producer = new Producer();
        Customer customer = new Customer();

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(customer);

        t1.setName("Producer");
        t2.setName("Customer");

        t1.start();
        t2.start();


    }
}
