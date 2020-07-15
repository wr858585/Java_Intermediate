package com.oono.exer;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  * 银行有一个账户：
 *  * 有两个储户分别向同一个账户存3000元，每次存1000元，存三次。
 *  * 每次存完打印账户余额
 *  * <p>
 *  * 分析：
 *  * ① 是否为多线程问题 --> 是，线程分别是：两个储户
 *  * ② 是否涉及线程安全问题 --> 是，因为有共享数据：账户余额
 *
 *
 * @author oono
 * @date 2020 07 15
 */
class Account1{

    private double balance;
    ReentrantLock lock = new ReentrantLock();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amt){
        lock.lock();
        balance += amt;
        System.out.println(Thread.currentThread().getName() + "存入" + amt + "，余额为：" + balance);
        lock.unlock();
    }

}


class Customer1 implements Runnable{

    Account1 acct = new Account1();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }

    }
}



public class AccountTest1 {
    public static void main(String[] args) {
        Customer1 customer1 = new Customer1();

        Thread t1 = new Thread(customer1);
        Thread t2 = new Thread(customer1);

        t1.setName("客户1");
        t2.setName("客户2");

        t1.start();
        t2.start();


    }
}
