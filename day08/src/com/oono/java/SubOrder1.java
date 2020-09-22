package com.oono.java;

/**
 * @author oono
 * @date 2020 09 03
 */
public class SubOrder1<E> extends Order<E>{

    //这个子类就没有指明泛型，所以实例化的时候仍需要手动指明对象需要的泛型（ArrayList的源码就是，继承了一个带泛型的类，自己也没有指明泛型的类型，让用户自己选择，这种也有开发意义，多在源码层面）



}
