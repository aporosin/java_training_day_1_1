package com.aptitude.training.day1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Stos<T> {
    private ArrayDeque<T> innerStos;

    public Stos (int size) {
        innerStos = new ArrayDeque<>(size);
    }

    public void push(T element)
    {
        innerStos.addLast(element);
        //innerStos.add(element);
    }

    public T pop()
    {
//        T result = isEmpty()? null:  innerStos.(innerStos.size() - 1);
//
//        if(result != null)
//            innerStos.remove(result);
//
//        return result;

        return innerStos.pollLast();
    }

    public boolean isEmpty()
    {
        return innerStos.size() == 0;
    }

    public void pushAll(Collection<? extends T> coll) // typ anonimowy
    {
        coll.forEach(c -> push(c));
    }

    // Horstman Java 8. Przewodnik programisty - dobre do egenrykow
    // alternative
    public void copyAll(Collection<? super T> elements)
    {
        innerStos.forEach(c -> elements.add(c));
    }
}
