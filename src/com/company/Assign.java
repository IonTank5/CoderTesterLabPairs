package com.company;

public class Assign {
    public static int number;
    public Assign(int x){
        number = x;
    }
    public int generation()
    {
        return (int) (Math.random()*number);
    }
}
