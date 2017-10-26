package Aleksey.Plekhanov;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int size = 10_000_000;
        System.out.println("Array for test from: " + size + " elements");

        Tester.testObject(size, Object.class);
        Tester.testObject(size, String.class);
        Tester.testObject(size, MyClass.class);
    }
}