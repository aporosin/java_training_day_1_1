package com.aptitude.training.day1.collections;

import com.aptitude.training.day1.Employee;

import java.util.*;

public class CollectionsComparison {

    static int iterations = 100000;

    public static void main(String[] args) {
        Set<Employee> employees = new HashSet<>();
        List<Employee> employeeList = new ArrayList<>();
        Map<String, Employee> dict = new HashMap<>();

        List<Employee> toShuffle;

        toShuffle =  (List<Employee>) Add1000Employees(new ArrayList<Employee>());
        Add1000Employees(new Vector<Employee>());
        Add1000Employees(new LinkedList<>());
        Add1000Employees(new PriorityQueue<>());
        Add1000Employees(new HashSet<>());
        Add1000Employees(new TreeSet<>());
        Add1000Employees(new LinkedHashSet<>());
        Add1000Employees(new HashMap<>());
        Add1000Employees(new TreeMap<>());
        Add1000Employees(new LinkedHashMap<>());

        Collections.shuffle(toShuffle);
        System.out.println("After shuffle: " + toShuffle.get(0).getName());
    }

    static Collection<Employee> Add1000Employees(Collection<Employee> target)
    {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
        {
            target.add(new Employee("Ola" + i));

        }

        long end = System.currentTimeMillis();
        System.out.println("Adding took: " + (end-start) + " for " + target.getClass().getName());
        return target;
    }

    static Employee getHalf(Collection<Employee> coll)
    {
        return null;
    }

    static void Add1000Employees(Map<Employee, String> target)
    {
        long start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++)
        {
            target.put(new Employee("Ola" + i), "Ola" + i);

        }

        long end = System.currentTimeMillis();
        System.out.println("Adding took: " + (end-start) + " for " + target.getClass().getName());
    }
}
