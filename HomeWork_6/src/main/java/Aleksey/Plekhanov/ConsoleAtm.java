package Aleksey.Plekhanov;

import java.util.Scanner;

class ConsoleAtm {

    private static Scanner in = new Scanner(System.in);

    static Scanner getIn() {
        return in;
    }

    static void close() {
        in.close();
    }
}