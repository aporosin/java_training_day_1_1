package com.aptitude.training.day1;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private String name;
    private String position;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    private int salary;

    public Employee(){}
    public Employee(String n){
        name = n;
    }

    public Employee(String name, String pos)
    {
        this(name);
        this.position = pos;
    }

    public Employee(String name, String pos, int salary)
    {
        this(name, pos);
        this.salary = salary;
    }

    public boolean equals(Employee o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getPosition() {

        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
