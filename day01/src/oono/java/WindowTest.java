package oono.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用Thread类继承的方式实现的
 *
 * 目前尚存在线程安全的问题，待解决
 *
 * @author oono
 * @date 2020 07 12
 */

class Window extends Thread{

    private static int ticket = 100;//如果不是声明为静态的，则每个对象w1，w2，w3都会new出来自己的ticket属性，各卖票100张。
    //因为ticket如果不是静态的，则随着obj的加载而加载，所以w1，w2，w3会各自在自己的结构中有一份ticket属性，一共三份ticket属性
    //而如果ticket是静态的，则随着class的加载而加载，那么w1，w2，w3都只会共用类里面的ticket属性，每次无论哪个obj对其--都会影响这唯一的一份ticket属性

    @Override
    public void run() {

        while(true){

            if(ticket > 0){
                System.out.println(getName() + "：卖票，票号为：" + ticket);
                ticket--;
            }else{
                break;
            }

        }
    }
}


public class WindowTest {

    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();


    }


}
