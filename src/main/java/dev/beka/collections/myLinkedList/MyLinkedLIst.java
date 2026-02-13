package dev.beka.collections.myLinkedList;
import java.util.*;

public class MyLinkedLIst <T> implements List<T>, Deque<T>{

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        Node(T data) {
            this.data = data;
        }
    }


    //--------------
    //helper methods
    //--------------
    private void indexCheckElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    //index == size add(index)
    private void indexCheckPosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> getNode(int index) {
        indexCheckElement(index);

        //choose where to start
        if (index < size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node<T> current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
            return current;
        }
    }


    //--------------------
    //Implementing methods
    //--------------------
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean  isEmpty() {
        return size == 0;
    }

    @Override
    public boolean offerFirst(T element) {
        Node<T> newNode = new Node<>(element);

        if (size == 0) {
            // {<- preve = null;  node ;next = null}
            head = newNode;
            tail = newNode;
        }

        else {
            // {<- previous = null;  node ;next = head ->}
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }

        size++;
        return true;
    }

    @Override
    public void addFirst(T element) {
        offerFirst(element);
    }

    @Override
    public boolean offerLast(T element) {
        Node<T> newNode = new Node<>(element);

        if (size == 0) {
        // {<- preve = null;  node ;next = null}
            head = newNode;
            tail = newNode;
        }

        else {
        // {<- previous = tail;  node ;next = null ->}
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
        return true;
    }

    @Override
    public void addLast(T element) {
        offerLast(element);
    }

    @Override
    public T pollFirst() {
        if(isEmpty()){
            return null;
        }

        T data = head.data;

        //one element
        if(size == 1){
            head = null;
            tail = null;
            return data;
        }

        Node<T> oldHead = head;

        head = oldHead.next;
        head.previous = null;

        oldHead.next= null;

        size--;
        return data;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        return pollFirst();
    }

    @Override
    public T pollLast() {
        if(isEmpty()){
            return null;
        }

        T data = tail.data;

        //one element
        if(size == 1){
            head = null;
            tail = null;
            return data;
        }

        Node<T> oldTail = tail;

        tail = oldTail.previous;
        tail.next = null;

        oldTail.previous = null;

        size--;
        return data;
    }

    @Override
    public T removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return pollLast();
    }

    @Override
    public T peekFirst() {
        if(isEmpty()){
            return null;
        }

        return head.data;
    }

    @Override
    public T getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return peekFirst();
    }

    @Override
    public T peekLast() {
        if(isEmpty()){
            return null;
        }

        return tail.data;
    }

    @Override
    public T getLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return peekLast();
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
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

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        //make sure list is not empty
        if(isEmpty() || o == null){
            return false;
        }

        Node<T> current = head;

        for(int i = 0; i < size; i++){
            //equals() ==

            if(current.data != null && current.data.equals(o)) {

                //one element
                if (size == 1) {
                    head = null;
                    tail = null;

                    size--;
                    return true;
                }

                //remove head
                if (current == head) {
                    head = head.next;
                    head.previous = null;
                    size--;
                    return true;
                }

                //remove tail
                if (current == tail) {
                    tail = tail.previous;
                    tail.next = null;

                    size--;
                    return data;
                }

                //remove middle
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }

            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    public void add(int index, T element) {
        indexCheckPosition(index);

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }
        //at this index==size
        Node<T> nodeAtIndex = getNode(index); // fixme exception
        Node<T> newNode = new Node<>(element);

        //{prev: node :next}
        newNode.previous = nodeAtIndex.previous;
        newNode.next = nodeAtIndex;

        nodeAtIndex.previous.next = newNode;
        nodeAtIndex.previous = newNode;

        size++;
    }

    public T remove(int index) {
        indexCheckElement(index);

        Node<T> node = getNode(index);
        T data = node.data;

        //one element
        if (size == 1) {
            head = null;
            tail = null;

            node.next = null;
            node.previous=null;

            size--;
            return data;
        }

        //remove head
        if (node == head) {
            head = node.next;
            head.previous = null;

            node.next = null; // redundant but ok
            size--;
            return data;
        }

        //remove tail
        if (node == tail) {
            tail = node.previous;
            tail.next = null;

            node.previous = null; // redundant but ok
            size--;
            return data;
        }

        //remove middle
        node.previous.next = node.next;
        node.next.previous = node.previous;

        node.next = null; // redundant but ok
        node.previous = null; // redundant but ok

        size--;
        return data;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }

}
