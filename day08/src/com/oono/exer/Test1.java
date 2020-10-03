package com.oono.exer;

/**
 * lambda表达式
 * 1. 使用前提 --> 函数式接口：接口中只有一个抽象方法（否则不知道执行哪个）
 * 2. 格式：拷贝小括号 写死右箭头 落地大括号
 * 3. abstract方法只能有一个，default、static方法可以有多个
 * @author oono
 * @date 2020 10 03
 */

interface Foo{
    void show();

    //为何java8引入了default方法 --> 让接口可以有不用重写就可以给实现类调用的方法！有些功能直接调！
    default int add(int x, int y){
        return x + y;
    }

}

public class Test1 {

    public static void main(String[] args) {

        Foo foo = () -> {
            System.out.println("hello show");
        };

        foo.show();
        foo.add(1,1);

    }

}
