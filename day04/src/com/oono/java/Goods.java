package com.oono.java;

/**
 * 商品类
 * @author oono
 * @date 2020 07 22
 */
public class Goods implements Comparable{

    private String name;
    private double price;

    public Goods(){

    }
    public Goods(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //指明商品比较大小的方式：按照价格从低到高排序，若价格一样则按照商品名称字母排序
    public int compareTo(Object obj){
        if(obj instanceof Goods){
            Goods goods = (Goods)obj;
            //方式一：自己写
            if(this.price > goods.price){
                return 1;
            }else if(this.price < goods.price){
                return -1;
            }else{
//                return 0;
                return this.name.compareTo(goods.name);
            }
            //方式二：调用包装类的结构compare()
//          return Double.compare(this.price, goods.price);
        }
        throw new RuntimeException("传入的数据类型不一致");
    }


}

