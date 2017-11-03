package com.aptitude.training.day1;

public class FirstGeneric<T> {

    private T value;

    public  FirstGeneric(){}
    public FirstGeneric(T x ){
        this.value = x;
    }

    public T getValue() {
        return value;
    }

    // generic method
    public static <X> void setSth(X sth)
    {
        System.out.println(sth);
    }

    // inner class can access private methods from uppper class
    // can be created inly withing uppper class
    public class Cos
    {

    }

    public static class CosStatic {
        // this inner class is static - cen be created outside of parent class as welll
    }
}
