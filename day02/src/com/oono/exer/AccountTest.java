package com.oono.exer;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行有一个账户：
 * 有两个储户分别向同一个账户存3000元，每次存1000元，存三次。
 * 每次存完打印账户余额
 * <p>
 * 分析：
 * ① 是否为多线程问题 --> 是，线程分别是：两个储户
 * ② 是否涉及线程安全问题 --> 是，因为有共享数据：账户余额
 *
 * @author oono
 * @date 2020 07 14
 */
class Account {

    private double balance;
    ReentrantLock lock = new ReentrantLock();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double deposit) {
        lock.lock();
        balance += deposit;
        System.out.println(Thread.currentThread().getName() + "存钱" + deposit + "元，余额为：" + balance);
        lock.unlock();
    }
}

class Customer extends Thread {

    private static Account acct = new Account();

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }

}

public class AccountTest {

    public static void main(String[] args) {
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.setName("客户1");
        c2.setName("客户2");

        c1.start();
        c2.start();


    }
}
