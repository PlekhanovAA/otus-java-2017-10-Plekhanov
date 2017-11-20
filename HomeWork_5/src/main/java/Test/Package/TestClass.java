package Test.Package;

import Aleksey.Plekhanov.annotations.After;
import Aleksey.Plekhanov.annotations.Before;
import Aleksey.Plekhanov.annotations.Test;

public class TestClass {

    @Before (resultTest = "OK")
    public static String beforeAnnoTest() {
        return "OK";
    }

    @Test (resultTest = "OK")
    public static String testAnnoTest() {
        return "OK";
    }

    @After(resultTest = "OK")
    public static String afterAnnoTest() {
        return "OK";
    }
}