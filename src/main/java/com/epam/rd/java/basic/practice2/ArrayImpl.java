package com.epam.rd.java.basic.practice2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    Object[] centralArray;
    int lengthOfArray;
    int actualLength =0;

    public ArrayImpl(int size) {
        this.centralArray = new Object[size];
        this.lengthOfArray= centralArray.length;
    }
    public ArrayImpl() {
        this.centralArray = new Object[10];
        this.lengthOfArray= centralArray.length;
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
                throw new NoSuchElementException("There is no such element 0");
            }
            return array[numberOfElement-1];
        }

    }

    @Override
    public void add(Object element) {
        if(actualLength >= lengthOfArray-3) {
            Object[] a = new Object[lengthOfArray*3];
            System.arraycopy(centralArray, 0, a, 0, centralArray.length);
            centralArray=a;

        }
        centralArray[actualLength] = element;
        actualLength++;

    }

    @Override
    public void set(int index, Object element) {

        if(index>actualLength-1 && index<lengthOfArray) {
            if(actualLength >= lengthOfArray-3) {
                Object[] a = new Object[lengthOfArray*3];
                System.arraycopy(centralArray, 0, a, 0, centralArray.length);
                centralArray=a;
            }
            actualLength++;
        }
        if(index<lengthOfArray) centralArray[index] = element;
        else throw new IndexOutOfBoundsException("The given index is out of bounds");
    }

    @Override
    public Object get(int index) {
        if(index<lengthOfArray) {
            Object answer =centralArray[index];
            if(answer ==null) throw new NoSuchElementException("There is no such index in the array 1");
            return answer;
        }

        return null;
    }

    @Override
    public int indexOf(Object element) {
        try {
            for (int i = 0; i < lengthOfArray; i++) {
                if (centralArray[i].equals(element)) return i;
            }
        } catch (NullPointerException e) {
            for(int i=0; i<lengthOfArray;i++) {
                if(centralArray[i]==null) return i;
            }
        }
        throw new NoSuchElementException("There is no such element in the array 3");
    }

    @Override
    public void remove(int index) {
        if(index<lengthOfArray) {
            for (int i = index; i < lengthOfArray - 1; i++) {
                centralArray[i] = centralArray[i + 1];
            }
            centralArray[lengthOfArray - 1] = null;
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
        myArray.add( null);
        myArray.add("SDHOF");
        myArray.add(9);
        myArray.add('s');
        myArray.add('s');
        myArray.remove(1);



        System.out.println(myArray.indexOf(null) );
        System.out.println(myArray.toString());

    }

}




