package Aleksey.Plekhanov;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main (String [] args) {
        List<Integer> list =  Arrays.asList(-1, 0, 1);
        SimpleTestClass[] simpleTestClasses = new SimpleTestClass[2];
        simpleTestClasses[0] = new SimpleTestClass();
        simpleTestClasses[1] = new SimpleTestClass();
        TestClass testClass = new TestClass(1, "Hello",  new int[]{-1, 0, 1}, true, list, simpleTestClasses);

        System.out.println("My JSON for TestClass");
        String myStr = IdentifierObjects.defineObject(testClass);
        System.out.println("JSON object: " + myStr + "\n");

        Gson gson = new Gson();
        System.out.println("Gson JSON for TestClass");
        String gsonStr = gson.toJson(testClass);
        System.out.println("JSON object: " + gsonStr + "\n");

        System.out.println("Gson deserialization myJSON for TestClass");
        TestClass read = gson.fromJson(myStr, TestClass.class);
        System.out.println(read.toString() + "\n");

        System.out.println("My JSON for array int");
        int[] arrayInts = {1, 2, 3};
        String myStrArrayInts = IdentifierObjects.defineObject(arrayInts);
        System.out.println("JSON object: " + myStrArrayInts + "\n");

        System.out.println("Gson JSON for array int");
        String gsonStrArrayInts = gson.toJson(arrayInts);
        System.out.println("JSON object: " + gsonStrArrayInts + "\n");

        System.out.println("Gson deserialization myJSON for array int");
        System.out.println(Arrays.toString(gson.fromJson(myStrArrayInts, arrayInts.getClass())) + "\n");

        System.out.println("My JSON for array String");
        String[] array = {"a", "b"};
        String myStrArrayStrings = IdentifierObjects.defineObject(array);
        System.out.println("JSON object: " + myStrArrayStrings + "\n");

        System.out.println("Gson JSON for array String");
        String gsonStrArrayStrings = gson.toJson(array);
        System.out.println("JSON object: " + gsonStrArrayStrings + "\n");

        System.out.println("Gson deserialization myJSON for array String");
        System.out.println(Arrays.toString(gson.fromJson(myStrArrayStrings, array.getClass())) + "\n");
    }
}
