package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListImpl implements List {
    private MyNode head;
    private MyNode tail;
    private int size =0;

    public ListImpl() {
        this.head = new MyNode();
        this.tail = new MyNode();
        head.next = tail;
    }

    @Override
    public void clear() {
        this.head = new MyNode();
        this.tail = new MyNode();
        head.next = tail;
        size=0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(this);
    }

    private class MyNode {
        Object value;
        private MyNode next;

        MyNode(Object value) {
            this.value = value;
            this.next = null;
        }
        MyNode() {
            this.value = (char)0;
            this.next = null;
        }

        @Override
        public String toString() {
            if(value==null) { // correct if wrong (crutch)
                value = "null";
                return value.toString();
            }
            return value.toString();
        }

    }

    private class IteratorImpl implements Iterator<Object> {
        private int numberOfIteration=0;
        private final ListImpl list;
        protected IteratorImpl(ListImpl list) {
            this.list=list;
        }
        @Override
        public boolean hasNext() {
            MyNode current = list.head;
            for(int i=0; i<=numberOfIteration; i++) {
                current = current.next;
                if(i==numberOfIteration && !current.equals ((char)0)) {

                    numberOfIteration++;
                    return true;

                }
            }
            numberOfIteration++;
            return false;
        }

        @Override
        public Object next() {
            try {
                if(numberOfIteration>size) throw new NoSuchElementException("Iteration beyond the end of the collection");
                MyNode current = list.head;
                for(int i=0; i<=numberOfIteration; i++) {
                    current = current.next;
                    if(i==numberOfIteration) {
                        return current.value.toString();
                    }
                }
                return "null";
            } catch (NullPointerException e) {
                return ("[" + null + "]");
            }

        }

    }

    @Override
    public void addFirst(Object element) {
        if(element == null) {
            element="null";
        }
        if(head.value.equals((char)0)) {
            head.value = element; // if head is empty (lol)
        }
        else {
            MyNode node = new MyNode(element);
            node.next = head;
            head = node;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        if(element == null) {
            element="null";
        }
        if(head.value.equals((char)(0))) {
            addFirst(element);

            return;
        }
        else if(tail.value.equals((char)(0))) {
            tail.value = element; // if tail is empty

        }
        else {
            MyNode node = new MyNode(element);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        if(head.value.equals((char)0)) return; // if head is empty (lol) there is nothing to remove
        else {
            head = head.next;
            size--;
        }
    }

    @Override
    public void removeLast() {
        MyNode current = head;
        for(int i=0; i<size-2; i++) {
            current = current.next;
        }

        tail = current;

        size--;
    }

    @Override
    public Object getFirst() {
        if(head.value.equals((char)0)) return null;
        if(head.value.equals("null")) return null;
        else return head.value;
    }

    @Override
    public Object getLast() {
        if(tail.value.equals((char)0)) return null;
        if(tail.value.equals("null")) return null;
        else return tail.value;
    }

    @Override
    public Object search(Object element) {

        MyNode current = head;
        for(int i=0; i<size; i++) {
            if(current.value.equals(element) ) {
                return current.value;

            }

            current = current.next;

        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if(element == null) {
            element="null";
        }
        if(head.value.equals(element)) {
            removeFirst();
            return true;
        }
        else if(tail.value.equals(element)) {
            removeLast();
            return true;
        }
        else if(this.search(element) ==null) {
            return false;
        }
        else {
            MyNode current = head;
            for(int i=0; i<size; i++) {
                if(current.value.equals(element) ) {
                    current.next = current.next.next;
                    System.out.println("found");
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        if(head.value.equals((char)0)) {
            return "";
        }
        if(tail.value.equals((char)0)) tail=null;
        String answer;
        StringBuilder builder = new StringBuilder();
        MyNode current = this.head;
        int counter =0;
        while(current != null) {
            //Prints each node by incrementing pointer
            if(counter == size-1) {
                builder.append(current.value.toString());
                break;
            }
            builder.append(current.value.toString() + ", ");
            current = current.next;
            counter++;
        }
        answer = builder.toString();
        return "["+ answer+"]";
    }

    public static void main(String[] args) {
        ListImpl myList = new ListImpl();
        myList.addLast(null);
        myList.addLast("A");
        myList.addLast("B");

        myList.addLast("C");
        System.out.println(myList.toString());
        System.out.println(myList.getLast());
        myList.remove("C");
        System.out.println(myList.toString());
        System.out.println(myList.getLast());

    }
}

