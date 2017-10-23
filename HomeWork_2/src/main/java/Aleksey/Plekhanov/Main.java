package Aleksey.Plekhanov;

import java.lang.management.ManagementFactory;

/**
* Runtime runtime = Runtime.getRuntime();
        * long mem = runtime.totalMemory() - runtime.freeMemory();
        * System.gc()
 */

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
        Runtime runtime = Runtime.getRuntime();
        int size = 20_000_000;

        System.out.println("Starting the loop");
        while (true) {
            long mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Memory  after array: " + mem);
            Object[] array = new Object[size];
            System.out.println("New array of size: " + array.length + " created");
            for (int i = 0; i < size; i++) {
                array[i] = new Object();
                //array[i] = new String(""); //String pool
                //array[i] = new String(new char[0]); //without String pool
                //array[i] = new MyClass();
            }
            System.out.println("Created " + size + " objects.");
            mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Memory  before array after GC: " + mem);
            System.gc();
            mem = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Memory  before array before GC: " + mem);
            Thread.sleep(1000); //wait for 1 sec
        }
    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;
    }
}