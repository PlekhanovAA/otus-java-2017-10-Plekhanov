package Aleksey.Plekhanov;

import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String... args) throws Exception{
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        new GcViewer();
        Garbage.start();
    }
}
