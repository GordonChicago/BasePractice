package com.example;

import java.util.ArrayList;

public class MyClass {
    public static void main(String[] args) {
        testEquals();
    }

    public static void testEquals(){
        ArrayList<People> peoples = new ArrayList<People>();
        for(int i=0;i<4;i++){
            People p = new People(i,"name"+i);
            peoples.add(p);
        }

        System.out.println(peoples.contains(new People(5,"11")));
    }

    public void testParse() {
        SystemNotify sys = new SystemNotify();
        String body = "[uid=201157]街灯下华丽的小傻瓜信[/uid] 给 [vid=1474366810306036]测试[/vid] 送了一个棒棒糖 X4";
        sys.setBody(body);
        sys.parse();

        System.out.println();
        System.out.println();
        System.out.println();

        SystemNotify sys1 = new SystemNotify();
        String body1 = "[uid=201157]街灯下华丽的小傻瓜信[/uid] 给 测试 送了一个棒棒糖 X4";
        sys1.setBody(body1);
        sys1.parse();
    }

}
