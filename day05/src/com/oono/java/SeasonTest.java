package com.oono.java;

/**
 * 一、 枚举类的使用
 * 1. 枚举类的理解：当类的对象是有限多个，且确定多个时，此类为枚举类
 * 2. 场景：当在开发中需要定义一组常量时，强烈建议使用enum
 * 3. 特殊的，如果enum只有一个对象，则可以直接用Singleton实现（enum可以理解为多例模式）
 *
 * 二、 如何定义枚举类
 * 1. 方式一：JDK5.0之前，自定义枚举类
 * 2. 方式二：JDK5出来后，可以使用enum关键字定义枚举类
 *
 * 三、Enum中的常用方法[注意：自定义枚举类继承的是Object，所以不能调；这些方法是在Enum类中，所以通过enum关键字修饰类的结构才能调用这些方法]
 * 1. toString()：返回当前枚举类对象常量的名称
 * 2. values()：返回枚举类型的对象[数组]，通过该数组可以很方便地遍历所有的枚举值；returns an array containing the constants of this enum type, in the order they're declared.
 * 3. valueOf(String name)：returns the enum constant of this type with the specified name. Throws IllegalArgumentException if name not found
 *
 * 四、使用enum关键字实现接口的情况
 * 1. 情况一：enum Season implements xxxInterface{...}：再重写接口中的抽象方法（没啥可说的）
 * 2. 情况二：不去统一重写抽象方法，每个obj去调（调出来的code都一样）；而在每个obj里面分别重写抽象方法，调出来的code都是customized的东西
 *
 * @author oono
 * @date 2020 07 24
 */
public class SeasonTest {

    public static void main(String[] args) {

        //toString()
        System.out.println(Season.SPRING);//Season{seasonName= 春天, seasonDesc= 春暖花开}
        System.out.println(Season.SPRING.toString());//Season{seasonName= 春天, seasonDesc= 春暖花开}
        //一样，因为println源码就是toString()，若Season类中没有重写，则invoke Object类中的toString()方法，变成打印底层类名和地址值


    }
}

//自定义枚举类
class Season{

    //0. 声明Season对象的属性，用private final来修饰
    private final String seasonName;
    private final String seasonDesc;

    //1. 私有化构造器，并（可变地）对对象属性赋值[如果显示初始化、构造器初始化，则只能赋值一个，无法可变]
    private Season(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //2. 提供当前枚举类的多个对象：用public static final一个个枚举出这些有限的确定的对象
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","寒冬腊月");

    //3.1 其他诉求：获取枚举类对象的属性
    public String getSeasonName(){
        return seasonName;
    }
    public String getSeasonDesc(){
        return seasonDesc;
    }

    //3.2 其他诉求：提供toString()方法
    public String toString(){
        return "Season{" + "seasonName= " + seasonName + ", seasonDesc= " + seasonDesc + "}";
    }

}