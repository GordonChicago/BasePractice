package com.example.rxjava;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/1.
 */

public class Student {
    private String name;
    private int age;
    private ArrayList<String> course = new ArrayList<>();
    public Student(String name,int age){
        this.name=name;
        this.age=age;
        course.add(this.name+"course0");
        course.add(this.name+"course1");
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getCourse(){
        return course;
    }
}
