package dev.beka.collections.set.myHashSet;

import java.util.*;

//for now we work with fixed size aray 10, no expansion
public class MyHashSet<E> implements Set<E> {

    private LinkedList<E>[] table;
    private final int defaultLength = 10;
    private final double LOAD_FACTOR = 0.75;

    //represents actual elements in array
    private int size;

    //represents the length of aray
    private int length;

    //real HashSet dont initialize buckets right away
    //it initialize them as needed to save memory
    public MyHashSet(){
        table = new LinkedList[defaultLength];
        length = defaultLength;
        size = 0;

        //buckets
        for(int i = 0; i< length; i++){
            table[i] = new LinkedList<>();
        }
    }

    // here should be a method that expends array based on LoadFactor
    //expand();

    @Override
    public boolean add(E e) {

        if (size >= table.length * LOAD_FACTOR) {
            //expand();
        }

        int hashCode = 0;

        //null always be at index 0;
        // null != null -> hashCode = 0;
        // e != null -> e.hashCode();
        if (e != null){
            hashCode = e.hashCode();
        }

        int index = hashCode % length;

        //check for existing elements
        if (table[index].contains(e)) {
            return false;
        }

        table[index].add(e);

        size++;
        return true;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    // check for null buckets first
    @Override
    public boolean remove(Object o) {

        int hashCode = 0;

        //null always be at index 0;
        // null != null -> hashCode = 0;
        // e != null -> e.hashCode();
        if (o != null){
            hashCode = o.hashCode();
        }

        int index = hashCode % length;

        //check for existing elements
        if (table[index].remove(o)) {
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    // check for null buckets first
    @Override
    public boolean contains(Object o) {
        int hashCode = 0;

        //null always be at index 0;
        // null != null -> hashCode = 0;
        // e != null -> e.hashCode();
        if (o != null){
            hashCode = o.hashCode();
        }

        int index = hashCode % length;

        //check for existing elements
        if (table[index].contains(o)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    //removes the buckets
    //i initialize bucets in constractor so
    // if after clear() we call add(e) it will throw NullPointerEcxeption
    @Override
    public void clear() {
        for(int i = 0; i < length; i++){
            table[i] = null; // drope the buckets
        }
        size = 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
}
