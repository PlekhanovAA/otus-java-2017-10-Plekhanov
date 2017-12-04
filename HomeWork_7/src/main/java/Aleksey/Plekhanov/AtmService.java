package Aleksey.Plekhanov;

import java.util.Scanner;

class AtmService {

    static void  startAtm(Atm atm, Scanner console) {
        boolean  proceed = true;
        while (proceed) {
            atm.printMenu();
            int choice = console.nextInt();
            switch (choice) {
                case 1:
                    atm.getStateAtm();
                    break;
                case 2:
                    atm.printMenuReceiving();
                    int nominal = console.nextInt();
                    System.out.println("Choice count:");
                    int count = console.nextInt();
                    atm.receiving(nominal, count);
                    break;
                case 3:
                    System.out.println("------- RETURNING -------");
                    System.out.println("Input sum:");
                    int sum = console.nextInt();
                    atm.returning(sum);
                    break;
                case 4:
                    proceed = false;
                    break;
                default:
                    break;
            }
        }
    }
}