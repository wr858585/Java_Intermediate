package oono.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用实现Runnable接口的方式
 *
 * 仍然待解决：线程安全问题
 *
 * @author oono
 * @date 2020 07 12
 */

class Window1 implements Runnable {

    private int ticket = 100;//这里就不像继承Thread类的方式需要给ticket加static了

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "卖票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();
        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();

    }
}
