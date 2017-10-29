package Aleksey.Plekhanov;

import java.util.*;

public class MassifList <T> implements List <T>{

    private static final int DEFAULT_SIZE = 2;
    private static int currenrSize = DEFAULT_SIZE;
    private static int index = 0;
    private T[] base;

    public MassifList (int size){
        base = (T[]) new Object[size];
    }
    public MassifList (){
        base = (T[]) new Object[DEFAULT_SIZE];
    }

    public int size() {
        return index;
    }

    public boolean isEmpty() {
        if (index == 0) return true;

        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public boolean add(Object o) {
        if (index ==  base.length) {
            T[] temp = (T[])new Object[currenrSize];
            temp = base.clone();
            currenrSize = (currenrSize * 3)/2 + 1;
            base = (T[]) new Object[currenrSize];
            System.arraycopy(temp,0,base,0,temp.length);
        }
        base[index] = (T)o;
        index++;
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean addAll(Collection c) {
        return false;
    }

    public boolean addAll(int index, Collection c) {
        return false;
    }

    public void clear() {

    }

    public T get(int index) {
        return base[index];
    }

    @Override
    public T remove(int index) {
        return null;
    }

    public Object set(int index, Object element) {
        if (index >= 0 && index <= this.index) {
            base[index] = (T)element;
        }

        return true;
    }

    public void add(int index, Object element) {

    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator listIterator() {
        return null;
    }

    public ListIterator listIterator(int index) {
        return null;
    }

    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
