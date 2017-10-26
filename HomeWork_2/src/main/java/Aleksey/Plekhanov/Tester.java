package Aleksey.Plekhanov;

class Tester {

    static void testObject (int size, Class clazz) {
        Runtime runtime = Runtime.getRuntime();
        Object[] array;
        long mem1;
        long mem2;
        switch (clazz.getSimpleName()) {
            case "Object":
                array = new Object[size];
                mem1 = getMemorySize(runtime);
                for (int i = 0; i < size; i++) {
                    array[i] = new Object();
                }
                mem2 = getMemorySize(runtime);
                System.out.println("Object size: " + getObjectSize(size, mem1, mem2));
                break;

            case "String":
                array = new Object[size];
                mem1 = getMemorySize(runtime);
                for (int i = 0; i < size; i++) {
                    array[i] = new String(""); //String pool
                }
                mem2 = getMemorySize(runtime);
                System.out.println("String pool size: " + getObjectSize(size, mem1, mem2));

                array = new Object[size];
                mem1 = getMemorySize(runtime);
                for (int i = 0; i < size; i++) {
                    array[i] = new String(new char[0]); //without String pool
                }
                mem2 = getMemorySize(runtime);
                System.out.println("String without String pool size: " + getObjectSize(size, mem1, mem2));
                break;

            case "MyClass":
                array = new Object[size];
                mem1 = getMemorySize(runtime);
                for (int i = 0; i < size; i++) {
                    array[i] = new MyClass();
                    getContainerSize (i, mem1, runtime);
                }
                mem2 = getMemorySize(runtime);
                System.out.println("Object MyClass size: " + getObjectSize(size, mem1, mem2));
                break;
        }
    }

    private static long getObjectSize (int size, long mem1, long mem2) {
        return (mem2 - mem1)/size;
    }

    private static long getMemorySize(Runtime runtime) {
        System.gc();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static void getContainerSize (int i, long mem1, Runtime runtime){
        if (i % 2_000_000 == 0) {
            long mem2 = runtime.totalMemory() - runtime.freeMemory();
            long result = mem2 - mem1;
            System.out.println("Container from " + i + " elements size: " + result);
        }
    }
}