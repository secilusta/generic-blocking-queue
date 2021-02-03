package org.secil;

public class Queue<T> implements IQueue<T>{
    private Node<T> head;
    private Node<T> tail;

    public Queue(){
        head = null;
        tail = null;
    }

    @Override
    public void Add(T value) {
        Node<T> node = new Node<T>(value);
        if (head == null) {
            head = node;
        }
        else {
            tail.setNextNode(node);
        }
        tail = node;
    }

    @Override
    public T Peek(){ //değeri queue dan çıkartmadan dön
        if (head == null) return null;
        return head.getValue();
    }

    @Override
    public T Poll(){ //değeri queue dan çıkartarak dön
        T value = null;

        if (head != null) {
            value = head.getValue();
            head = head.getNextNode();
        }
        return value;
    }

    public void printQueue(){
        Node node = head;
        while ( node != null ){
            System.out.println(node.getValue());
            node = node.getNextNode();
        }
    }
}
