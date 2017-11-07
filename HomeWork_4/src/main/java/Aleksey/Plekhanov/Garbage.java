package Aleksey.Plekhanov;

import java.util.ArrayList;
import java.util.Collections;

class Garbage {

    static void start() throws InterruptedException {
        int size = 500_000;
        ArrayList<Integer> array = new ArrayList<>();

        while (true) {
            System.out.println("Starting the loop");
            array.addAll(Collections.nCopies(size/2, 0));
            array.addAll(Collections.nCopies(size/2, 1));
            array.removeAll(Collections.singleton(1));
            System.out.println("Array size: " + array.size() + ". Free memory: " + Runtime.getRuntime().freeMemory());
            Thread.sleep(1000);
        }
    }
}
