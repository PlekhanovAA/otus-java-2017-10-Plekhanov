package Aleksey.Plekhanov;

import java.util.*;
@SuppressWarnings("unchecked")
public class MassifList <T> implements List <T>{

    private static final int DEFAULT_SIZE = 2;
    private static int currentSize = DEFAULT_SIZE;
    private static int size = 0;
    private T[] base;

    public MassifList (int size){
        base = (T[]) new Object[size];
        currentSize = size;
    }
    MassifList (){
        base = (T[]) new Object[DEFAULT_SIZE];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public boolean add(Object o) {
        if (size == currentSize) {
            T[] temp;
            temp = base.clone();
            currentSize = (currentSize * 3)/2 + 1;
            base = (T[]) new Object[currentSize];
            System.arraycopy(temp,0,base,0,temp.length);
        }
        base[size] = (T)o;
        size++;
        return true;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    public T get(int index) {
        rangeCheck(index);
        return base[index];
    }

    @Override
    public T remove(int index) {
        return null;
    }

    public Object set(int index, Object element) {
        if (index >= 0 && index <= size) {
            base[index] = (T)element;
        }

        return true;
    }

    public void sort (Comparator<? super T> c) {
        Arrays.sort(base, 0, size, c);
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator iterator() {
        return null;
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
