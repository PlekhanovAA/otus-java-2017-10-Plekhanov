package Aleksey.Plekhanov;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AtmDepartmentService {

    private static List<Atm> atms = new ArrayList<>();

    static void printMenu() {
        System.out.println("---------- ATM DEPARTMENT MENU ---------");
        System.out.println("1. Build ATM;");
        System.out.println("2. Views ATM;");
        System.out.println("3. Working with current ATM;");
        System.out.println("4. Total volume;");
        System.out.println("5. Revert state all ATMs;");
        System.out.println("6. Exit.");
    }

    static void buildAtm(Scanner console) {
        System.out.println("Count banknotes: ");
        atms.add(new Atm(console.nextInt()));
    }

    static void viewsAtm () {
        if (atms.isEmpty()) {
            System.out.println("ATMs missing.");
        }else {
            int i = 1;
            for (Atm atm : atms) {
                System.out.println("#" + i + "ATM-------------");
                atm.getStateAtm();
                i++;
            }
        }
    }

    static void working (Scanner console){
        if (atms.isEmpty()) {
            System.out.println("ATMs missing.");
        }else{
            viewsAtm();
            System.out.println("what ATM to work?");
            try {
                AtmService.startAtm(atms.get(console.nextInt() - 1), console);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect choice");
            }

        }
    }

    static void getBalance() {
        if (atms.isEmpty()) {
            System.out.println("ATMs missing.");
        }else {
            int total = 0;
            int i = 1;
            for (Atm atm : atms) {
                int currentVolume = atm.getVolume();
                System.out.println("ATM#" + i + " value: " + currentVolume);
                total += currentVolume;
                i++;
            }
            System.out.println("Total value: " + total);
        }
    }

    static void revertState(){
        if (atms.isEmpty()) {
            System.out.println("ATMs missing.");
        }else {
            System.out.println("Revert state all ATMs;");
        }
    }
}