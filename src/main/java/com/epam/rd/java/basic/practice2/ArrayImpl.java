package com.epam.rd.java.basic.practice2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    Object[] centralArray = new Object[10];
    int lengthOfArray = centralArray.length;

	@Override
    public void clear() {
        Object[] a = new Object[10];
        centralArray = a;
    }

	@Override
    public int size() {
	    IteratorImpl iterator = new IteratorImpl(centralArray);
        int counter=0;
        while(iterator.hasNext()) counter++;
        return counter;
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

            return array[numberOfElement-1];
        }

    }
	
	@Override
    public void add(Object element) {
        IteratorImpl iterator = new IteratorImpl(centralArray);
        for(int i=0; i<centralArray.length; i++) {
            if (!iterator.hasNext()) {
                centralArray[i] = element;
                return;
            }
        }
        Object[] a = new Object[lengthOfArray*3];
        System.arraycopy(centralArray, 0, a, 0, centralArray.length);
        centralArray=a;
        add(element);
    }

	@Override
    public void set(int index, Object element) {
	    if(index<lengthOfArray) centralArray[index] = element;
        else System.out.println("The given index is out of bonds");
    }

	@Override
    public Object get(int index) {
        if(index<lengthOfArray) {
            Object answer =centralArray[index];
            if(answer ==null) throw new NoSuchElementException("There is no such index in the array");

            return answer;
        }

        throw new NoSuchElementException("There is no such index in the array");
    }

	@Override
    public int indexOf(Object element) {
        for(int i=0; i<lengthOfArray;i++) {
            if(centralArray[i].equals(element)) return i;
        }
        throw new NoSuchElementException("There is no such element in the array");
    }

	@Override
    public void remove(int index) {
	    if(index<lengthOfArray) {
            for (int i = index; i < lengthOfArray - 1; i++) {
                centralArray[i] = centralArray[i + 1];
            }
            centralArray[lengthOfArray - 1] = (char) 0;
        } else System.out.println("Index is out of bonds of the array");
    }

    @Override
    public String toString() {
        String answer;
        IteratorImpl iterator = new IteratorImpl(centralArray);
        StringBuilder stringBuilder = new StringBuilder();
        int counter =0;
        while(iterator.hasNext()) {
            if(centralArray[counter+1] ==null) {
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
        myArray.add(2);
        myArray.add("SDHOF");
        myArray.add('s');
        myArray.remove(1);
        myArray.set(0, "s;duoufhgi");

        System.out.println(myArray.get(1));
        System.out.println(myArray.size());
        System.out.println(myArray.toString());

    }

}
