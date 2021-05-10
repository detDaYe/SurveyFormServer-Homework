package org.lumenk.object.webserver.util;

public class MyQueue <T>{

    private Node front = null;
    private Node rear = null;
    private int count = 0;

    public void enqueue(T data){
        Node node = new Node(null, data);

        if(front == null) front = node;
        if(rear != null) rear.link = node;
        rear = node;

        count++;
    }

    public T dequeue(){
        if(count == 0) return null;
        T temp = front.data;
        front = front.link;

        count--;
        return temp;
    }

    public int size(){
        return count;
    }

    private class Node{
        Node link;

        public Node(Node link, T data) {
            this.link = link;
            this.data = data;
        }

        T data;
    }
}
