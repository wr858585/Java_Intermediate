package com.oono.java;

/**
 * 使用enum关键字来定义枚举类
 * 1. 定义的枚举类默认继承于java.lang.Enum，而非Object。当中重写了toString()方法，会打印当前对象的名字
 *
 *
 * @author oono
 * @date 2020 07 24
 */
public class SeasonTest1 {

    public static void main(String[] args) {

        //1. toString()
        System.out.println(Season1.SUMMER);//SUMMER
        System.out.println(Season1.class.getSuperclass());//class java.lang.Enum

        System.out.println("**************************");

        //2. values()
        Season1[] values = Season1.values();//values() returns an array containing the constants of this enum type
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        /*
        SPRING
        SUMMER
        AUTUMN
        WINTER
         */

        System.out.println("**************************");

        //同理，Thread类中也是用枚举类来定义线程状态的
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values1.length; i++) {
            System.out.println(values1[i]);
        }

        /*
        NEW
        RUNNABLE
        BLOCKED
        WAITING
        TIMED_WAITING
        TERMINATED
         */

        //3. valueOf(String str)
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);//WINTER



    }
}

//自定义枚举类
enum Season1{
    //1. 提供当前枚举类的对象，省略所有重复的结构（如private static final, = new Season）。多个对象用逗号隔开，最后用；（和声明数类相同的多个变量一样）
    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","寒冬腊月");

    //2. 声明Season中的属性：用private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //3. 私有化构造器，并给用构造器给对象进行初始化
    private Season1(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //4. 其他诉求：提供getter
    public String getSeasonName(){
        return seasonName;
    }
    public String getSeasonDesc(){
        return seasonDesc;
    }

}
