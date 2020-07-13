package com.oono.java1;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 *
 * @author oono
 * @date 2020 07 13
 */
public class BankTest {


    public static void main(String[] args) {
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        System.out.println(bank1 == bank2);//true,表示为同一个对象，所以为单例
    }

}

class Bank {

    private Bank() {

    }

    private static Bank bank = null;

    public static Bank getInstance() {
        //方式一：效率稍差（因为就算造好了bank，每个线程还是要同步进去，等，变成单线程。
        //而高效率的且线程安全的做法应该是：在没有对象的时候，用同步监视器让一个线程进去造对象，其他线程在外面等（以确保线程安全）。obj造好了之后，所有线程应该并行，拿着造好的obj就走
/*
        synchronized (Bank.class) {
            if (bank == null) {
            bank = new Bank();
            }
            return bank;
        }
    }
*/

        //方式二：效率更高（因为一旦new出来这个唯一的对象bank后，之后的线程if都不用进去了，更不用等同步，因为等同步的时候只能单线程，所以不等快很多）
        if(bank == null){
            synchronized (Bank.class){
                bank = new Bank();
            }
        }
        return bank;
    }
}