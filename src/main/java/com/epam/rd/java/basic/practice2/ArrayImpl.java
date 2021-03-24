package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    Object[] centralArray;

    int actualLength =0;

    public ArrayImpl(int size) {
        this.centralArray = new Object[size];

    }
    public ArrayImpl() {
        this.centralArray = new Object[1];

    }

    @Override
    public void clear() {
        centralArray = new Object[10];

        actualLength=0;
    }

    @Override
    public int size() {
        return actualLength;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl(centralArray);
    }

    private class IteratorImpl implements Iterator<Object> {
        private Object[] array;
        private int numberOfElement=0;

        IteratorImpl(Object[] centralArray) {
            this.array=centralArray;
        }

        @Override
        public boolean hasNext() {
            if(centralArray[numberOfElement] !=null) {
                numberOfElement++;
                return true;
            }
            else return false;
        }

        @Override
        public Object next() {
            if(array[numberOfElement-1]==null || (numberOfElement-1)>array.length) {
                throw new NoSuchElementException("Iteration beyond the end of the collection");
            }
            return array[numberOfElement-1].toString();
        }

    }

    @Override
    public void add(Object element) {
        if(actualLength >= centralArray.length/2) {
            Object[] a = new Object[centralArray.length*3];
            System.arraycopy(centralArray, 0, a, 0, centralArray.length);
            centralArray=a;

        }
        centralArray[actualLength] = element;
        actualLength++;

    }

    @Override
    public void set(int index, Object element) {

        if(index>actualLength-1 && index<centralArray.length) {
            if(actualLength >= centralArray.length-3) {
                Object[] a = new Object[centralArray.length*3];
                System.arraycopy(centralArray, 0, a, 0, centralArray.length);
                centralArray=a;
            }
            actualLength++;
        }
        if(index<centralArray.length) centralArray[index] = element;
        else throw new IndexOutOfBoundsException("The given index is out of bounds");
    }

    @Override
    public Object get(int index) {

        if(index<centralArray.length) {
            return centralArray[index];

        }
        return null;

    }

    @Override
    public int indexOf(Object element) {

        try {
            for (int i = 0; i <=actualLength; i++) {

                if (element.equals(centralArray[i])) return i;
            }

        } catch (NullPointerException e) {
            for(int i=0; i<centralArray.length;i++) {
                if(centralArray[i]==null) return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if(index<centralArray.length) {
            for (int i = index; i < centralArray.length - 1; i++) {
                centralArray[i] = centralArray[i + 1];
            }
            centralArray[centralArray.length - 1] = null;
            actualLength--;
        } else System.out.println("Index is out of bonds of the array");
    }

    @Override
    public String toString() {
        String answer;
        StringBuilder stringBuilder = new StringBuilder();
        int counter =0;
        for(int i=1; i<=actualLength; i++) {
            if((counter) ==actualLength-1) {
                stringBuilder.append(centralArray[counter]);
                break;
            }
            stringBuilder.append(centralArray[counter]+", ");
            counter++;
        }
        answer = stringBuilder.toString();

        return answer;

    }

    public static void main(String[] args) {
        ArrayImpl myArray = new ArrayImpl();
        myArray.add( "A");
        myArray.add("B");
        myArray.add("C");
        myArray.add(null);
        myArray.add("D");
        myArray.add("E");

        for(int i=0 ; i< myArray.actualLength; i++) {

            System.out.println(myArray.indexOf(myArray.get(i)));
        }

        System.out.println(myArray.get(5));


        System.out.println(myArray.toString());
        System.out.println(myArray.size());
    }

}








