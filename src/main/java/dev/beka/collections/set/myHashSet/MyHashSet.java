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

    public MyHashSet(){
        table = new LinkedList[defaultLength];
        length = defaultLength;
        size = 0;
    }

    private int findIndex(Object o){
        //not safe with negative hashCode(); //kadyr
        //int hashCode = (e == null)? 0 : e.hashCode();
        //int index = hashCode % length;

        // safe with negative hashCode //chatGPT
        int hash = (o == null) ? 0 : o.hashCode();
        int index = (hash & 0x7fffffff) % length;

        return index;
    }

    private void initializeBuckets(int index){
        table[index] = new LinkedList<>();
    }

    // here should be a method that expends array based on LoadFactor
    //expand();

    @Override
    public boolean add(E e) {

        if (size >= table.length * LOAD_FACTOR) {
            //expand();
        }

        int index = findIndex(e);


        //initialize table[index] as needed
        if(table[index] == null){
            initializeBuckets(index);
        }

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

    @Override
    public boolean remove(Object o) {

        int index = findIndex(o);
        LinkedList<E> bucket = table[index];

        if (bucket == null) {
            return false;
        }

        Iterator<E> it = bucket.iterator();

        while (it.hasNext()) {
            E element = it.next();

            // case 1: both null
            if (o == null && element == null) {
                it.remove();
                size--;
                return true;
            }

            // case 2: both non-null and equal
            if (o != null && o.equals(element)) {
                    it.remove();
                    size--;
                    return true;
                }
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
