package Aleksey.Plekhanov;

import Aleksey.Plekhanov.annotations.Before;

public class TestClass {

    @Before
    public static void beforeAnnoTest() {
        System.out.println("Test Before Annotation");
    }
}
