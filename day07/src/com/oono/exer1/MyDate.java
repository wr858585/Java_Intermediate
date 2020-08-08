package com.oono.exer1;

/**
 * MyDate类包含：
 * private成员变量year, month, day;提供getter&setter
 *
 *
 * @author oono
 * @date 2020 08 08
 */
public class MyDate {

    private int year, month, day;

    public MyDate() {
    }
    public MyDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String toString(){
        return "MyDate{year=" + year + ", month=" + month + ", day=" + day + "}";
    }



}
