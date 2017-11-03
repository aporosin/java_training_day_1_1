package com.aptitude.training.day1;

import java.sql.Statement;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter your name:");
        Scanner sc = new Scanner(System.in);

        //String name = sc.nextLine();
        //System.out.println("Hello " + name);
        //System.out.printf("Hello %s", name);

        Arrays.asList(1,4).forEach(e -> System.out.println(e));

        FirstGeneric<Integer> fg = new FirstGeneric<>(23);

        // Call static generic
        FirstGeneric.<String>setSth("sth");

        // possible to create inner static class in here
        FirstGeneric.CosStatic cosSttaic = new FirstGeneric.CosStatic();

        ///=============

        Stos<Integer> stosik = new Stos<>(10);
        stosik.push(1);
        stosik.push(2);
        stosik.push(3);
        Collection<Integer> coll = Arrays.asList(4,5,6);

        Integer val = stosik.pop();

        while(val != null) {
            System.out.println(val);
            val = stosik.pop();
        }

        System.out.println("On stack: " + val);

        List<CEO> ceos = new ArrayList<>();
        ceos.add( new CEO("w"));
        ceos.add( new CEO("d"));

        Stos<Employee> employeeStos = new Stos<>(3);
        employeeStos.pushAll(ceos); // => nie zadziala bo Manager jest typem specjalizowanym od Employee

        // fix: in pushAll use "? extends T" in method definition to allow all types inheriting fromm T

        // Super in copyAll allows to copy all
        Stos<CEO> ceoStos = new Stos<>(3);
        Collection<Employee> employees = new ArrayList<>();
        ceoStos.copyAll(employees);

        // Compare ints => cached up to 127
        Integer a = 127;
        Integer b = 127;
        if(a == b)
            System.out.println("Are equal ");
        else
            System.out.println("NOT equal");


    }
}
