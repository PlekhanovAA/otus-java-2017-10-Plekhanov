package Aleksey.Plekhanov;

import java.util.Scanner;

class AtmDepartment {

    static void start() {
        boolean  proceed = true;
        try (Scanner console = new Scanner(System.in)) {
            while (proceed) {
                AtmDepartmentService.printMenu();
                int choice = console.nextInt();
                switch (choice) {
                    case 1:
                        AtmDepartmentService.buildAtm(console);
                        break;
                    case 2:
                        AtmDepartmentService.viewsAtm();
                        break;
                    case 3:
                        AtmDepartmentService.working(console);
                        break;
                    case 4:
                        AtmDepartmentService.getBalance();
                        break;
                    case 5:
                        AtmDepartmentService.revertState();
                        break;
                    case 6:
                        proceed = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}