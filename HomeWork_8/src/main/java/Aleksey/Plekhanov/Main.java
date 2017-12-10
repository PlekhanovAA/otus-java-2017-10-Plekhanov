package Aleksey.Plekhanov;

import com.google.gson.Gson;

public class Main {

    public static void main (String [] args) throws IllegalAccessException{
        TestClass testClass = new TestClass(1, "Hello",  new int[]{-1, 0, 1}, true);
        System.out.println("____________________ My JSON ____________________");
        String myStr = Serializer.serialize(testClass);
        System.out.println("JSON object: " + myStr + "\n");
        Gson gson = new Gson();
        System.out.println("___________________ Gson JSON ___________________");
        String gsonStr = gson.toJson(testClass);
        System.out.println("JSON object: " + gsonStr + "\n");
        System.out.println("________ Gson deserialization for myJSON ________");
        TestClass read = gson.fromJson(myStr, TestClass.class);
        System.out.println(read.toString() + "\n");
        System.out.println("Equals: " + testClass.equals(read));
    }
}
