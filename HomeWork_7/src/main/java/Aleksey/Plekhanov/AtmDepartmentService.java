package Aleksey.Plekhanov;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AtmDepartmentService {

    private static List<Atm> atms = new ArrayList<>();

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
            int i = 1;
            for (Atm atm : atms) {
                System.out.println("Recycled ATM#" + i + ":");
                atm.revert();
                i++;
            }
        }
    }
}