package com.oono.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * 1. 理解Annotation：
 * ① JDK5.0新增功能（以前用配置文件做）
 * ② Annotation其实就是代码里的特殊标记，这些标记可以在编译、类加载、运行时被读取，并执行相应的处理。通过使用Annotation，程序员可以在不改变原有逻辑的基础上，在源文件中嵌入信息
 * ③ 在JavaSE中，注解的作用比较简单，例如标记过时的功能，忽略警告等
 *    在JavaEE/Android开发中，注解占据了更重要的地位，例如：
 *    > 用于配置应用程序的任何切面
 *    > 代替JavaEE中所遗留的冗余代码和XML配置繁琐等问题
 *
 * 2. Annotation示例：
 * ① 示例一：生成相关文档的注解
 * ② 示例二：在编译时进行格式检查（JDK内置的三个基本注解）
 * @Override:限定重写父类方法（该注解只能用于方法）
 * @Deprecated:用于表示所修饰的元素（类/方法等）已过时，通常是因为所修饰的结构危险或有更好的其他解决办法导致
 * @SuppressWarnings:抑制编译器警告
 * ③ 示例三：跟踪代码依赖性，实现替代配置文件功能
 *
 * 3. 如何自定义注解：
 * ① 注解声明为：@interface（不像public class/enum/interface Xxx{}，而是public @interface Xxx{}
 * ② 内部定义成员，通常使用value去表示
 * ③ 可以指定成员的默认值 --> 使用default关键字修饰
 * ④ 如果自定义注解没有成员，叫标识；如果有自定义成员，叫元数据注解MetaData annotation
 *
 * 如果注解有成员，在使用注解时，要指定成员的值，否则报错（可以默认用default，没有default定义结构则必须在使用时赋值）
 *
 * 自定义注解，必须配上注解的信息处理流程（使用反射）才有意义
 *
 * 4. JDK提供的4种元注解：对现有注解进行解释说明的注解
 * ① Retention：指定所修饰的Annotation的生命周期
 *      > SOURCE：注解只用源文件中保留，javac命令对源文件进行编译，编译器丢弃该注解，且生成的字节码文件xxx.class不包含该注解
 *      > CLASS：注解在编译时保留，在运行时丢弃 --> 不会加载到内存中给CPU处理[这是默认选项]
 *      > RUNTIME：只有声明为RUNTIME的生命周期的注解，才能通过反射获取，因为该注解在内存中
 * ② Target：指定所修饰的Annotation能够用来修饰的结构/元素
 * ***************下面两个出现频率较低********************
 * ③ Documented：表示所修饰的Annotation在被javadoc解析时，会保留下来
 * ④ Inherited：
 *
 * 5. 通过反射来获取注解信息：（到反射时会细讲 ）
 *
 * 6. JDK8.0中注解的新特性：可重复注解、类型注解
 * 6.1 可重复注解：
 * ① 在MyAnnotation上声明@Repeatable，成员值为MyAnnotation.class
 * ② MyAnnotation的@Target和@Retention等元注解必须和MyAnnotations相同，否则报错
 * 6.2 类型注解：
 * ① Element.TYPE_PARAMETER：表示该注解能写在类型变量的声明语句中（如：泛型声明）
 * ② Element.TYPE_USE：表示该注解能写在使用类型的任何语言中
 *
 * @author oono
 * @date 2020 07 26
 */
public class AnnotationTest {

    @Test
    public void testGetAnnotation() {
        Class<Student> clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
    }

}

//@MyAnnotation(value = "world")
@MyAnnotation("123")
@MyAnnotation("abc")

class Person{

    private String name;
    private int age;

    public Person(){

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk(){
        System.out.println("人走路");
    }

    public void eat(){
        System.out.println("人吃饭");
    }
}

interface info{
    void show();
}

class Student extends Person implements info{

    @Override
    public void walk() {
        System.out.println("学生走路");
    }

    @Override
    public void show() {

    }

}

class Generic<@MyAnnotation T>{

    public void show() throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int)10L;



    }

}