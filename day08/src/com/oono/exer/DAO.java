package com.oono.exer;

import java.util.*;

/**
 * 要求：定义个泛型类DAO<T>，其中定义一个Map成员变量，Map的键为String类型，值为T类型
 *
 * 分别创建以下方法：
 * public void save(String id, T entity)：保存T类型的对象到Map成员变量中
 * public T get(String id)：从map中获取id对应的实例
 * public void update(String id, T entity)：替换map中的key为id的entity对象
 * public List<T> list()：返回map中存放的所有T对象
 * public List    ·<T> list()：返回map中存放的所有T对象
 * public void delete(String id)：删除指定id对象
 *
 * @author oono
 * @date 2020 09 07
 */
public class DAO<T> {

    private Map<String, T> map;

    //保存T类型的对象到Map成员变量中
    public void save(String id, T entity){
        map.put(id, entity);
    }

    //从map中获取id对应的对象
    public T get(String id){
        return map.get(id);
    }

    //替换map中key为id的内容，改为entity对象
    public void update(String id,T entity){
        //必须要先判断是否有这个key，否则不判断的话，没有key本不应该有任何替换，但是put会把entity添加上去
        if(map.containsKey(id)){
            map.put(id,entity);
        }
    }

    //返回map中存放的所有T对象
    public List<T> list(){
        Collection<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        list.addAll(values);
        return (List<T>) list;//??
    }

    //返回map中存放的T对象



}
