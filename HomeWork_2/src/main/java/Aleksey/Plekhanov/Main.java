package Aleksey.Plekhanov;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        int size = 10_000_000;

        Object[] array = new Object[size];
        System.out.println("New array Object of size: " + array.length + " created");
        System.gc();
        long mem1 = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new Object();
        }
        System.out.println("Created " + size + " objects.");
        System.gc();
        long mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory test: " + Tester.test(size, mem1, mem2));

        array = new Object[size];
        System.out.println("New array string pool of size: " + array.length + " created");
        System.gc();
        mem1 = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new String(""); //String pool
        }
        System.out.println("Created " + size + " objects.");
        System.gc();
        mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory test: " + Tester.test(size, mem1, mem2));

        array = new Object[size];
        System.out.println("New array without string pool of size: " + array.length + " created");
        System.gc();
        mem1 = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new String(new char[0]); //without String pool
        }
        System.out.println("Created " + size + " objects.");
        System.gc();
        mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory test: " + Tester.test(size, mem1, mem2));

        array = new Object[size];
        System.out.println("New array MyClass of size: " + array.length + " created");
        System.gc();
        mem1 = runtime.totalMemory() - runtime.freeMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new MyClass();
        }
        System.out.println("Created " + size + " objects.");
        System.gc();
        mem2 = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory test: " + Tester.test(size, mem1, mem2));
    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;
    }
}