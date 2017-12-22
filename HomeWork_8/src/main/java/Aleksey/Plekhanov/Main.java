package Aleksey.Plekhanov;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main (String [] args) {
        List<Integer> list =  Arrays.asList(-1, 0, 1);
        SimpleTestClass[] simpleTestClasses = new SimpleTestClass[2];
        simpleTestClasses[0] = new SimpleTestClass();
        simpleTestClasses[1] = new SimpleTestClass();
        TestClass testClass = new TestClass(1, "He\"llo",  new int[]{-1, 0, 1}, true, list, simpleTestClasses);
        start(testClass);

        int[] arrayInts = {1, 2, 3};
        start(arrayInts);

        String[] arrayStrings = {"a", "b"};
        start(arrayStrings);

        int integer = 123;
        start(integer);

        String string = "abc";
        start(string);

        start(new A());
    }

    private static void start(Object object) {
        System.out.println("____________" + object.getClass().getSimpleName() + "____________");
        String myStr = Serializer.serialize(object);
        System.out.println("  My JSON:" + myStr);
        Gson gson = new Gson();
        System.out.println("Gson JSON:" + gson.toJson(object));

        System.out.println("Gson deserialization my JSON:");
        if (object.getClass().isArray()) {
            System.out.println(Arrays.toString(convertToObjectArray(gson.fromJson(myStr, object.getClass()))));
        }else {
            System.out.println(gson.fromJson(myStr, object.getClass()) + "\n");
        }

    }

    private static Object[] convertToObjectArray(Object array) {
        Class ofArray = array.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            List ar = new ArrayList();
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                ar.add(Array.get(array, i));
            }
            return ar.toArray();
        }
        else {
            return (Object[]) array;
        }
    }

    static class A{
        int a = 0;
        String str = "x\"yz";
        B b = new B();

        @Override
        public String toString() {
            return "A{" +
                    "a=" + a +
                    ", str='" + str + '\'' +
                    ", b=" + b +
                    '}';
        }
    }

    static class B{
        int aa = 654;
        String bb = "9\"87";

        @Override
        public String toString() {
            return "B{" +
                    "aa=" + aa +
                    ", bb='" + bb + '\'' +
                    '}';
        }
    }
}