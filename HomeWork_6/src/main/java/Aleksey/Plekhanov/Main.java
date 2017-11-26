package Aleksey.Plekhanov;


import java.util.Scanner;

public class Main {

    public static void main (String... args) {
        Atm atm = new Atm(10);
        Scanner in = new Scanner(System.in);
        boolean  proceed = true;
        while (proceed) {
            printMenu();
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    atm.getState();
                    break;
                case 2:
                    proceed = false;
                default:
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("------- MENU -------");
        System.out.println("1. State;");
        System.out.println("2. Exit.");
    }
}