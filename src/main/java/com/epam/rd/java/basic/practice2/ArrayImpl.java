package com.epam.rd.java.basic.practice2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    Object[] centralArray;
    int lengthOfArray ;
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
        Object[] a = new Object[10];
        centralArray = a;
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
            if(array[numberOfElement-1]==null || (numberOfElement-1)>=array.length) {
                throw new NoSuchElementException("There is no such element");
            }
            return array[numberOfElement-1];
        }

    }
	
	@Override
    public void add(Object element) {
        IteratorImpl iterator = new IteratorImpl(centralArray);
        for(int i=0; i<centralArray.length; i++) {
            if (!iterator.hasNext()) {
                centralArray[i] = element;
                actualLength++;
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
        if(index>actualLength-1 && index<lengthOfArray) actualLength++;
	    if(index<lengthOfArray) centralArray[index] = element;
        else throw new IndexOutOfBoundsException("The given index is out of bounds");
    }

	@Override
    public Object get(int index) {
        if(index<lengthOfArray) {
            Object answer =centralArray[index];


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
            centralArray[lengthOfArray - 1] = null;
            actualLength--;
        } else System.out.println("Index is out of bonds of the array");
    }

    @Override
    public String toString() {
        String answer;
        IteratorImpl iterator = new IteratorImpl(centralArray);
        StringBuilder stringBuilder = new StringBuilder();
        int counter =0;
        for(int i=0; i<actualLength; i++) {
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
        myArray.set(0,  'a');
        myArray.add("SDHOF");
        myArray.add('s');
        myArray.remove(1);
        myArray.clear();


        System.out.println(myArray.size());
        System.out.println(myArray.toString());

    }

}
