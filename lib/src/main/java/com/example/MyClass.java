package com.example;

public class MyClass {
    public static void main(String[] args) {
        SystemNotify sys = new SystemNotify();
        String body = "[uid=201157]街灯下华丽的小傻瓜信[/uid] 给 [vid=1474366810306036]测试[/vid] 送了一个棒棒糖 X4";
        sys.setBody(body);
        sys.parse();

        System.out.println();System.out.println();System.out.println();

        SystemNotify sys1 = new SystemNotify();
        String body1 = "[uid=201157]街灯下华丽的小傻瓜信[/uid] 给 测试 送了一个棒棒糖 X4";
        sys1.setBody(body1);
        sys1.parse();
    }
}
