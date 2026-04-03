package dev.beka.AllOneDataStructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {

    private class Node {
        int count;
        Set<String> keys = new HashSet<>();
        Node previous;
        Node next;

        public Node(int count) {
            this.count = count;
        }
    }


    Map<String, Node> map;

    private Node head;
    private Node tail;

    public AllOne() {
        map = new HashMap<>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.previous = head;
    }


    //inserts key into myImplementedList

    //if key exists increase amount by +1
    //  retive and add  hashSet and hashMap
    // check if elemenet exist

    //if new key add with amount 1
    public void inc(String key) {
        if (!map.containsKey(key)) {
            Node node = (head.next != tail && head.next.count == 1) ? head.next : new Node(1);
            if (node.previous == null) {
                insertAfter(head, node);
            }

            node.keys.add(key);

            map.put(key, node);
        } else {
            Node currentNode = map.get(key);
            Node nextNode = currentNode.next != tail && currentNode.next.count == currentNode.count + 1
                    ? currentNode.next
                    : new Node(currentNode.count + 1);

            if (nextNode.previous == null) {
                insertAfter(currentNode, nextNode);
            }

            nextNode.keys.add(key);
            map.put(key, nextNode);

            currentNode.keys.remove(key);

            if (currentNode.keys.isEmpty()) {
                remove(currentNode);
            }
        }
    }


    // if amount is 0 then delete
    public void dec(String key) {
        Node node = map.get(key);

        if(node.count == 1){
            node.keys.remove(key);
            map.remove(key);
        }

        else{
            Node previousNode = (node.previous != head && node.previous.count == node.count-1)
                    ? node.previous : new Node(node.count-1);

            if(previousNode.next == null){
                insertAfter(node.previous, previousNode);
            }

            previousNode.keys.add(key);
            map.put(key, previousNode);

            node.keys.remove(key);
        }

        if(node.keys.isEmpty())remove(node);
    }


    public String getMaxKey() {
        if (tail.previous == head) {
            return "";
        }

        return tail.previous.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }

        return head.next.keys.iterator().next();
    }

    private void insertAfter(Node prev, Node newNode) {
        newNode.next = prev.next;
        newNode.previous = prev;
        prev.next.previous = newNode;
        prev.next = newNode;
    }

    private void remove(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.next = null;
        node.previous = null;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key); O(n+m)
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
