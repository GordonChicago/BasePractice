package com.example;

/**
 * Created by Administrator on 2016/11/21.
 */

public enum  EnumTest implements OtherInterface{
    SING,SDf,SDF;
    @Override
    public void method() {
    }
}

interface OtherInterface{
    public void method();
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }
    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
enum EnumTest1{
}
interface Food{
}
