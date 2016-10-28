package com.example;

/**
 * Created by Administrator on 2016/10/27.
 */
public class People {
    int id;
    String name;

    public People(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof People)) {
            return false;
        }
        People p = (People)obj;
        return p.id == id;
    }
}
