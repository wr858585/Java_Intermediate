package com.oono.java2;

/**
 * 使用Thread类继承的方式做生产者-消费者线程通信问题
 *
 * @author oono
 * @date 2020 07 16
 */

class Clerk2{
    private int product;
    public int getProduct() {
        return product;
    }
    public synchronized void addProduct(){
        if (product < 20) {
            product++;
            System.out.println(Thread.currentThread().getName() + " adds a product." + product + " remaining.");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }public synchronized void takeProduct(){
        if (product > 0) {
            product--;
            System.out.println(Thread.currentThread().getName() + " takes a product." + product + " remaining.");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer2 extends Thread{
    Clerk2 clerkA = new Clerk2();
    Producer2(Clerk2 clerk2){
        clerkA = clerk2;
    }
    @Override
    public void run() {
        while(true) {
            clerkA.addProduct();
        }
    }
}

class Customer2 extends Thread{
    Clerk2 clerkB = new Clerk2();
    Customer2(Clerk2 clerk2){
        clerkB = clerk2;
    }
    @Override
    public void run() {
        while (true){
            clerkB.takeProduct();
        }
    }
}

public class ProductTest2 {
    public static void main(String[] args) {
        Clerk2 clerk = new Clerk2();
        Producer2 producer2 = new Producer2(clerk);
        Customer2 customer2 = new Customer2(clerk);
        producer2.setName("Producer");
        customer2.setName("Customer");
        producer2.start();
        customer2.start();
    }
}
