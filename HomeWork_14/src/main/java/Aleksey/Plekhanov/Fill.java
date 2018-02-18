package Aleksey.Plekhanov;

import java.util.ArrayList;

public class Fill implements Runnable {

    private int size;
    private ArrayList<Integer> list;

    Fill (int size, ArrayList<Integer> list) {
        this.size = size;
        this.list = list;
    }
    public void run() {
        for (int i = 0; i < size; i++) {
            list.add((int)(Math.random() * 10));
        }
    }
}