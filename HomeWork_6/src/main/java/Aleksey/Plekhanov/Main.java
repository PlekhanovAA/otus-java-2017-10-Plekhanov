package Aleksey.Plekhanov;

public class Main {

    public static void main (String... args) {
        Atm atm = new Atm(10);
        boolean  proceed = true;
        while (proceed) {
            atm.printMenu();
            int choice = ConsoleAtm.getIn().nextInt();
            switch (choice) {
                case 1:
                    atm.getState();
                    break;
                case 2:
                    atm.printMenuReceiving();
                    int nominal = ConsoleAtm.getIn().nextInt();
                    System.out.println("Choice count:");
                    int count = ConsoleAtm.getIn().nextInt();
                    atm.receiving(nominal, count);
                    break;
                case 3:
                    System.out.println("------- RETURNING -------");
                    System.out.println("Input sum:");
                    int sum = ConsoleAtm.getIn().nextInt();
                    atm.returning(sum);
                    break;
                case 4:
                    proceed = false;
                    break;
                default:
                    break;
            }
        }
        ConsoleAtm.close();
    }
}