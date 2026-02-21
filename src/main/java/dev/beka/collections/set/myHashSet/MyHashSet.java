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

    private void initializeBucket(int index){
        table[index] = new LinkedList<>();
    }

    private void expand(){
        LinkedList<E>[] temporaryTable = table;

        //new table
        length = length * 2;
        size = 0;
        table = new LinkedList[length * 2];


        //check buckets
        for(LinkedList<E> list : temporaryTable){

            if(list != null){
                for(E e : list){
                    add(e);
                }
            }
        }
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
    public boolean add(E e) {


        int index = findIndex(e);

        //initialize table[index] with buckets as needed
        if(table[index] == null){
            initializeBucket(index);
        }

        //check for existing elements
        for (E element : table[index]) {
            if(e == null && element == null){
                return false;
            }

            if (e != null && e.equals(element)) {
                return false;
            }
        }

        table[index].add(e);
        size++;

        if (size >= table.length * LOAD_FACTOR) {
            expand();
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;

        for(E e : c){
            if(add(e)){
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean remove(Object o) {

        int index = findIndex(o);

        if(table[index] == null ){
            return false;
        }

        Iterator<E> itr = table[index].iterator();

        while(itr.hasNext()) {
            E element = itr.next();

            //check for existing elements
            // case 1: both null
            boolean areBothNull = o == null && element == null;
            boolean isNonNullAndEqual = o != null && o.equals(element);
            if (areBothNull || isNonNullAndEqual) {
                itr.remove();
                size--;
                return true;
            }

            // case 2: o non-null and equal
//            if (o != null && o.equals(element)) {
//                itr.remove();
//                size--;
//                return true;
//            }
        }
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c) {

        boolean modified = false;

        for(Object o : c){
            if(remove(o)){
                modified = true;
            }
        }

        return modified;
    }


    @Override
    public boolean contains(Object o) {
        int index = findIndex(o);

        if(table[index] == null ){
            return false;
        }

        return table[index].contains(o);

//        for (E element : table[index]) {
//
//            //check for existing elements
//            // case 1: both null
//            if (o == null && element == null) {
//                return true;
//            }
//
//            // case 2: o non-null and equal
//            if (o != null && o.equals(element)) {
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c){
            if(!contains(o)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for(int i = 0; i < length; i++){
            table[i] = null; // drope the buckets
        }

        size = 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
       // iterator  hasNext  equalse next or remove
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
