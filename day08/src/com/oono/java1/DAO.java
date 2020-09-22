package com.oono.java1;

import java.util.List;

/**
 * @author oono
 * @date 2020 09 04
 *
 * DAO: Data Access Object --> 数据访问对象：数据库中的数据对象
 * 数据库中每一张表对应Java中的每一个类，增删改查的是某个类的一个对象。
 * 到底操作哪个类？ -->不确定，写成泛型DAO<T>
 */
public class DAO<T> {//表的通识操作的DAO

    //添加一条记录
    public void add(T t){}

    //删除一条记录
    public boolean remove(int index){
        return false;
    }

    //修改一条记录
    public void update(int index, T t){

    }

    //查询一条记录
    public T getIndex(int index){
        return null;
    }

    //查询多条记录
    public List<T> getForList(int index){
        return null;
    }

    //泛型方法
    //举例：获取表中一共有多少条记录？（E->Long型）获取最大的员工入职时间？（E->Date型）
    public <E> E getValue(){
        return null;
    }
}
