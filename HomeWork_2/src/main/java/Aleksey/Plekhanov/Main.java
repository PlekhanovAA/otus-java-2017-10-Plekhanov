package Aleksey.Plekhanov;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        int size = 10_000_000;
        System.out.println("Array for test from: " + size + " elements");

        Tester.testObject(size, runtime, Object.class);
        Tester.testObject(size, runtime, String.class);
        Tester.testObject(size, runtime, MyClass.class);
    }
}