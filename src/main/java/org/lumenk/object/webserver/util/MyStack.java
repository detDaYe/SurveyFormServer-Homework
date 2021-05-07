package org.lumenk.object.webserver.util;

public class MyStack <T>{

    private int count;
    private Node top;

    public MyStack(){
        count = 0;
        top = null;
    }

    public void push(T data){
        top = new Node(top, data);
        count++;
    }

    public T pop(){
        if(count == 0) return null;
        T temp = top.data;
        top = top.link;
        count--;
        return temp;
    }

    public T peek(){
        return top == null? null : top.data;
    }

    public int size(){
        return count;
    }

    private class Node{

        public Node(Node link, T data) {
            this.link = link;
            this.data = data;
        }

        Node link;
        T data;
    }
}
