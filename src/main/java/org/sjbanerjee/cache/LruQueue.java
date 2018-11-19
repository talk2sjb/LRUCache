package org.sjbanerjee.cache;


/**
 * This class is actually a doubly linked list
 * with the properties of a queue - remove from rear and insert at front
 * or a FIFO
 */
public class LruQueue {
    //default capacity
    private static int capacity = 10;
    private static int size = 0;
    private Node head;
    private Node tail;

    public LruQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LruQueue(int capacity) {
        this();
        this.capacity = capacity;
    }

    /**
     * Adds element to the top/front
     * If max capacity reached, then pop from rear end and then push
     *
     * @param node
     */
    public void push(Node node){
        if(capacity <= size){
            pop();
        }

        if(size == 0){
            node.setNext(null);
            node.setPrev(null);
            tail = node;
        } else {
            head.setPrev(node);
            node.setNext(head);
        }

        head = node;
        size = size + 1;
    }

    /**
     * Removes the last element from tail
     */
    public void pop(){
        if(size == 0 || tail == null){
            return;
        }

        tail = tail.getPrev();
        tail.setNext(null);
        size = size - 1;
    }

    /**
     * Removes specific node
     */
    public void pop(Node node){
        if(size == 0 || node == null){
            return;
        }

        //if tail node
        if(node == tail) {
            pop();
        }
        else if(node == head){
            head = node.getNext();
            node.getNext().setPrev(null);
            node.setNext(null);
            node = null;
        } else {
            Node prev = node.getPrev();
            Node next = node.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            node = null;
        }

        size = size - 1;
    }

    public int getSize(){
        return this.size;
    }

    public static int getCapacity() {
        return capacity;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    static class Node<K, V> {
        private Node prev;
        private Node next;
        private V data;
        private K key;

        public Node(K key, V data) {
            this.key = key;
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public V getData() {
            return data;
        }

        public K getKey() {
            return key;
        }
    }
}
