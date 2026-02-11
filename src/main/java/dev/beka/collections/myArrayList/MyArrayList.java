package dev.beka.collections.myArrayList;
import java.util.*;

public class MyArrayList <T> implements List<T>{

    static final double CAPACITY_MULTIPLIER = 1.5;

    Object[] data;
    int capacity = 10;
    int size = 0;

    //------------
    //Constructors
    //------------
    public MyArrayList() {
        data = new Object[capacity];
    }

    public MyArrayList(int initialCapacity){
        data = new Object[initialCapacity];
    }

    public MyArrayList(Collection<? extends T> elements) {
        data = elements.toArray();
        size = data.length;
        capacity = data.length;
    }

    //--------------
    //helper methods
    //--------------
    private void capacityCheck() {
        if (size == capacity) {
            expand();
        }
    }

    private void capacityCheck(int elementsSize){
        int predicatedTotalSize = size + elementsSize;

        while(predicatedTotalSize > capacity){
            expand();
        }
    }

    private void expand() {
        capacity = (int) (capacity * CAPACITY_MULTIPLIER);
        data = Arrays.copyOf(data, capacity);
    }

    //dose not allows size == index
    private void indexCheckElement(int index){
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    //allows size == index
    private void indexCheckPosition(int index){
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
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
    public boolean add(T e) {
        capacityCheck();
        data[size] = e;
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        indexCheckPosition(index);
        capacityCheck();

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;

        size++;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        //c to array
        Object[] dataC = c.toArray();
        int cElementsSize = c.size();

        //we cant add empty collection
        if(cElementsSize == 0){return false;}

        // checks if Object[] data can fit all of this elements
        capacityCheck(cElementsSize);

        System.arraycopy(dataC, 0, data, size, cElementsSize);

        size = size + cElementsSize;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        indexCheckPosition(index);
        int cElementsSize = c.size();
        Object[] dataC = c.toArray();
        //we cant add empty collection
        if(cElementsSize == 0){return false;}

        // checks if Object[] data can fit all of this elements
        capacityCheck(cElementsSize);

        //we have enough space for all elements

        // first shift elements to the right  start from index
        System.arraycopy(data, index, data, index + cElementsSize, size - index);

        // add new elements into empty spaces start from index
        System.arraycopy(dataC, 0, data, index, cElementsSize);

        size = size + cElementsSize;
        return true;
    }

    @Override
    public T set(int index, T element) {
        indexCheckElement(index);

        Object oldElement = data[index];
        data[index] = element;

        return (T) oldElement;
    }

    @Override
    public T get(int index) {
        indexCheckElement(index);
        return (T)data[index];
    }

    @Override
    public T remove(int index) {
        indexCheckElement(index);

        T removedElement = (T) data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }

        size--;
        data[size] = null;

        return removedElement;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && data[i] == null) ||
                    (o != null && o.equals(data[i]))) {

                int numMoved = size - i - 1;

                if (numMoved > 0) {
                    System.arraycopy(data, i + 1, data, i, numMoved);
                }

                size--;
                data[size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null){
            throw new NullPointerException();
        }

        if(c.isEmpty()) {
            return false;
        }

        HashSet<?> myHash = new HashSet<>(c);
        int shift = 0;

        for(int i = 0; i < size; i++){

            //match found increase shift++
            if(myHash.contains(data[i])){
                shift++;

            }

            //match not found override elements at [i - shift] with current element [i]
            else{
                data[i - shift] = data[i];

            }
        }


        for (int i = size - shift; i < size; i++){
            data[i] = null;
        }

        if(shift == 0) return false;

        size = size - shift;
        return true;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++){
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return -1 != indexOf(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null){
            throw new NullPointerException();
        }

        if(c.isEmpty()) {
            return true;
        }

        for(Object x : c){
            if(!contains(x)){
                return false;
            }
        }

        return true;
    }

    //under development
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null){
            throw new NullPointerException();
        }

        if(c.isEmpty()) {
            return false;
        }

            HashSet<?> myHash = new HashSet<>(c);
            int shift = 0;

            for(int i = 0; i < size; i++){

                //match found increase shift++
                if(myHash.contains(data[i])){
                    shift++;

                }

                //match not found override elements at [i - shift] with current element [i]
                else{
                    data[i - shift] = data[i];

                }
            }


            for (int i = size - shift; i < size; i++){
                data[i] = null;
            }

            if(shift == 0){return false;}

            size = size - shift;
            return true;
        }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++ ){
            if(o.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastObject = -1;
        for(int i = 0; i < size; i++ ){
            if(o.equals(data[i])){
                lastObject = i;
            }
        }
        return lastObject;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        MyArrayList<T> insideList = new MyArrayList<T>();

        for(int i = fromIndex; i < toIndex ; i++){
            insideList.add( (T) data[i] );
        }

        return insideList;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
}
